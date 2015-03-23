package ghosttoursapp

import dataObjects.Guides
import db.dbConnect
import java.time.LocalDate;
import java.time.Month;

import rotaGenerator.WeekRota;
import db.Demo

class GeneratorController {


    def optimise() { 
		Guides guides = new Guides();
		Demo demo = new Demo();
		
		ArrayList<String> respresp = new ArrayList<String>();
		for (Object o : demo.what()){
			respresp.add(o)
		}
		[message:respresp];		
	}
}