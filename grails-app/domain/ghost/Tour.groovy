package ghost

class Tour {
	
	int guideId
	int typeId
	Date datetime
	
	static hasMany = [bookings : Booking]
	
    static constraints = {
    }
	
	static mapping={
		table "tour"
		version false
		
		id column:"tour_id"
		guideId column:"guide_id"
		typeId column:"type_id"
		datetime column:"datetime"
	}
}
