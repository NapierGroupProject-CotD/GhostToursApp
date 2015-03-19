package ghost

class StaffRole {
	Staff staff
	Role role
	
	static belongsTo=[staff:Staff, role:Role]
	static StaffRole link(Staff staff, Role role){
		def sr = StaffRole.findByStaffAndRole(staff, role)
		if(!sr){
			sr = new StaffRole()
			staff?.addToStaffRoles(sr)
			role?.addToStaffRoles(sr)
			sr.save()
		}
		return sr
	}
	
	static void unlink(staff, role) {
		def sr = StaffRole.findByStaffAndRole(staff, role)
		if (sr){
			staff?.removeFromStaffRoles(sr)
			role?.removeFromStaffRoles(sr)
			sr.delete()
		}
	}
	
    static constraints = {
    }
	static mapping={
		version false
	}
}
