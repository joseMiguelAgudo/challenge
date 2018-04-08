package challenge.adidas.api.city.dto.response;

import java.time.LocalTime;

/**
 * 
 * Describes the object Link returned by cityAPI service
 * 
 * @author joseam
 *
 */
public class Link {

	String destination;

	LocalTime departure;

	LocalTime arrival;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalTime departure) {
		this.departure = departure;
	}

	public LocalTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalTime arrival) {
		this.arrival = arrival;
	}

}
