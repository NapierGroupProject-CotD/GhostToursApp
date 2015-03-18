package ghost

class Day {
	
	String name
		
	static hasMany = [availableDays : AvailableDay]
	
    static constraints = {
    }
	
	static mapping={
		table "day"
		version false
		
		id column:"day_id"
		name column:"name"
	}
}
