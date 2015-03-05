package ghost

class StaffController {
	//git test
	def staffService //by using naming convention, it becomes a StaffService object
	
	def manageStaff() {
		def staffList = Staff.list()                  // Domain.list() is equivalent to SELECT * FROM table
		def mapOfRoles = staffService.listAllRoles()  //service method call
		
		
		[staffList:staffList, mapOfRoles:mapOfRoles]  // these are passed to manageStaff.gsp
	}
	
	def view(){
		println params
		
		[temp:params.test]
	}
}
