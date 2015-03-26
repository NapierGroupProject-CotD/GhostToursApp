package ghost

class Role {
	String name
	String description
	
	static hasMany=[staffRoles:StaffRole]
	
    static constraints = {
    }
	static mapping={
		version false
	}
	def getAllStaff() {
		return staffRoles.collect{it.staff}
	}
}
