package ghost

class Staff {
	String name
	String phone
	String email
	String username
	String password
	boolean isActive

	static hasMany =[availableDays : AvailableDay, 
				    datesUnavailable : DateUnavailable, 
					           tours : Tour,
							   staffRoles : StaffRole,
							   prefTours:PrefTour]
    static constraints = {
		
    }
	
	def roles() {
		return staffRoles.collect{it.role}
	}
	static mapping={
		version false
	}
	
}
