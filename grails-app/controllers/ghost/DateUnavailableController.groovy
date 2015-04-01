package ghost

import java.text.DateFormat
import java.text.SimpleDateFormat

class DateUnavailableController {

    def index() { }
	
	def showDatesUnavailable(){
		if(!session.getAttribute("loggedInStaff") || (!session.getAttribute("isGuide") && !session.getAttribute("isManager"))){
			redirect(controller:"staff", action:"logout")
		} else {
		
			Staff loggedInStaff = session.getAttribute("loggedInStaff")
			def now = new Date()
			def datesUnavailable = DateUnavailable.findAllByStaffAndDateGreaterThan(loggedInStaff, now)
		
			[datesUnavailable:datesUnavailable]
		}
	}
	
	def saveDateUnavailable(){
		if(!session.getAttribute("loggedInStaff") || (!session.getAttribute("isGuide") && !session.getAttribute("isManager"))){
			redirect(controller:"staff", action:"logout")
		} else {
		
			Staff loggedInStaff = session.getAttribute("loggedInStaff")
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
			try{
				Date date= format.parse(params.date)
				if(Tour.findByDatetimeGreaterThan(date)==null){
					def dateUnavailable = new DateUnavailable(date:date, staff:loggedInStaff)
					dateUnavailable.save(flush:true, failOnError:true)
					flash.message = "Date added."
				} else {
					flash.message = "Rota already generated for that day, contact manager to arrange cover."
				}
		
			} catch(java.text.ParseException e){
				flash.message = "Error. Date format must be 'dd/mm/yyyy'. Alternatively, use the datepicker."
			}
		
			redirect(action:"showDatesUnavailable")
		}
	}
	
	def deleteDateUnavailable(){
		if(!session.getAttribute("loggedInStaff") || (!session.getAttribute("isGuide") && !session.getAttribute("isManager"))){
			redirect(controller:"staff", action:"logout")
		} else {
		
			def dateUnavailable = DateUnavailable.get(params.dateUnavailableId)
			dateUnavailable.delete(flush:true)
			flash.message = "Date deleted."
		
			redirect(action:"showDatesUnavailable")
		}
	}
}
