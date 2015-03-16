package ghost

class Tour {
	
	int staffId
	int typeId
	Date datetime
	
	static hasMany = [bookings : Booking]
	
    static constraints = {
    }
	
	static mapping={
		table "tour"
		version false
		
		id column:"tour_id"
		staffId column:"staff_id"
		typeId column:"type_id"
		datetime column:"datetime"
	}
}
