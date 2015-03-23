package rotaGenerator;
import genetics.Genome;
import java.time.LocalDate;
import java.util.ArrayList;	import java.util.Map.Entry;
import dataObjects.Guide; import dataObjects.Guides; import dataObjects.Tour;

public class FortnightRota extends Genome {			
	public FortnightRota(){	/* Create Blank Week. */ }
	public FortnightRota(LocalDate fortnightBegins){		
	/* 		Pass in a date, it will read tours array, picking up 	*
	 * 		all tours for following fortnight and assign guides.	*/ 
		for(int week = 0; week < 2; week++) {
			LocalDate currentDate = fortnightBegins.plusWeeks(week);
			this.writeGene(currentDate, new WeekRota(currentDate));
		}
	}
	
	@Override public String toString() {
		return super.toString() 
			+ "***Fortnight Rota Beginning " + getGenome().firstKey().toString()
			+" Fitness : " + assessFitness() + "***\n";
	}
	
	/* GeneticInterface Methods */
	@Override public double assessFitness() {
		// For each week get highest and lowest scoring guide.
		// if week 1 lowest is week 2 highest (or vice versa) return 1.
		ArrayList<Guide> lowestScorers = new ArrayList<Guide>();
		ArrayList<Guide> highestScorers = new ArrayList<Guide>();
		
		// For each weekRota in genome
		for (Entry <Object, Object> entry: this.getGenes()){
			WeekRota week = (WeekRota) entry.getValue();
			
			double highestScore = 0.0;	Guide highestScorer = null;
			double lowestScore = 0.0;	Guide lowestScorer = null;
			
			// For each guide in Guides...
			for (Guide guide : Guides.guides) {
				double allocatedToursScore = 0;
				
				int weekdayMultiplier  = 1;	int tourTypeMultiplier = 1; 
				
				// ...and for each day in week... 
				for (Entry<Object, Object> day : week.getGenes()) { 
					DayRota valueDay = (DayRota) day.getValue();
					
					// ...tally allocated tours.
					for (Entry <Object, Object> rota : valueDay.getGenes()){
						Tour key = (Tour) rota.getKey();
						Guide val = (Guide) rota.getValue();
						if (val == guide) {
							switch (key.get_date().getDayOfWeek()) {
								case SATURDAY:	weekdayMultiplier = 7;	break;
								case FRIDAY:	weekdayMultiplier = 6;	break;
								case THURSDAY:	weekdayMultiplier = 5;	break;
								case SUNDAY:	weekdayMultiplier = 4;  break;
								case MONDAY:	weekdayMultiplier = 3;  break;
								case TUESDAY:	weekdayMultiplier = 2;	break;
								case WEDNESDAY:	weekdayMultiplier = 1;	break;
								default: weekdayMultiplier = 0; 		break;
							}
							
							if (key.get_type().equals("Double Dead")) {
								tourTypeMultiplier = 2;
							} else {
								tourTypeMultiplier = 1;
							} 
							allocatedToursScore = allocatedToursScore + 1 * (weekdayMultiplier * tourTypeMultiplier);
						} // end if match
					} // end of day
				}// end of week

				allocatedToursScore = allocatedToursScore * (7 / guide.get_available_days().size() );

				if (allocatedToursScore > highestScore) {
					highestScore = allocatedToursScore;
					highestScorer = guide;
				}
				if (allocatedToursScore < lowestScore || lowestScorer == null) {
					lowestScore = allocatedToursScore;
					lowestScorer = guide;
				}
			}
			lowestScorers.add(lowestScorer);
			highestScorers.add(highestScorer);
		}
		if ( lowestScorers.get(0).equals(highestScorers.get(1)) == true ) {
			return 1;
		}
		if ( lowestScorers.get(1).equals(highestScorers.get(0)) == true ) {
			return 1;
		} return 0;
	} // end assessFitness()
}