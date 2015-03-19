package ghost

import java.sql.Time

class TourType {
	
	String typeName
	Time length
	double cost
	String location
	int spaces
	
	static hasMany = [tours : Tour, preffTours:PrefTour]
	
    static constraints = {
    }
	static mapping={
		version false
	}
	
}
