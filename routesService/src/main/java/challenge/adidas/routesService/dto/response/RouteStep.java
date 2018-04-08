package challenge.adidas.routesService.dto.response;

import java.time.LocalTime;

/**
 * business object to describe a RouteStep, composed by the departure, arrival
 * and the timeSpent
 * 
 * @author Lenovo
 *
 */
public class RouteStep {

	String departureCity;

	String arrivalCity;

	LocalTime timeSpent;

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public LocalTime getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(LocalTime timeSpent) {
		this.timeSpent = timeSpent;
	}

}
