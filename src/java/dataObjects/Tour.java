package dataObjects;
import java.time.LocalDate;
import java.time.LocalTime;

public class Tour implements Comparable<Tour> {
	private LocalDate _date;
	private LocalTime _time;
	private String _type;

	// Constructor
	public Tour(LocalDate _date, LocalTime _time, String _type) {
		set_date(_date);
		set_time(_time);
		set_type(_type);
	}

	// toString().
	@Override public String toString() {
		return this._date.getDayOfWeek().toString() + ", "
				+ this._date.toString() + ", " 	
				+ this._time.toString() + ", " 
				+ this._type + ". ";
		
//		return "Tour: " 	+ this._type 			+ ", "
//			+  "Date: " 	+ this._date.getDayOfWeek().toString() + ", " + this._date.toString() + ", "
//			+  "Time: "  	+ this._time.toString() + ". ";
	}

	// CompareTo(). Tours are sorted by Date, Time and Type.
	@Override public int compareTo(Tour testTour) {
		if (this._date.isBefore(testTour._date)) {
			return -1;
		}
		if (this._date.isAfter(testTour._date)) {
			return 1;
		}

		if (this._time.isBefore(testTour._time)) {
			return -1;
		}
		if (this._time.isAfter(testTour._time)) {
			return 1;
		}

		if (this._type.compareTo(testTour._type) < 0) {
			return 0;
		}
		if (this._type.compareTo(testTour._type) > 0) {
			return 0;
		}

		return 0; // Almost definitely an error. Error handler?
	}

	/* Getters */
	public LocalDate get_date() {
		return _date;
	}
	public LocalTime get_time() {
		return _time;
	}
	public String get_type() {
		return _type;
	}
	
	/* Setters */
	public void set_date(LocalDate _date) {
		this._date = _date;
	}

	public void set_time(LocalTime _time) {
		this._time = _time;
	}

	public void set_type(String _type) {
		this._type = _type;
	}
} // End Tour