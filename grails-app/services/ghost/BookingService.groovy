package ghost

import grails.transaction.Transactional

@Transactional
class BookingService {

    def serviceMethod() {

    }
	
	def getMapOfRemainingTourPlaces(){
		HashMap<Tour, Integer> mapOfRemainingTourPlaces = new HashMap<Tour, Integer>()
		def remainingTourPlaces
		
		Tour.list().each{ tour ->
			remainingTourPlaces = TourType.get(tour.typeId).spaces
			
			Booking.findAllByTourId(tour.id).each{ booking ->
				remainingTourPlaces -= booking.numberPeople
			}
			
			mapOfRemainingTourPlaces.put(tour, remainingTourPlaces)
		}//end tour each
		
		return mapOfRemainingTourPlaces
	}// end getMapOf...()
	
	def getRemainingTourPlaces(tourId){
		def remainingTourPlaces = TourType.get(Tour.get(tourId).typeId).spaces
		Booking.findAllByTourId(tourId).each{ booking ->
			remainingTourPlaces -= booking.numberPeople
		}
		println "zzzzz" +remainingTourPlaces
		return remainingTourPlaces
	}
}
