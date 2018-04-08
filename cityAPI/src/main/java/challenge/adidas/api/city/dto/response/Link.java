package challenge.adidas.api.city.dto.response;

import java.time.LocalTime;

/**
 * 
 * Business object to represent a Link, it is formed by its destination,
 * departure and arrival
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
