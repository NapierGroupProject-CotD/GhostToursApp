package ghosttoursapp
import java.time.LocalDate
import dataObjects.Guides
import rotaGenerator.GivenPeriodRota
import db.DBConnect

class GeneratorController {

	DBConnect db = new DBConnect()
	Guides guides = new Guides()

    def optimise() { 
				
		db.readDataBase()
				
		GivenPeriodRota rota = new GivenPeriodRota(LocalDate.of(/*year*/2015, /*month*/3, /*day*/1), /*numberOfDays*/30)

		rota.evolve()
		
		db.write(rota)
	
		def response = rota.formattedOutput()
		[message:response]
	}
}