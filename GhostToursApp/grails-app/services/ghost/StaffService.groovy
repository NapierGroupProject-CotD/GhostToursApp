package ghost

import grails.transaction.Transactional

@Transactional
class StaffService {

    def serviceMethod() {}
	
	def listAllRoles(){
		def mapOfRoles = new HashMap <Staff, ArrayList<String>>() //a map of lists, to be returned by method
		def staffRoleList 										  //temporary list of StaffRole objects (staff_role table rows)
		def roleList = new ArrayList<String>() 					  //temporary list of Role.name (String)
		
		Staff.list().each{ member-> //loops for every Staff object (staff table row)
			staffRoleList = StaffRole.findAllByStaffId(member.id) //SELECT * FROM staff_role WHERE staff_id=... 
			if (roleList!=null){
				roleList.clear()
			}
			
			staffRoleList.each{ staffRole ->
				roleList.add(Role.get(staffRole.roleId).name) //Role.get(id) returns Role by id primary key.
			}
			
			mapOfRoles.put(member, roleList.clone())       // ArrayList needs to be cloned if you plan on reusing in inside the method 
			
		}// end Staff.each

		return mapOfRoles
		
	} // end listOfRoles()
	
	def listOfRoles(Staff member){
		def listOfRoles =new ArrayList<String>()  //a list of roles, to be returned by method
		def staffRoleList 				          //temporary list of StaffRole objects (staff_role table rows)
		
		staffRoleList = StaffRole.findAllByStaffId(member.id) //SELECT * FROM staff_role WHERE staff_id=...
		
		staffRoleList.each{ staffRole ->
			listOfRoles.add(Role.get(staffRole.roleId).name) //Role.get(id) returns Role by id primary key.
		}
		
		return listOfRoles
		
	}// end listOfRoles(Staff)
	
}// end StaffService
