package ghost

import java.text.DateFormat
import java.text.SimpleDateFormat

class BookingController {
	def bookingService

    def index() { }
	
	def newBookingDetails(){
		if(!session.getAttribute("loggedInStaff")){
			redirect(controller:"staff", action:"logout")
		} else {
		
			if(params.chosenTour == null) {
				flash.message = "Please select tour!"
				redirect(controller:"staff", action:"bookerDashboard")
			} else {
				def tour = Tour.get(params.chosenTour)
				def remainingTourPlaces = tour.getRemainingPlaces()
				def tourBookings = Booking.findAllByTour(tour)

				[tour:tour, remainingTourPlaces:remainingTourPlaces, tourBookings:tourBookings]
			}
		}
	}
	
	def addBooking(){
		if(!session.getAttribute("loggedInStaff") || (!session.getAttribute("isBooker") && !session.getAttribute("isManager"))){
			redirect(controller:"staff", action:"logout")
		} else {
		
			def booking = new Booking()
			Staff staff = session.getAttribute("loggedInStaff")
		
			booking.tour = Tour.get(params.tourId.toInteger())
			booking.staff = staff
			booking.custName = params.custName
			booking.numberPeople = params.numberPeople.toInteger() 
			booking.save(flush:true)
		
			redirect(controller:"staff", action:"bookerDashboard")
		}
	}
	
	def cancelBooking(){
		if(!session.getAttribute("loggedInStaff") || (!session.getAttribute("isBooker") && !session.getAttribute("isManager"))){
			redirect(controller:"staff", action:"logout")
		} else {
			def booking = Booking.get(params.bookingId.toInteger())
			booking.delete(flush:true, failOnError:true)
		
			redirect(action:"newBookingDetails", params:[chosenTour:params.chosenTour])
		}
	}
	
	def nextDay(){
		if(!session.getAttribute("loggedInStaff")){
			redirect(controller:"staff", action:"logout")
		} else {
			Calendar selectedDate = session.getAttribute("selectedDate")
			selectedDate.add(Calendar.DATE, 1)
			selectedDate.set(Calendar.HOUR, 0)
			session.setAttribute("selectedDate", selectedDate)
			session.setAttribute("today", false)
		
			redirect(controller:"staff", action:"bookerDashboard")
		}
	}
	def previousDay(){
		if(!session.getAttribute("loggedInStaff")){
			redirect(controller:"staff", action:"logout")
		} else {
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
	}
	
	def today(){
		if(!session.getAttribute("loggedInStaff")){
			redirect(controller:"staff", action:"logout")
		} else {
			def selectedDate = Calendar.instance
			session.setAttribute("selectedDate", selectedDate)
			session.setAttribute("today", true)
		
			redirect(controller:"staff", action:"bookerDashboard")
		}
	}
	def pickDate(){
		if(!session.getAttribute("loggedInStaff")){
			redirect(controller:"staff", action:"logout")
		} else {
			def selectedDate = Calendar.instance
			def now = Calendar.instance
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
			try{
				Date date= format.parse(params.date)
				selectedDate.setTime(date)
				if(now.clearTime() == selectedDate.clearTime()){
					return today()
				} else {
					if(now.clearTime() > selectedDate.clearTime()){
						flash.dateMessage = "Error. Cannot select date in the past."
						redirect(controller:"staff", action:"bookerDashboard")
					} else {
						selectedDate.set(Calendar.HOUR, 0)
						session.setAttribute("today", false)
						session.setAttribute("selectedDate", selectedDate)
						redirect(controller:"staff", action:"bookerDashboard")
					}
				}
		
			} catch(java.text.ParseException e){
				flash.dateMessage = "Error. Date format must be 'dd/mm/yyyy'. Alternatively, use the datepicker."
				redirect(controller:"staff", action:"bookerDashboard")
			}
			
			
			
		}
		
	}
}
