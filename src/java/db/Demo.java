package db;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import rotaGenerator.*;

public class Demo {
		
	dbConnect db = new dbConnect();

	public ArrayList<String> what() throws Exception {
		
		db.readDataBase();
		WeekRota week = new WeekRota(LocalDate.of(2015, Month.MARCH, 2));
		week.evolve();
		db.write(week);
	
		return week.formattedOutput();
	}
}