package challenge.adidas.routesService.dto.response;

import java.util.LinkedList;

import challenge.adidas.routesService.endpoint.request.RouteCriteria;

/**
 * business object to describe a Route, composed by the departure, arrival and
 * the steps to be taken
 * 
 * @author Lenovo
 *
 */
public class Route {

	RouteCriteria criteria;

	String departureCity;

	String arrivalCity;

	LinkedList<RouteStep> steps;

	public RouteCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(RouteCriteria criteria) {
		this.criteria = criteria;
	}

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

	public LinkedList<RouteStep> getSteps() {
		return steps;
	}

	public void setSteps(LinkedList<RouteStep> steps) {
		this.steps = steps;
	}

}
