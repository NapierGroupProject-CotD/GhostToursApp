package genetics;
import java.util.Map.Entry; import java.util.ArrayList;
import java.util.Set; import java.util.TreeMap;

public abstract class Genome implements Evolvable, Comparable<Genome> {
	public final double MUTATIONRATE = 0.01;
	public final double GENESWAPLIKELIHOOD 	= 	0.5		;

	private TreeMap<Object, Object> genome = new TreeMap<Object, Object>();

	public ArrayList<String> formattedOutput(){
		ArrayList<String> output = new ArrayList<String>();
		for ( Entry<Object, Object> entry : this.genome.entrySet() ){
			Object key 	= entry.getKey();	Object val	= entry.getValue();
			String outputLine = key.toString() + " : " +  val.toString();
			output.add(outputLine);
		} 	return output;
	}
	
	/* toString */
	@Override public String toString() {
		String output = "\n";
		for ( Entry<Object, Object> entry : this.genome.entrySet() ){
			Object key 	= entry.getKey();	Object val	= entry.getValue();
			output = output + key.toString() + "\n" + val.toString() + "\n";
		} 	return output;
	}
	
	/* compareTo(). Objects order themselves by 'fitness'*/
	@Override public int compareTo(Genome testGenome) {
		if ( this.assessFitness() > testGenome.assessFitness() ) {
			return 1;
		} else if ( this.assessFitness() < testGenome.assessFitness() ) {
			return -1;
		} else {	return -1;	}
	}
	
	/* Evolvable Methods */
	public double assessFitness() {
		double fitness = 0;
		
		for ( Entry<Object, Object> entry : this.getGenes() ){
			Genome val = (Genome) entry.getValue();			
			fitness = fitness + val.assessFitness();
		} 
		
		fitness = fitness / this.genomeSize();
		return fitness;
	}
	
	public void crossover(Genome partner) {
		System.out.println("No crossover method defined.");
	}
	
	public void evolve() {		
			for ( Entry<Object, Object> entry : this.getGenes() ){
				Object key = entry.getKey();
				Genome val = (Genome) entry.getValue();
				
				val.evolve();
				this.writeGene(key, val);
			}
	}
	
	public void mutate(){
		for (Entry<Object, Object> entry : this.getGenes()) {
			Object key = entry.getKey();
			Genome genome = (Genome)entry.getValue();
		
			if (Math.random() >= MUTATIONRATE){
				genome.mutate();
				this.writeGene(key, genome);
			}		
		}
	}
	
	public boolean sanityCheck() {
		return true;
	}
	
	/* Other */
	public int genomeSize() { // genomeSize()
		return this.genome.size();
	}
	
	public int genomeLength() { // genomeLength(). size - 1
		return this.genome.size() - 1 /* SIZELENGTHOFFSET*/;
	}
	
	public void writeGene(Object key, Object value){ // writeGene(key, gene)
		genome.put(key, value);
	}
	
	public Set<Entry<Object, Object>> getGenes() { // getGenes(). 
		//Return iterable EntrySet of genes from genome.
		return this.getGenome().entrySet();
	}
	
	/* TreeMap genome; Get & Set */
	public TreeMap<Object, Object> getGenome() {
		return this.genome;
	}
	public void setGenome(TreeMap<Object, Object> newGenome) {
		this.genome = newGenome;
	}
}