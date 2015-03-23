package rotaGenerator;
import java.time.LocalDate;
import genetics.*;

public class MonthRota extends Genome{
	public MonthRota() { /* Blank MonthRota */ }
	public MonthRota( LocalDate monthBegins ) {

		int dayCount = 0;
		switch ( monthBegins.getMonth() ) {
			case SEPTEMBER:	dayCount = 30; // Thirty days has September...
			case APRIL:		dayCount = 30;
			case JUNE:		dayCount = 30;
			case NOVEMBER:	dayCount = 30;
			case FEBRUARY:	if ( monthBegins.isLeapYear() ) {	
								dayCount = 29;
							} else { dayCount = 28; }	
			default: dayCount = 31;
		}
		
		while ( dayCount > 14 ) {
			LocalDate currentDate = monthBegins.plusDays(dayCount);
			this.writeGene(monthBegins, new FortnightRota(currentDate));	
			dayCount = dayCount - 14; // #Days in a fortnight.
		}
		while ( dayCount > 7 ) { // Never actually used....
			LocalDate currentDate = monthBegins.plusDays(dayCount);
			this.writeGene(currentDate, new WeekRota(currentDate));	
			dayCount = dayCount - 7; // #Days in a week.
		}
		while ( dayCount > 0 ) {
			LocalDate currentDate = monthBegins.plusDays(dayCount);
			this.writeGene(currentDate, new DayRota(currentDate));	
			dayCount--;
		}	
	}
	@Override public String toString() {
		return super.toString() + "****Month Rota Beginning " + getGenome().firstKey().toString() 
				+ " Fitness " + assessFitness() + "****\n";
	}
}