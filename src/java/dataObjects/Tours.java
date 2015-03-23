package dataObjects;
import java.util.ArrayList;

public class Tours {
	public static ArrayList<Tour> tours = new ArrayList<Tour>();

	// toString
	@Override public String toString() {
		String output = "\n------\nTours\n------\n";
		for (Tour eachTour : this.getTours()) {
			output = output + eachTour.toString() + "\n";
		}
		return output;
	}

	/* Adders */
	// Add a Tour.
	public void add(Tour Tour) {
		this.getTours().add(Tour);
	}

	// Add many Tours from Tour Array.
	public void add(Tour[] ToursFromArray) {
		for (Tour eachTour : ToursFromArray) {
			this.getTours().add(eachTour);
		}
	}

	// Add Tours classes together.
	public void add(Tours ToursFromAToursClass) {
		this.getTours().addAll(getTours());
	}

	/* Getters */

	public ArrayList<Tour> getTours() {
		return tours;
	}

	public Tour getTour(int i) {
		int countItUp = 0;
		for (Tour eachTour : this.getTours()) {
			countItUp++;
			if (countItUp == i) {
				return eachTour;
			}
		}
		return null;
	}
} // End Tours.