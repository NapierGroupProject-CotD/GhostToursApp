package genetics;

import java.util.TreeMap;

import dataObjects.Guide;
import dataObjects.Tour;

public interface Evolvable {
	
	public void 	crossover(Genome partner);
	public void 	mutate();
	public boolean 	sanityCheck();
	public double 	assessFitness();
	
	public TreeMap<Tour, Guide> getAll();
	
}
