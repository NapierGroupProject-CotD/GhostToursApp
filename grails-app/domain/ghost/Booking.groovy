package ghost

class Booking {
	
	int tourId
	String custName
	int numberPeople
	int staffId
	
    static constraints = {
    }
	
	static mapping ={
		table "booking"
		version false
		
		id column:"booking_id"
		custName column:"cust_name"
		numberPeople column:"number_people"
	}
}
