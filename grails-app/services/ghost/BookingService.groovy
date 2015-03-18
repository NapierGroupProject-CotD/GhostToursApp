package ghost

import grails.transaction.Transactional

@Transactional
class BookingService {

    def serviceMethod() {

    }
	
	//this method returns a map of Tour keys and remaining places (int) values. we use the map to display remaining places in the list of upcoming tours on the booking view
	def getMapOfRemainingTourPlaces(){
		HashMap<Tour, Integer> mapOfRemainingTourPlaces = new HashMap<Tour, Integer>()
		
		def date = new Date()
		def futureToursList = Tour.findAllByDatetimeGreaterThan(date)  // we don't need remaining places for past tours so we use this
		
		def remainingTourPlaces
		futureToursList.each{ tour ->
			remainingTourPlaces = TourType.get(tour.typeId).spaces
			
			Booking.findAllByTourId(tour.id).each{ booking ->
				remainingTourPlaces -= booking.numberPeople
			}
			
			mapOfRemainingTourPlaces.put(tour, remainingTourPlaces)
		}//end tour each
		
		return mapOfRemainingTourPlaces
	}// end getMapOf...()
	
	// this method returns the remaining places for a single tour
	def getRemainingTourPlaces(tourId){
		def remainingTourPlaces = TourType.get(Tour.get(tourId).typeId).spaces
		Booking.findAllByTourId(tourId).each{ booking ->
			remainingTourPlaces -= booking.numberPeople
		}
		println "zzzzz" +remainingTourPlaces
		return remainingTourPlaces
	}
}
