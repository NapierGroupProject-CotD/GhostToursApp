package ghost

class PrefTour {

	int guideId
	int typeId
	
    static constraints = {
    }
	
	static mapping={
		table "pref_tour"
		version false
		
		id column:"pref_id"
		guideId column:"guide_id"
		typeId column:"type_id"
	}
}
