package ghost

class StaffRole {
	int staffId
	int roleId
	
    static constraints = {
    }
	
	static mapping={
		table "staff_role"
		version false
		
		id column:"staff_role_id"
		staffId column:"staff_id"
		roleId column:"role_id"
	}
}
