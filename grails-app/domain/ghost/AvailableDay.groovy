package ghost

class AvailableDay {
	
	String day
	Staff staff
	
	static belongsTo=[staff:Staff]
	
    static constraints = {
    }
	
	static mapping={
		version false
	}
	
}
