package ghost

class BookingController {
	def bookingService

    def index() { }
	
	def newBookingDetails(){
		def tour = Tour.get(params.chosenTour.toInteger())
		def remainingTourPlaces = tour.getRemainingPlaces()

		[tour:tour, remainingTourPlaces:remainingTourPlaces]
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
}
