package rotaGenerator;
import genetics.Genome;import genetics.Population;

import java.time.LocalDate; import java.util.ArrayList; import java.util.Map.Entry;

import dataObjects.Tour;	import dataObjects.Tours;
import dataObjects.Guide; 	import dataObjects.Guides;

public class DayRota extends Genome {
	private final int POPULATIONLIMIT = 20;
	private LocalDate date;
	
	public DayRota() { /* Blank Day Constructor */ }
	public DayRota(LocalDate aDate){
	/* 		Pass in a date, it will read tours array, picking up 	*
	 * 		all tours for following week and assign guides.			*/
		this.setDate(aDate);
		for(Tour temp : Tours.tours) {
			if (temp.get_date().equals(aDate)) {
				this.writeGene(temp, Guides.randomAvailableGuide(temp));
			}
		}
	}
	
	@Override
	public ArrayList<String> formattedOutput() {
		ArrayList<String> output = super.formattedOutput();
		String fitness = "Day Fitness : " + this.assessFitness();
		output.add(fitness);
		return output;
	}
	
	@Override public String toString() {
		return super.toString() + "*Day Fitness : " + assessFitness() + "*\n";
	}

	/* GeneticInterface Methods */
	@Override public double assessFitness() {
	/* As few, ideally zero, assignments of multiple tours to one guide. */
		double toursCount = this.genomeSize();
	
		double doubleBookings = 0;
		ArrayList<Guide> exclude = new ArrayList<Guide>();
			// For each guide in DayRota...
			for (Entry<Object, Object> outerLoop : this.getGenes()) {	
				Tour currentTour = (Tour) outerLoop.getKey();
				Guide currentGuide = (Guide) outerLoop.getValue();
	
				int doubleBooked = 0;					
				//...count allocated tours. Note multiple day bookings.
				for (Entry<Object, Object> innerLoop : this.getGenes()) {
					Tour comparatorTour = (Tour) innerLoop.getKey();
					Guide comparatorGuide = (Guide) innerLoop.getValue();
										
					if ( currentGuide.equals(comparatorGuide) == true
							&& currentTour.equals(comparatorTour) == false	
								&& exclude.contains(currentGuide)   == false	) {
						
						doubleBooked++;
						exclude.add(currentGuide);
					}
				}
				doubleBookings = doubleBookings + doubleBooked;
			}
			double fitness = 1 - (doubleBookings / toursCount);
			return fitness;
	}
	
	@Override public void crossover(Genome partner) {
		DayRota newRota = new DayRota();
		for(Entry<Object, Object> entry : partner.getGenes()){
			Tour key = (Tour) entry.getKey();
			Guide value = (Guide) entry.getValue();
				
			if (Math.random() <= GENESWAPLIKELIHOOD){
				newRota.writeGene(key, this.getGenome().get(key));
			} else {
				newRota.writeGene(key, value);
			}		
		}
		this.setGenome(newRota.getGenome()); 
	}
	
	@Override public void mutate() {
		for (Entry<Object, Object> entry : this.getGenes()) {
			Tour key = (Tour) entry.getKey();
			Guide newValue = Guides.randomAvailableGuide(key);
			if (Math.random() >= MUTATIONRATE){
				this.writeGene(key, newValue);
			}		
		}
	}
	
	@Override public boolean sanityCheck() { /* Three Hour Rule. */return true; }
		
	@Override public void evolve() {
		Population genPop = new Population();
		DayRota fittestNew = new DayRota();
				
		for(int i = 0; i < POPULATIONLIMIT; i++){
			genPop.add(new DayRota(this.date));
		}
		genPop.evolvePopulation();
		fittestNew = (DayRota) genPop.pollFittest();
		genPop.add(fittestNew);	
	
		this.setGenome( fittestNew.getGenome() );
	}
	/* Setters */
	public void setDate(LocalDate date) {
		this.date = date;
	}
}