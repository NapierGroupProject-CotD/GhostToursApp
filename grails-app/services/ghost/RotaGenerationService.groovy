package ghost
import java.time.LocalDate
import java.time.*
import dataObjects.Guides
import rotaGenerator.GivenPeriodRota
import db.DBConnect

import grails.transaction.Transactional

@Transactional
class RotaGenerationService {
	
	DBConnect db = new DBConnect()
	Guides guides = new Guides()
	
    def serviceMethod() {

    }
	
	def optimise(date, days) {
		
		db.readDataBase()
		
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		GivenPeriodRota rota = new GivenPeriodRota(localDate, days)

		rota.evolve()

		db.write(rota)

		def response = rota.formattedOutput()

	}//end optimise
}
