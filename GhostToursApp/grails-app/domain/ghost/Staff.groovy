package ghost

class Staff {
	String name
	String phone
	String email
	String username
	String password

	static hasMany =[availableDays : AvailableDay, 
				  datesUnavailable : DateUnavailable, 
					     prefTours : PrefTour, 
					         tours : Tour]
    static constraints = {
		
    }
	
	static mapping ={
		table "staff"
		version false
		
		id column:"staff_id"
		name column:"name"
		phone column:"phone"
		email column:"email"
		username column:"username"
		password column:"password"
	}
}
