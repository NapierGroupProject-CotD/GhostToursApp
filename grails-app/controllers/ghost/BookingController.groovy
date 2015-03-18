package ghost

class BookingController {
	def bookingService

    def index() { }
	
	def newBookingDetails(){
		def tourId = params.chosenTour
		def remainingTourPlaces = bookingService.getRemainingTourPlaces(tourId)
		println "zzzzz" +remainingTourPlaces
		
		
		[tourId:tourId, remainingTourPlaces:remainingTourPlaces]
	}
	
	def addBooking(){
		def booking = new Booking()
		Staff staff = session.getAttribute("loggedInStaff")
		
		booking.tourId = params.tourId.toInteger()
		booking.staffId = staff.id
		booking.custName = params.custName
		booking.numberPeople = params.numberPeople.toInteger() 
		booking.save(flush:true)
		
		redirect(controller:"staff", action:"bookerDashboard")
	}
}
