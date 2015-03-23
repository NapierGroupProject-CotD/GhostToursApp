package dataObjects;

import java.util.ArrayList;
import java.util.Random;

public class Guides {
	public static ArrayList<Guide> guides = new ArrayList<Guide>();

	// toString(). Display details for each guide in Guides.
	@Override public String toString() {
		String output = ">>>Guides\n";
		for (Guide eachGuide : Guides.getGuides()) {
			output = output + eachGuide.toString();
		}
		return output;
	}
	
	public static Guide randomAvailableGuide(Tour currentTour) { // randomAvailableGuide(). 
		// Pick random available Guide from guides[]. Available not same as sanityChecked.
		Random generator = new Random();			
		int random = generator.nextInt(Guides.guides.size());
		random = random + 1;
		
			random = generator.nextInt(Guides.guides.size());
			random = random + 1;
			
			while (Guides.getGuide(random).isGuideAvailable(currentTour.get_date()) == false) {
				random = generator.nextInt(Guides.guides.size());
				random = random + 1;
			}
		return Guides.getGuide(random);
	}

	/* Adders */
	
	// Add a Guide.
	public void add(Guide guide) {
		Guides.getGuides().add(guide);
	}

	// Add many Guides from a Guide Array.
	public void add(Guide[] guidesFromArray) {
		for (Guide eachGuide : guidesFromArray) {
			Guides.getGuides().add(eachGuide);
		}
	}

	// Add two Guides class together.
	public void add(Guides guidesFromAGuidesClass) {
		Guides.getGuides().addAll(Guides.getGuides());
	}

	/* Getters */
	
	public static ArrayList<Guide> getGuides() {
		return guides;
	}

	public static Guide getGuide(int i) {
		int countItUp = 0;
		for (Guide eachGuide : Guides.getGuides()) {
			countItUp++;
			if (countItUp == i) {
				return eachGuide;
			}
		}
		return null;
	}
} // End Guides.