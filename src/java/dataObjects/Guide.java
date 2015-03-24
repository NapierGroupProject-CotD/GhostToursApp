package dataObjects;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Guide {
	private int _idi; // id index
	private String _id; // name
	private ArrayList<DayOfWeek> _available_days = new ArrayList<DayOfWeek>();		// Regular weekly availability.
	private ArrayList<LocalDate> _dates_unavailable = new ArrayList<LocalDate>();	// Holidays etc.

	/* Constructors */
	public Guide(String _id) {
		set_id(_id);
		_available_days.add(DayOfWeek.MONDAY);
		_available_days.add(DayOfWeek.TUESDAY);
		_available_days.add(DayOfWeek.WEDNESDAY);
		_available_days.add(DayOfWeek.THURSDAY);
		_available_days.add(DayOfWeek.FRIDAY);
		_available_days.add(DayOfWeek.SATURDAY);
		_available_days.add(DayOfWeek.SUNDAY);
	}
	
	public Guide(String _id, ArrayList<DayOfWeek> _available_days, int _idi){
		set_idi(_idi);
		set_id(_id);
		set_available_days(_available_days);
	}

	// toString().
	@Override public String toString() {
		return "Guide : " 			+ this._id 							+ ".\n" 
	/*		+  "Availability : " 	+ _available_days.toString() 		+ ".\n"
			+  "Dates Unavailable : "+ _dates_unavailable.toString()	+ ".\n"*/ ; 
	}

	// isGuideAvailable(). 
	public boolean isGuideAvailable(LocalDate date) {
		if (this._available_days.contains(date.getDayOfWeek())) {
			if (this._dates_unavailable.contains(date)) {
				return false;
			}
			return true;
		}
		return false;
	}

	/* Getters */
	public String get_id() {
		return _id;
	}
	public ArrayList<DayOfWeek> get_available_days() {
		return _available_days;
	}
	public int get_idi() {
		return _idi;
	}
	
	/* Setters */
	public void set_idi(int _idi) {
		this._idi = _idi;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public void set_available_days(ArrayList<DayOfWeek> _available_days) {
		this._available_days = _available_days;
	}
} // End Guide