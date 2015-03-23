package db;
//import java.time.DayOfWeek;
import genetics.Genome;

import java.time.LocalDate;
//import java.time.LocalTime;
import java.time.Month;
//import java.util.ArrayList;


import java.util.ArrayList;
import java.util.Map.Entry;


import java.util.TreeMap;


//import dataObjects.*;
import rotaGenerator.*;

public class Demo {
		
	dbConnect db = new dbConnect();

	public ArrayList<String> what() /*throws Exception*/ {
		try {
			db.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WeekRota week = new WeekRota(LocalDate.of(2016, Month.APRIL, 18));
		
	//	System.out.println(week.toString());
		
		week.evolve();
		
	//	System.out.println(week.toString());
		System.out.println(week.formattedOutput());

		return week.formattedOutput();
	}
	
	public static void main(String[] args) throws Exception {
		
		dbConnect db = new dbConnect();
		db.readDataBase();

		
/*		ArrayList<DayOfWeek> fred_available_days = new ArrayList<DayOfWeek>();
		fred_available_days.add(DayOfWeek.MONDAY);
		fred_available_days.add(DayOfWeek.WEDNESDAY);
		fred_available_days.add(DayOfWeek.FRIDAY);
		fred_available_days.add(DayOfWeek.SATURDAY);

		ArrayList<DayOfWeek> george_available_days = new ArrayList<DayOfWeek>();
		george_available_days.add(DayOfWeek.TUESDAY);
		george_available_days.add(DayOfWeek.THURSDAY);
		george_available_days.add(DayOfWeek.FRIDAY);
		george_available_days.add(DayOfWeek.SATURDAY);

		ArrayList<DayOfWeek> ron_available_days = new ArrayList<DayOfWeek>();
		ron_available_days.add(DayOfWeek.MONDAY);
		ron_available_days.add(DayOfWeek.WEDNESDAY);
		ron_available_days.add(DayOfWeek.SUNDAY);

		ArrayList<DayOfWeek> bill_available_days = new ArrayList<DayOfWeek>();
		bill_available_days.add(DayOfWeek.FRIDAY);
		bill_available_days.add(DayOfWeek.SATURDAY);
		bill_available_days.add(DayOfWeek.SUNDAY);

		ArrayList<DayOfWeek> charlie_available_days = new ArrayList<DayOfWeek>();
		charlie_available_days.add(DayOfWeek.FRIDAY);
		charlie_available_days.add(DayOfWeek.SATURDAY);

		ArrayList<DayOfWeek> ginny_available_days = new ArrayList<DayOfWeek>();
		ginny_available_days.add(DayOfWeek.THURSDAY);		
		ginny_available_days.add(DayOfWeek.SUNDAY);

		ArrayList<DayOfWeek> percy_available_days = new ArrayList<DayOfWeek>();
		percy_available_days.add(DayOfWeek.SATURDAY);
		
		Guide[] guides = new Guide[] {
				
			new Guide("Fred", fred_available_days),
			new Guide("George", george_available_days),
			new Guide("Ron", ron_available_days),
			new Guide("Bill", bill_available_days),
			new Guide("Charlie", charlie_available_days),
			new Guide("Ginny", ginny_available_days), 
			new Guide("Percy", percy_available_days)	, 	
			new Guide("Molly"), // No _available_days specified - Available all days.
			new Guide("Arthur"),
			new Guide("Errol"),	

			new Guide("Carla"), 	
			new Guide("Sam"),
			new Guide("Norm"),
			new Guide("Cliff"),
			new Guide("Frasier"),
			new Guide("Coach"),  
			new Guide("Woody"),	 
			new Guide("Diane"), 
			new Guide("Rebecca"),
			new Guide("Lillith")
		};
		
		Tour[] tours = new Tour[] {
				
			// Monday
			new Tour(LocalDate.of(2016, Month.APRIL, 18), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 18), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 18), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 18), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 18), LocalTime.of(21, 30), "Double Dead"),
			
			//Tuesday
			new Tour(LocalDate.of(2016, Month.APRIL, 19), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 19), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 19), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 19), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 19), LocalTime.of(21, 30), "Double Dead"),
			
			//Wednesday
			new Tour(LocalDate.of(2016, Month.APRIL, 20), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 20), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 20), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 20), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 20), LocalTime.of(21, 30), "Double Dead"),
			
			//Thursday
			new Tour(LocalDate.of(2016, Month.APRIL, 21), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 21), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 21), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 21), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 21), LocalTime.of(21, 30), "Double Dead"),
			
			//Friday
			new Tour(LocalDate.of(2016, Month.APRIL, 22), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 22), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 22), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 22), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 22), LocalTime.of(21, 30), "Double Dead"),
			
			// Saturday
			new Tour(LocalDate.of(2016, Month.APRIL, 23), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 23), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 23), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 23), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 23), LocalTime.of(21, 30), "Double Dead"),
			
			// Sunday
			new Tour(LocalDate.of(2016, Month.APRIL, 24), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 24), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 24), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 24), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 24), LocalTime.of(21, 30), "Double Dead"), 
			
			// Monday
			new Tour(LocalDate.of(2016, Month.APRIL, 25), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 25), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 25), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 25), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 25), LocalTime.of(21, 30), "Double Dead"),
			
			//Tuesday
			new Tour(LocalDate.of(2016, Month.APRIL, 26), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 26), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 26), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 26), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 26), LocalTime.of(21, 30), "Double Dead"),
			
			//Wednesday
			new Tour(LocalDate.of(2016, Month.APRIL, 27), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 27), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 27), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 27), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 27), LocalTime.of(21, 30), "Double Dead"),
			
			//Thursday
			new Tour(LocalDate.of(2016, Month.APRIL, 28), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 28), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 28), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 28), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 28), LocalTime.of(21, 30), "Double Dead"),
			
			//Friday
			new Tour(LocalDate.of(2016, Month.APRIL, 29), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 29), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 29), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 29), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 29), LocalTime.of(21, 30), "Double Dead"),
			
			// Saturday
			new Tour(LocalDate.of(2016, Month.APRIL, 30), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 30), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.APRIL, 30), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.APRIL, 30), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.APRIL, 30), LocalTime.of(21, 30), "Double Dead"),
			
			// Sunday
			new Tour(LocalDate.of(2016, Month.MAY, 1), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 1), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 1), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 1), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 1), LocalTime.of(21, 30), "Double Dead"), 
			
			
			// Monday
			new Tour(LocalDate.of(2016, Month.MAY, 2), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 2), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 2), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 2), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 2), LocalTime.of(21, 30), "Double Dead"),
			
			//Tuesday
			new Tour(LocalDate.of(2016, Month.MAY, 3), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 3), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 3), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 3), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 3), LocalTime.of(21, 30), "Double Dead"),
			
			//Wednesday
			new Tour(LocalDate.of(2016, Month.MAY, 4), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 4), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 4), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 4), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 4), LocalTime.of(21, 30), "Double Dead"),
			
			//Thursday
			new Tour(LocalDate.of(2016, Month.MAY, 5), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 5), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 5), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 5), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 5), LocalTime.of(21, 30), "Double Dead"),
			
			//Friday
			new Tour(LocalDate.of(2016, Month.MAY, 6), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 6), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 6), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 6), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 6), LocalTime.of(21, 30), "Double Dead"),
			
			// Saturday
			new Tour(LocalDate.of(2016, Month.MAY, 7), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 7), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 7), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 7), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 7), LocalTime.of(21, 30), "Double Dead"),
			
			// Sunday
			new Tour(LocalDate.of(2016, Month.MAY, 8), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 8), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 8), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 8), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 8), LocalTime.of(21, 30), "Double Dead"),
					
			// Monday
			new Tour(LocalDate.of(2016, Month.MAY, 9), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 9), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 9), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 9), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 9), LocalTime.of(21, 30), "Double Dead"),
			
			//Tuesday
			new Tour(LocalDate.of(2016, Month.MAY, 10), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 10), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 10), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 10), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 10), LocalTime.of(21, 30), "Double Dead"),
			
			//Wednesday
			new Tour(LocalDate.of(2016, Month.MAY, 11), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 11), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 11), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 11), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 11), LocalTime.of(21, 30), "Double Dead"),
			
			//Thursday
			new Tour(LocalDate.of(2016, Month.MAY, 12), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 12), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 12), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 12), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 12), LocalTime.of(21, 30), "Double Dead"),
			
			//Friday
			new Tour(LocalDate.of(2016, Month.MAY, 13), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 13), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 13), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 13), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 13), LocalTime.of(21, 30), "Double Dead"),
			
			// Saturday
			new Tour(LocalDate.of(2016, Month.MAY, 14), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 14), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 14), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 14), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 14), LocalTime.of(21, 30), "Double Dead"),
			
			// Sunday
			new Tour(LocalDate.of(2016, Month.MAY, 15), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 15), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 15), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 15), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 15), LocalTime.of(21, 30), "Double Dead"),
			
			
			// Monday
			new Tour(LocalDate.of(2016, Month.MAY, 16), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 16), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 16), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 16), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 16), LocalTime.of(21, 30), "Double Dead"),
			
			//Tuesday
			new Tour(LocalDate.of(2016, Month.MAY, 17), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 17), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 17), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 17), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 17), LocalTime.of(21, 30), "Double Dead"),
			
			//Wednesday
			new Tour(LocalDate.of(2016, Month.MAY, 18), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 18), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 18), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 18), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 18), LocalTime.of(21, 30), "Double Dead"),
			
			//Thursday
			new Tour(LocalDate.of(2016, Month.MAY, 19), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 19), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 19), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 19), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 19), LocalTime.of(21, 30), "Double Dead"),
			
			//Friday
			new Tour(LocalDate.of(2016, Month.MAY, 20), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 20), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 20), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 20), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 20), LocalTime.of(21, 30), "Double Dead"),
			
			// Saturday
			new Tour(LocalDate.of(2016, Month.MAY, 21), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 21), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 21), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 21), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 21), LocalTime.of(21, 30), "Double Dead"),
			
			// Sunday
			new Tour(LocalDate.of(2016, Month.MAY, 22), LocalTime.of(15, 30), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 22), LocalTime.of(19, 30), "Double Dead"),
			new Tour(LocalDate.of(2016, Month.MAY, 22), LocalTime.of(20, 00), "Underground"),
			new Tour(LocalDate.of(2016, Month.MAY, 22), LocalTime.of(20, 30), "Graveyard  "),
			new Tour(LocalDate.of(2016, Month.MAY, 22), LocalTime.of(21, 30), "Double Dead")
			
		}; 

		Guides guidesClass = new Guides();
		guidesClass.add(guides);
		
		Tours toursClass = new Tours();
		toursClass.add(tours);		*/
		
		//DayRota Test
/*		DayRota day = new DayRota(LocalDate.of(2016, Month.APRIL, 18));
		
		System.out.println(day.toString());
		
		day.evolve();
		
		System.out.println(day.toString());		*/	
		
		//WeekRota Test
		WeekRota week = new WeekRota(LocalDate.of(2016, Month.APRIL, 18));
		
		System.out.println(week.toString());
		
		week.evolve();
		
		System.out.println(week.toString());	
		
		//FortnightRota Test.
/*		FortnightRota fortnight = new FortnightRota(LocalDate.of(2016, Month.APRIL, 18));
		
		System.out.println(fortnight.toString());
		
		fortnight.evolve();
		
		System.out.println(fortnight.toString());	
		
		//MonthRota Test.
		MonthRota month = new MonthRota(LocalDate.of(2016, Month.APRIL, 18));
		
		System.out.println(month.toString());
		
		month.evolve();
		
		System.out.println(month.toString());	*/
	}
}
