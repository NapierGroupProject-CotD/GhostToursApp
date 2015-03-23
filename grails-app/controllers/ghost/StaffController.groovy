package ghost

class StaffController {

	def staffService //by using naming convention, it becomes a StaffService object
	def bookingService
	def hashingService
	
	def login(){}
	
	def logout(){
		session.invalidate()
		
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
			
					ArrayList<Role> listOfRoles = loggedInStaff.roles()
			
					if(listOfRoles.size > 1){      // I thought that if a member has more than one role, we could have a page where he would select which view to access
						[listOfRoles:listOfRoles]  // the list of roles is passed to chooseView.gsp. reminder: the naming convention - if a view is not explicitly chosen, the method will look for one with the same name
				
					} else {
						if(listOfRoles[0].name.equals("Manager")){        // if the staff member has only one role, the appropriate method is called by the redirect to render the next view. 
							redirect(action:"managerDashboard")       
						} else {
							if(listOfRoles[0].name.equals("Guide")){
								redirect(action:"guideDashboard")
							} else {
								if(listOfRoles[0].name.equals("Booker")){
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
			ArrayList<String> listOfRoles = loggedInStaff.roles()
			[listOfRoles:listOfRoles]
		}
			
	}// end chooseView
	
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
		
	}//end changeView
	
	def managerDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		[staffName:loggedInStaff.name]
	}
	
	def guideDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		def startOfMonth = Calendar.instance
		startOfMonth.set(Calendar.DATE, 1)
		startOfMonth.clearTime()
		
		def endOfMonth = Calendar.instance
		endOfMonth.add(Calendar.MONTH, 1)
		endOfMonth.set(Calendar.DATE, 1)
		endOfMonth.clearTime()
		
		//println startOfMonth.format("dd-MMM-yy")
		//println endOfMonth.format("dd-MMM-yy")
		
		ArrayList<Calendar> dateList = new ArrayList<Calendar>()
		ArrayList<Tour> tourList = new ArrayList<Tour>()
		def tours = Tour.findAllByDatetimeBetween(startOfMonth.getTime(), endOfMonth.getTime())
		HashMap<Date, ArrayList<Tour>> tourMap = new HashMap<Date, ArrayList<Tour>>()
		Calendar tourCal
		
		startOfMonth.upto(endOfMonth) {
			
			tours.each{ tour->
				tourCal = Calendar.getInstance()
				tourCal.setTime(tour.datetime)
				//println it.format("dd-MM-yy")
				if(tourCal.clearTime()==it.clearTime()){
					tourList.add(tour)
				}
			}
			//println it.format("dd-MM-yy")
			//println tourList
			tourMap.put(it.clone(), tourList.clone())
			//println tourMap.get(it.getTime())
			tourList.clear()
			dateList.add(it.clone())
		}
		
		println tourMap.get(dateList[5])
		
		[loggedInStaff:loggedInStaff, dateList:dateList, tourMap:tourMap, tours:tours]
	}
	
	def bookerDashboard(){
		Staff loggedInStaff = session.getAttribute("loggedInStaff")
		def selectedDate
		if(session.getAttribute("selectedDate") == null){
			selectedDate = Calendar.instance
			session.setAttribute("selectedDate", selectedDate)
		} else {
			selectedDate = session.getAttribute("selectedDate").clone()
		}
		
		Calendar start = selectedDate.clone() //this gets current date and time
		
		Calendar end = selectedDate.clone()
		end.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DATE)+1, 0, 0)
		
		//println "xxxxx"+start.format("yyyy-MM-dd HH:mm:ss")
		//println "xxxxx"+end.format("yyyy-MM-dd HH:mm:ss")
		
		def futureToursList = Tour.findAllByDatetimeBetween(start.getTime(), end.getTime())
		session.setAttribute("today", true)
		
		[staffName:loggedInStaff.name, futureToursList:futureToursList]

	}
	
	def manageStaff() {
		def staffList = Staff.list()                  // Domain.list() is equivalent to SELECT * FROM table
		def mapOfRoles = staffService.mapOfRoles()  //service method call
		
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
		
		def role = params.role
		if(role instanceof String){
			StaffRole.link(newStaff, Role.get(role.toInteger()))
		} else {
			role.each{ tempRole->
				StaffRole.link(newStaff, Role.get(tempRole.toInteger()))
			}
		}
		
		
		
		redirect(action:"manageStaff")
	}
	
	def deleteStaff(){
		def staff= Staff.get(params.staffId.toInteger())
		
		
		staff.delete(flush:true, failOnError:true)
		
		redirect(action:"manageStaff")
	}
	
}
