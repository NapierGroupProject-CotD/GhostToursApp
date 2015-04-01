package ghost

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar.*

class TourController {

	def schedulingService
	
    def index() { }
	
	
	
	def generateTours(){
		Date startDate
		Date endDate
		boolean pass=true
		if(!session.getAttribute("loggedInStaff") || !session.getAttribute("isManager")){
			redirect(controller:"staff", action:"logout")
		} else {
			Staff loggedInStaff = session.getAttribute("loggedInStaff")
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
			try{
				startDate= format.parse(params.startDate)
				endDate= format.parse(params.endDate)
				
	
			} catch(java.text.ParseException e){
				flash.message = "Error. Date format must be 'dd/mm/yyyy'. Alternatively, use the datepicker."
				redirect(controller:"staff", action:"guideDashboard")
				pass = false
			}
			if(pass){
				def start = Calendar.instance
				def end = Calendar.instance
				def newTour
		
				start.setTime(startDate)
				end.setTime(endDate)
				end.add(Calendar.DATE, 1)
		
				start.upto(end) { tempDate ->
			
					tempDate.set(Calendar.HOUR, 3)
					tempDate.set(Calendar.MINUTE, 30)
					tempDate.set(Calendar.SECOND, 0)
					tempDate.set(Calendar.AM_PM, Calendar.PM)
					schedulingService.createTour(tempDate, "Underground")
				
					tempDate.set(Calendar.HOUR, 7)
					tempDate.set(Calendar.MINUTE, 30)
					tempDate.set(Calendar.SECOND, 0)
					tempDate.set(Calendar.AM_PM, Calendar.PM)
					schedulingService.createTour(tempDate, "Double Dead")
			
					tempDate.set(Calendar.HOUR, 8)
					tempDate.set(Calendar.MINUTE, 0)
					tempDate.set(Calendar.SECOND, 0)
					tempDate.set(Calendar.AM_PM, Calendar.PM)
					schedulingService.createTour(tempDate, "Underground")
			
					tempDate.set(Calendar.HOUR, 8)
					tempDate.set(Calendar.MINUTE, 30)
					tempDate.set(Calendar.SECOND, 0)
					tempDate.set(Calendar.AM_PM, Calendar.PM)
					schedulingService.createTour(tempDate, "Graveyard")
			
					tempDate.set(Calendar.HOUR, 9)
					tempDate.set(Calendar.MINUTE, 30)
					tempDate.set(Calendar.SECOND, 0)
					tempDate.set(Calendar.AM_PM, Calendar.PM)
					schedulingService.createTour(tempDate, "Double Dead")
			
				}// end upto loop
		
				redirect(controller:"staff", action:"guideDashboard")
			}
		}//end session validation
	}//end generateTours
	
	
	def setTourStaff(){
		if(!session.getAttribute("loggedInStaff") || !session.getAttribute("isManager")){
			redirect(controller:"staff", action:"logout")
		} else {
			def tour = Tour.get(params.tourId)
			tour.staff = Staff.get(params.staff)
			tour.save(flush:true)
			redirect(controller:"staff", action:"guideDashboard")
		}
	}
}
