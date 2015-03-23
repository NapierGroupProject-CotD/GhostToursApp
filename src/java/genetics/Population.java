package genetics;
import java.util.Random;
import java.util.TreeSet;

public class Population {
	TreeSet<Genome> herd = new TreeSet<Genome>();
	
	private static final int SELECTIONSAMPLESIZE = 5;
	
	private final int LENGTHSIZEOFFSET = 1;
	
	public void evolvePopulation() { 
	
		Population evolved = new Population();
		
		// Survival of the fittest! Preserve most fit.
		Genome fittest = this.pollFittest();
		
		// Generate evolved population of identical length.
		for ( int i = 0; i < herd.size() - LENGTHSIZEOFFSET; i++ ) {
			
			// Tournament Selection. Breed! 
			Genome aGenome = this.selection();
			Genome partner = this.selection();		
	
			// Crossover.	
			aGenome.crossover(partner);
		
			do { // Mutate Individual. Do not break hard constraints.
				aGenome.mutate(); 
			} while ( aGenome.sanityCheck() != true );
					
			// Add offspring to next generation.
			evolved.add(aGenome);	
			
			// Return fittest to herd.
			evolved.add(fittest);
			setHerd( evolved.getHerd() ); 
		}
	}

	private Genome selection() {
		Population tournament = new Population();
		for (int i = 0; i < SELECTIONSAMPLESIZE; i++) {
			tournament.add(randomGenome());
		}
		return tournament.pollFittest();
	}
	
	public Genome randomGenome() {
		Genome randomGenome = null;
		Random generator = new Random();
		int random = generator.nextInt(herd.size() - LENGTHSIZEOFFSET);
		
		int i = 0;
		for	(Genome genome : getHerd() ){
			while ( i < random ) {
				i++;
			}
			randomGenome = genome;
		}
		return randomGenome;
	}

	public void add(Genome genome){
		herd.add(genome);
	}
	
	public Genome pollFittest() {
		Genome temp = getHerd().pollLast();
		return temp;
	}
	
	/* Getters */
	public TreeSet<Genome> getHerd() {
		return herd;
	}
	
	/* Setters */
	public void setHerd(TreeSet<Genome> genPop) {
		this.herd = genPop;
	}
} // End Population.