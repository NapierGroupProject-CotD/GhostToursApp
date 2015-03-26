package ghost

import grails.transaction.Transactional

@Transactional
class SchedulingService {

    def serviceMethod() {

    }
	
	def createTour(Calendar datetime, String typeName){
		def tourType = TourType.findByTypeName(typeName)
		
		def start = Calendar.instance
		def end = Calendar.instance
		
		start.set(datetime.get(Calendar.YEAR), datetime.get(Calendar.MONTH), datetime.get(Calendar.DATE), datetime.get(Calendar.HOUR), datetime.get(Calendar.MINUTE)-2)
		start.set(Calendar.HOUR, datetime.get(Calendar.HOUR))
		start.set(Calendar.AM_PM, Calendar.PM)
		
		end.set(datetime.get(Calendar.YEAR), datetime.get(Calendar.MONTH), datetime.get(Calendar.DATE), datetime.get(Calendar.HOUR), datetime.get(Calendar.MINUTE)+2)
		end.set(Calendar.HOUR, datetime.get(Calendar.HOUR))
		end.set(Calendar.AM_PM, Calendar.PM)
		
		def newTour = Tour.findByTourTypeAndDatetimeBetween(tourType, start.getTime(), end.getTime())
		if(newTour == null){
		
			newTour = new Tour()
			newTour.datetime= datetime.getTime()
			newTour.tourType = tourType
		
			newTour.save(flush:true, failOnError:true)
			println newTour.datetime.format("yyyy-MM-dd HH:mm:ss")
			
		}//end if
		
	}//end createTour
	
	
}//end class
