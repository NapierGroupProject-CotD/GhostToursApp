package ghost

import grails.transaction.Transactional

@Transactional
class StaffService {

    def serviceMethod() {}
	
	def mapOfRoles(){
		
		HashMap<Staff, ArrayList<Role>> mapOfRoles = new HashMap<Staff, ArrayList<Role>>()
		Staff.list().each{ staff->
			mapOfRoles.put(staff, staff.roles())
		}
		return mapOfRoles
	}
	
}// end StaffService
