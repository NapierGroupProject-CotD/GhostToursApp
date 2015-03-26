package ghost

class BookingController {
	def bookingService

    def index() { }
	
	def newBookingDetails(){
		if(params.chosenTour == null) {
			flash.message = "Please select tour!"
			redirect(controller:"staff", action:"bookerDashboard")
		} else {
		
			def tour = Tour.get(params.chosenTour.toInteger())
			def remainingTourPlaces = tour.getRemainingPlaces()
			
			def tourBookings = Booking.findAllByTour(tour)

			[tour:tour, remainingTourPlaces:remainingTourPlaces, tourBookings:tourBookings]
		}
	}
	
	def addBooking(){
		def booking = new Booking()
		Staff staff = session.getAttribute("loggedInStaff")
		
		booking.tour = Tour.get(params.tourId.toInteger())
		booking.staff = staff
		booking.custName = params.custName
		booking.numberPeople = params.numberPeople.toInteger() 
		booking.save(flush:true)
		
		redirect(controller:"staff", action:"bookerDashboard")
	}
	
	def cancelBooking(){
		def booking = Booking.get(params.bookingId.toInteger())
		booking.delete(flush:true, failOnError:true)
		
		redirect(action:"newBookingDetails", params:[chosenTour:params.chosenTour])
	}
	
	def nextDay(){
		Calendar selectedDate = session.getAttribute("selectedDate")
		selectedDate.add(Calendar.DATE, 1)
		selectedDate.set(Calendar.HOUR, 0)
		session.setAttribute("selectedDate", selectedDate)
		session.setAttribute("today", false)
		
		redirect(controller:"staff", action:"bookerDashboard")
	}
	def previousDay(){
		Calendar selectedDate = session.getAttribute("selectedDate")
		selectedDate.add(Calendar.DATE, -1)
		selectedDate.set(Calendar.HOUR, 0)
		session.setAttribute("selectedDate", selectedDate)
		
		Calendar now = Calendar.instance
		if(selectedDate.clearTime() == now.clearTime()){
			session.setAttribute("today", true)
			session.setAttribute("selectedDate", null)
		}
		
		redirect(controller:"staff", action:"bookerDashboard")
	}
	
	def today(){
		def selectedDate = Calendar.instance
		session.setAttribute("selectedDate", selectedDate)
		session.setAttribute("today", true)
		
		redirect(controller:"staff", action:"bookerDashboard")
	}
}
