package ghost

class Booking {
	
	String custName
	int numberPeople
	Tour tour
	Staff staff
	
	static belongsTo=[tour:Tour]
	
    static constraints = {
    }
	static mapping={
		version false
	}
}
