package ghost

import grails.transaction.Transactional


class StaffController {

	def staffService //by using naming convention, it becomes a StaffService object
	def bookingService
	def hashingService
	
	def login(){
		if(session.getAttribute("loggedInStaff")){
			redirect(action:"chooseView")
		}
		
	}
	
	def logout(){
		session.invalidate()
		
		redirect(controller:"staff", action:"login")
	}
	
	def changePassword(){
		def staff = session.getAttribute("loggedInStaff")
	}
	def savePassword(){
		if(params.password != params.repeatPassword){
			flash.errorMessage = "Repeated password does not match!"
			redirect(action:"changePassword")
		} else {
			Staff staff = session.getAttribute("loggedInStaff")
			staff.password = hashingService.createHash(params.password)
			staff.save(flush:true)	
			redirect(action:"logout")
		}
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
					loggedInStaff.roles().each{
						if(it.name.equals("Manager")){
							session.setAttribute("isManager", true)
						}
						if(it.name.equals("Booker")){
							session.setAttribute("isBooker", true)
						}
						if(it.name.equals("Guide")){
							session.setAttribute("isGuide", true)
						}
					} 
					
					if(password.equals("changeme")){
						redirect(action:"changePassword")
						
					}else{
					
						ArrayList<Role> listOfRoles = loggedInStaff.roles().sort{it.id}
						if(Role.findByName("Manager") in listOfRoles){        // if the staff member has only one role, the appropriate method is called by the redirect to render the next view. 
							redirect(action:"managerDashboard")       
						} else {
							if(Role.findByName("Booker") in listOfRoles){
								redirect(action:"bookerDashboard")
							} else {
								if(Role.findByName("Guide") in listOfRoles){
									redirect(action:"guideDashboard")
								}
							}
						}
					
					}// end password changeme if else
					
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
			ArrayList<Role> listOfRoles = loggedInStaff.roles().sort{it.id}
			if(Role.findByName("Manager") in listOfRoles){        // if the staff member has only one role, the appropriate method is called by the redirect to render the next view. 
				redirect(action:"managerDashboard")       
			} else {
				if(Role.findByName("Booker") in listOfRoles){
					redirect(action:"bookerDashboard")
				} else {
					if(Role.findByName("Guide") in listOfRoles){
						redirect(action:"guideDashboard")
					}
				}
			}
		}
			
	}// end chooseView
	
	/*
	def changeView(){
		def roleId = params.role
		def chosenRole = Role.get(roleId.toInteger()).name
		
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
		
	}//end changeView */
	
	def managerDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		ArrayList<String> listOfRoles = loggedInStaff.roles()
		[staffName:loggedInStaff.name, listOfRoles:listOfRoles]
	}
	
	def guideDashboard(){
		
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		session.setAttribute("isManager", false)
		def availableGuideMap
		
		loggedInStaff.roles().each{ role ->
			if(role.name.equals("Manager")){
				session.setAttribute("isManager", true)
				availableGuideMap = staffService.getAvailableGuideMap()
			}
		}
		
		def startOfMonth = Calendar.instance
		startOfMonth.set(Calendar.DATE, 1)
		startOfMonth.clearTime()
		
		def endOfMonth = Calendar.instance
		endOfMonth.add(Calendar.MONTH, 1)
		endOfMonth.set(Calendar.DATE, 1)
		endOfMonth.clearTime()
		
		
		ArrayList<Calendar> dateList = new ArrayList<Calendar>()
		ArrayList<Tour> tourList = new ArrayList<Tour>()
		def tours = Tour.findAllByDatetimeBetween(startOfMonth.getTime(), endOfMonth.getTime())
		HashMap<Date, ArrayList<Tour>> tourMap = new HashMap<Date, ArrayList<Tour>>()
		Calendar tourCal
		
		startOfMonth.upto(endOfMonth) {
			
			tours.each{ tour->
				tourCal = Calendar.getInstance()
				tourCal.setTime(tour.datetime)
				
				if(tourCal.clearTime()==it.clearTime()){
					tourList.add(tour)
				}
			}
			
			tourMap.put(it.clone(), tourList.clone())
			tourList.clear()
			dateList.add(it.clone())
		}
		
		ArrayList<String> listOfRoles = loggedInStaff.roles()	
		[loggedInStaff:loggedInStaff, dateList:dateList, tourMap:tourMap, tours:tours, listOfRoles:listOfRoles, availableGuideMap:availableGuideMap]

	}
	
	def bookerDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		def selectedDate
		if(session.getAttribute("selectedDate") == null){
			selectedDate = Calendar.instance
			session.setAttribute("selectedDate", selectedDate)
			session.setAttribute("today", true)
		} else {
			selectedDate = session.getAttribute("selectedDate").clone()
		}
		
		Calendar start = selectedDate.clone() //this gets current date and time
		
		Calendar end = selectedDate.clone()
		end.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE)+1, 0, 0)
		
		//println "xxxxx"+start.format("yyyy-MM-dd HH:mm:ss")
		//println "xxxxx"+end.format("yyyy-MM-dd HH:mm:ss")
		
		def futureToursList = Tour.findAllByDatetimeBetween(start.getTime(), end.getTime())
		//session.setAttribute("today", true)
		
		ArrayList<String> listOfRoles = loggedInStaff.roles()
		
		[staffName:loggedInStaff.name, futureToursList:futureToursList, listOfRoles:listOfRoles]

	}
	
	def manageStaff() {
		def staffList = Staff.list()                  // Domain.list() is equivalent to SELECT * FROM table
		def mapOfRoles = staffService.mapOfRoles()  //service method call

		
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		ArrayList<String> listOfRoles = loggedInStaff.roles()
		
		[staffList:staffList, mapOfRoles:mapOfRoles, listOfRoles:listOfRoles]  // these are passed to manageStaff.gsp
	}
	
	def viewStaff() {
		println 'getting view for staff no. '+params.staffId
		def staffMember = Staff.get(params.staffId)
		[staffMember:staffMember]
	}
	
	def saveStaff(){
		boolean unique = true
		String errorMessage = null
		Staff.list().each{ 
			if(params.username == it.username){
				 errorMessage = "Username already exists!"
				unique = false
			}
			if(params.email == it.email){
				 errorMessage = "Email already exists!"
				unique = false
			}
			if(params.phone == it.phone){
				 errorMessage = "Phone number already exists!"
				unique = false
			}
		} 	
		
		
		if(unique){
			Staff newStaff = new Staff(params)
			newStaff.password = hashingService.createHash(params.password)
			newStaff.save(flush:true, failOnError:true)
			
			def role = params.role
			if(role instanceof String){
				StaffRole.link(newStaff, Role.get(role.toInteger()))
			} else {
				role.each{ tempRole->
					StaffRole.link(newStaff, Role.get(tempRole.toInteger()))
				}
			}	
		} else {
			flash.errorMessage = errorMessage
		}
		
		redirect(action:"manageStaff")		
	}
	
	def deleteStaff(){
		def staff= Staff.get(params.staffId.toInteger())
		
		
		staff.delete(flush:true, failOnError:true)
		
		redirect(action:"manageStaff")
	}
	
	def updateStaff(){
		println 'start update'
		Staff staff = Staff.get(params.staffId)
		staff.properties = params
		staff.save(flush:true, failOnError:true)
		
		redirect(action:"manageStaff")
	}
	
	def removeRole() {
		Staff staffMember = Staff.get(params.staffId)
		Role roleToRemove = Role.get(params.roleId)
		
		StaffRole.unlink(staffMember, roleToRemove)
		
		staffMember.save(flush:true, failOnError:true)
		redirect(action:"manageStaff")
	}
	
	def addRole() {
		boolean hasAlready = false
		Staff staffMember = Staff.get(params.staffId)
		Role newRole = Role.get(params.roleToAdd)
		
		staffMember.roles().each{
			if(newRole.equals(it)){
				hasAlready = true
			}
		}
		
		if(hasAlready){
			redirect(action:"manageStaff")
		} else {
			StaffRole.link(staffMember, newRole)
			staffMember.save(flush:true, failOnError:true)
			redirect(action:"manageStaff")
		}
		
	}
	
}
