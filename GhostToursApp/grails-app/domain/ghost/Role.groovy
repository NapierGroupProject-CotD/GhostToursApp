package ghost

class Role {
	String name
	String description
	
    static constraints = {
    }
	static mapping={
		table "role"
		version false
		
		id column:"role_id"
		name column:"name"
		description column:"description"
		
	}
}
