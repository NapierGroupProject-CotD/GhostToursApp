package ghost

class AvailableDay {
	
	int guideId
	int dayId
	
    static constraints = {
    }
	
	static mapping ={
		table "available_day"
		version false
		
		id column:"available_id"
		guideId column:"guide_id"
		dayId column:"day_id"
	}
}
