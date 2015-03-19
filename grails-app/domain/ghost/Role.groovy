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
}
