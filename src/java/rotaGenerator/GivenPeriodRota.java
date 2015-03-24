package rotaGenerator;
import java.time.LocalDate;
import java.util.TreeMap;
import java.util.Map.Entry;

import dataObjects.Guide;
import dataObjects.Tour;
import genetics.*;

public class GivenPeriodRota extends Genome{
	public GivenPeriodRota( LocalDate periodBegins, int numberOfDays ) {
		
		int dayCount = numberOfDays;

		while ( dayCount > 7 ) { // Never actually used....
			LocalDate currentDate = periodBegins.plusDays(dayCount);
			this.writeGene(currentDate, new WeekRota(currentDate));	
			dayCount = dayCount - 7; // #Days in a week.
		}
		while ( dayCount > 0 ) {
			LocalDate currentDate = periodBegins.plusDays(dayCount);
			this.writeGene(currentDate, new DayRota(currentDate));	
			dayCount--;
		}	
	}
	
	public TreeMap<Tour, Guide> getAll(){
		TreeMap<Tour, Guide> forWriting = new TreeMap<Tour, Guide>();
		for (Entry<Object, Object> entry : getGenome().entrySet()){
			DayRota day = (DayRota) entry.getValue();
			forWriting.putAll(day.getAll());
		}
	return forWriting;
	}
	
	@Override public String toString() {
		return super.toString() + "****Given Period Rota Beginning " + getGenome().firstKey().toString() 
				+ " Fitness " + assessFitness() + "****\n";
	}
}