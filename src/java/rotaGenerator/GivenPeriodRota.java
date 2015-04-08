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
		LocalDate currentDate = periodBegins;
		System.out.println("currentDate: "+currentDate);
		System.out.println("dayCount: "+dayCount);
		while ( dayCount >= 7 ) {
			this.writeGene(currentDate, new WeekRota(currentDate));	
			currentDate = currentDate.plusDays(7);
			System.out.println("currentDate: "+currentDate);
			dayCount = dayCount - 7; // Days in a week.
		}
		while ( dayCount > 0 ) {
			this.writeGene(currentDate, new DayRota(currentDate));	
			currentDate = currentDate.plusDays(1);
			System.out.println("currentDate: "+currentDate);
			dayCount--;
		}	
	}
	
	public TreeMap<Tour, Guide> getAll(){
		TreeMap<Tour, Guide> forWriting = new TreeMap<Tour, Guide>();
		for (Entry<Object, Object> entry : getGenome().entrySet()){
			Genome o = (Genome) entry.getValue();
			forWriting.putAll(o.getAll());
		}
	return forWriting;
	}
	
	@Override public String toString() {
		return super.toString() + "****Given Period Rota Beginning " + getGenome().firstKey().toString() 
				+ " Fitness " + assessFitness() + "****\n";
	}
}