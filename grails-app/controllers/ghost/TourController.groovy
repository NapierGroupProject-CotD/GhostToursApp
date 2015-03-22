package ghost

import java.util.Calendar.*

class TourController {

	def schedulingService
	
    def index() { }
	
	
	
	def generateTours(){
		def start = Calendar.instance
		def end = Calendar.instance
		def newTour
		
		start.add(Calendar.MONTH, 1)
		start.set(Calendar.DATE, 1)
		
		end.add(Calendar.MONTH, 2)
		end.set(Calendar.DATE, 1)
		
		
		//println start.format("yyyy-MM-dd")
		//println end.format("yyyy-MM-dd")
		
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
			
			if(tempDate.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY){
				tempDate.set(Calendar.HOUR, 8)
				tempDate.set(Calendar.MINUTE, 0)
				tempDate.set(Calendar.SECOND, 0)
				tempDate.set(Calendar.AM_PM, Calendar.PM)
				schedulingService.createTour(tempDate, "Underground")
			}
			if(tempDate.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY){
				tempDate.set(Calendar.HOUR, 8)
				tempDate.set(Calendar.MINUTE, 30)
				tempDate.set(Calendar.SECOND, 0)
				tempDate.set(Calendar.AM_PM, Calendar.PM)
				schedulingService.createTour(tempDate, "Graveyard")
			}
			
			tempDate.set(Calendar.HOUR, 9)
			tempDate.set(Calendar.MINUTE, 30)
			tempDate.set(Calendar.SECOND, 0)
			tempDate.set(Calendar.AM_PM, Calendar.PM)
			schedulingService.createTour(tempDate, "Double Dead")
				
			
		}// end upto loop
	}//end generateTours
	
}