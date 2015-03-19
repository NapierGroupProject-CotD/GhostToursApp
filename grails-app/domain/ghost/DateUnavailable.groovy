package ghost

class DateUnavailable {
	
	Date date
	Staff staff
	
	static belongsTo=[staff:Staff]
	
    static constraints = {
    }
	static mapping={
		version false
	}
}
