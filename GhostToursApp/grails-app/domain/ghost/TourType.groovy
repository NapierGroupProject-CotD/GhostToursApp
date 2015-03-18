package ghost

import java.sql.Time

class TourType {
	
	String typeName
	Time length
	double cost
	String location
	int spaces
	
	static hasMany = [tours : Tour, 
				  prefTours : PrefTour]
	
    static constraints = {
    }
	
	static mapping={
		table "tour_type"
		version false
		
		id column:"type_id"
		typeName column:"type_name"
		length column:"length"
		cost column:"cost"
		location column:"location"
		spaces column:"spaces"
	}
}
