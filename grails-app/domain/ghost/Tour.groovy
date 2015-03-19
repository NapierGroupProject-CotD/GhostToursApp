package ghost

class Tour {
	
	Date datetime
	TourType tourType
	Staff staff
	
	static hasMany = [bookings : Booking]
	static belongsTo = [tourType:TourType]
	
    static constraints = {
		staff nullable:true
    }
	static mapping={
		version false
	}
	
	public int getRemainingPlaces(){
		def places = this.tourType.spaces
		bookings.each{ booking ->
			places -= booking.numberPeople
		}
		return places
		
	}
}
