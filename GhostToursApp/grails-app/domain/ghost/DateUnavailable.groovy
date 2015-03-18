package ghost

class DateUnavailable {
	
	int guideId
	Date date
	
    static constraints = {
    }
	
	static mapping={
		table "date_unavailable"
		version false
		
		id column:"unavailable_id"
		guideId column:"guide_id"
		date column:"date"
	}
}
