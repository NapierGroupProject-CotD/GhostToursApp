package genetics;

public interface Evolvable {
	
	public void 	crossover(Genome partner);
	public void 	mutate();
	public boolean 	sanityCheck();
	public double 	assessFitness();
	
}
