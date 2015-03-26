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
	
	def getAvailableGuideMap(){
		def mondayList = [], tuesdayList = [], wednesdayList = [], thursdayList = [], fridayList = [], saturdayList = [], sundayList = []
		def role = Role.findByName("Guide")
		role.getAllStaff().each{ tempStaff->
				if(isAvailable(tempStaff, "Monday")){
					mondayList.add(tempStaff)
				}
				if(isAvailable(tempStaff, "Tuesday")){
					tuesdayList.add(tempStaff)
				}
				if(isAvailable(tempStaff, "Wednesday")){
					wednesdayList.add(tempStaff)
				}
				if(isAvailable(tempStaff, "Thursday")){
					thursdayList.add(tempStaff)
				}
				if(isAvailable(tempStaff, "Friday")){
					fridayList.add(tempStaff)
				}
				if(isAvailable(tempStaff, "Saturday")){
					saturdayList.add(tempStaff)
				}
				if(isAvailable(tempStaff, "Sunday")){
					sundayList.add(tempStaff)
				}
		}
		
		HashMap availableGuideMap = new HashMap()
		availableGuideMap.put(1, mondayList)
		availableGuideMap.put(2, tuesdayList)
		availableGuideMap.put(3, wednesdayList)
		availableGuideMap.put(4, thursdayList)
		availableGuideMap.put(5, fridayList)
		availableGuideMap.put(6, saturdayList)
		availableGuideMap.put(7, sundayList)
		println availableGuideMap
		
		return availableGuideMap
		
	}
	
	def isAvailable(Staff staff, String day){
		staff.availableDays.each{
			if(it.day.equals(day)){
				return false
			}
		}
		
		return true
	}
	
}// end StaffService
