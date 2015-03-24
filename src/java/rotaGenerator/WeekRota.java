package rotaGenerator;
import genetics.Genome;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map.Entry;

import dataObjects.Guide;	import dataObjects.Guides;

public class WeekRota extends Genome {		
	public WeekRota(){ /* Create Blank Week. */	}
	public WeekRota(LocalDate weekBegins){
	/* 		Pass in a date, it will read tours array, picking up 	* 		
	 * 		all tours for following week and assign guides.		 	*/		
		for(int weekday = 0; weekday < 7; weekday++) {
			LocalDate currentDate = weekBegins.plusDays(weekday);
			this.writeGene(currentDate, new DayRota(currentDate));
		}
	}
	
	@Override public ArrayList<String> formattedOutput() {
		ArrayList<String> output = new ArrayList<String>();
		
		for( Entry<Object, Object> day : getGenes() ){
			output.addAll(((Genome) day.getValue()).formattedOutput());
		}
		String fitness = "Week Fitness : " + this.assessFitness();
		output.add(fitness);
		return output;
	}
	
	@Override public String toString() {
		return super.toString() 
				+ "**Week Rota Beginning " + this.getGenome().firstKey().toString()
				+ " Fitness : " + assessFitness() + "**\n" ;
	}
	
	/* GeneticInterface Methods */
	@Override public void evolve() {
		int i = 0;
		do {
			super.evolve();
			i++;
		} while ( i < 1000 /*assessFitness() < 0.6 */);
	}
	
	@Override public double assessFitness() { 
		return (/*super.assessFitness() + */fairness() )/* / 2*/  ;
		/* Dividing by two in order to take average of fitness measurements */
		}
	
	private double fairness() { /* Are assignments shared evenly? */
		double unfairness = 0;
		// For each guide in Guides...
		for (Guide guide : Guides.guides) {
			int allocatedTours = 0;
			int tourCount = 0;
			// ...and for each day... 
			for (Entry<Object, Object> day : this.getGenes()) {
				DayRota valueDay = (DayRota) day.getValue();
				// ...tally allocated tours.
				for (Entry <Object, Object> rota : valueDay.getGenes()){
					Guide valueGuide = (Guide) rota.getValue();
					if (valueGuide == guide) {
						allocatedTours++;	
					}
					tourCount++; // Tally total number of tours.
				}
				// Weighting by availability. Do I want this inverse?
				allocatedTours = allocatedTours * ( 7 / guide.get_available_days().size() );
			}
			double averageAllocations = tourCount / Guides.guides.size();
			unfairness = unfairness + (Math.abs(allocatedTours - averageAllocations) / tourCount);
		}
		return 1 - unfairness;
	}
}