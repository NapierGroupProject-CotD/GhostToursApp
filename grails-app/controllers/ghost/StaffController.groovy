package ghost

class StaffController {

	def staffService //by using naming convention, it becomes a StaffService object
	def bookingService
	def hashingService
	
	def login(){}
	
	def logout(){
		session.setAttribute("loggedInStaff", null)
		redirect(controller:"main", action:"index")
	}
	
	def chooseView(){
		Staff loggedInStaff
		if(session.getAttribute("loggedInStaff") == null){
			
			def username = params.username  //reminder: params is a map passed by the form. username and password are the keys, given by the name attributes in the <input> tags.   
			def password = params.password  //          the values are whatever was entered in the text fields.
		
			if(Staff.findByUsername(username) != null){
				
				def correctHash = Staff.findByUsername(username).password
				if(hashingService.validatePassword(password, correctHash)){
					loggedInStaff = Staff.findByUsername(username)
			
					//if login is correct, we put the Staff object in session so we can use it anywhere we want (controllers, gsps)
					session.setAttribute("loggedInStaff", loggedInStaff) 
			
					ArrayList<String> listOfRoles = staffService.listOfRoles(loggedInStaff)
			
					if(listOfRoles.size > 1){      // I thought that if a member has more than one role, we could have a page where he would select which view to access
						[listOfRoles:listOfRoles]  // the list of roles is passed to chooseView.gsp. reminder: the naming convention - if a view is not explicitly chosen, the method will look for one with the same name
				
					} else {
						if(listOfRoles[0].equals("Manager")){        // if the staff member has only one role, the appropriate method is called by the redirect to render the next view. 
							redirect(action:"managerDashboard")       
						} else {
							if(listOfRoles[0].equals("Guide")){
								redirect(action:"guideDashboard")
							} else {
								if(listOfRoles[0].equals("Booker")){
									redirect(action:"bookerDashboard")
								}
							}
						}
					}// end listOfRoles.size if else
					
				} else {
					flash.message = "Username/Password invalid. try again!"
					redirect(action:"login")
				} // end if else for password validation
			
			} else {
				flash.message = "Username/Password invalid. try again!" // flash is another map. this is accessible from the views. like a reverse params as I understand it, very useful for creating error messages
																		  // message is the key in this case, and the string is the value
				redirect(action:"login") // authentication fails, so back to login								  	
			} //end staff != null if else
		
		} else {
			loggedInStaff = session.getAttribute("loggedInStaff")
			ArrayList<String> listOfRoles = staffService.listOfRoles(loggedInStaff)
			[listOfRoles:listOfRoles]
		}
			
	}// end chooseView
	
	def changeView(){
		def chosenRole = params.role
		
		if(chosenRole.equals("Manager")){    
			redirect(action:"managerDashboard")       
		} else {
			if(chosenRole.equals("Guide")){
				redirect(action:"guideDashboard")
			} else {
				if(chosenRole.equals("Booker")){
								redirect(action:"bookerDashboard")
							}
			}
		} // end if else
		
	}//end changeView
	
	def managerDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		[staffName:loggedInStaff.name]
	}
	
	def guideDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		[staffName:loggedInStaff.name]
	}
	
	def bookerDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		def tourList = Tour.list()
		def mapOfRemainingTourPlaces = bookingService.getMapOfRemainingTourPlaces()
		
		[staffName:loggedInStaff.name, mapOfPlaces:mapOfRemainingTourPlaces]
	}
	
	def manageStaff() {
		def staffList = Staff.list()                  // Domain.list() is equivalent to SELECT * FROM table
		def mapOfRoles = staffService.listAllRoles()  //service method call
		
		[staffList:staffList, mapOfRoles:mapOfRoles]  // these are passed to manageStaff.gsp
	}
	
	def saveStaff(){
		Staff newStaff = new Staff()
		newStaff.name= params.name
		newStaff.phone = params.phone
		newStaff.email = params.email
		newStaff.username = params.username
		newStaff.password = hashingService.createHash(params.password)
		newStaff.save(flush:true, failOnError:true)	
		
		redirect(action:"managerDashboard")
	}
	
}
