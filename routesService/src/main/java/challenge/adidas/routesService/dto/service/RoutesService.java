package challenge.adidas.routesService.dto.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import challenge.adidas.api.city.dto.response.City;
import challenge.adidas.api.city.dto.response.Link;
import challenge.adidas.routesService.cityApiClient.CityApiClient;
import challenge.adidas.routesService.dto.response.Route;
import challenge.adidas.routesService.dto.response.RouteStep;
import challenge.adidas.routesService.endpoint.request.RouteCriteria;
import challenge.adidas.routesService.exceptions.BaseException;
import challenge.adidas.routesService.exceptions.RouteNotFoundException;

@Component
public class RoutesService {

	@Autowired
	CityApiClient cityApiClient;

	public Route findRoute(String from, String to, RouteCriteria criteria) throws BaseException {
		Route route;
		switch (criteria) {
		case QUICKEST:
			route = findQuickestRoute(from, to);
			break;
		case LESS_CONNECTIONS:
		default:
			route = findLessConnectionsRoute(from, to);
			break;
		}
		if (route.getSteps() == null || route.getSteps().size() == 0) {
			throw new RouteNotFoundException(from, to);
		}
		return route;

	}

	private Route findLessConnectionsRoute(String from, String to) throws BaseException {
		Route route = new Route();
		route.setCriteria(RouteCriteria.QUICKEST);
		route.setDepartureCity(from);
		route.setArrivalCity(to);
		RouteExplorationResult result = exploreLessConnectionLink(from, to, new LinkedList<RouteStep>(),
				new ArrayList<String>());

		route.setSteps(result.getSteps());
		return route;
	}

	private RouteExplorationResult exploreLessConnectionLink(String from, String to, LinkedList<RouteStep> steps,
			List<String> citiesAlreadyVisited) throws BaseException {
		City city = cityApiClient.getCity(from);
		citiesAlreadyVisited.add(from);

		if (from.equals("ZARAGOZA")) {
			System.out.println("BREAK");
		}

		LinkedList<RouteStep> bestRoute = null;

		for (Link link : city.getLinks()) {
			if (!citiesAlreadyVisited.contains(link.getDestination())) {
				RouteStep step = new RouteStep();
				step.setDepartureCity(from);
				step.setArrivalCity(link.getDestination());
				step.setTimeSpent(link.getArrival().minusNanos(link.getDeparture().toNanoOfDay()));
				LinkedList<RouteStep> stepsTaken = new LinkedList<>();
				stepsTaken.addAll(steps);
				stepsTaken.add(step);
				if (!link.getDestination().equals(to)) {
					RouteExplorationResult routeResult = exploreLessConnectionLink(link.getDestination(), to,
							stepsTaken, citiesAlreadyVisited);

					if (routeResult.getSteps() != null) {
						if ((bestRoute == null) || (bestRoute.size() > routeResult.getSteps().size())) {
							bestRoute = routeResult.getSteps();
						}
					}
				} else {
					RouteExplorationResult result = new RouteExplorationResult();
					result.setSteps(stepsTaken);
					return result;
				}
			}
		}
		RouteExplorationResult result = new RouteExplorationResult();
		result.setSteps(bestRoute);
		return result;
	}

	private Route findQuickestRoute(String from, String to) throws BaseException {
		Route route = new Route();
		route.setCriteria(RouteCriteria.QUICKEST);
		route.setDepartureCity(from);
		route.setArrivalCity(to);
		RouteExplorationResult result = exploreQuickestLink(from, to, new LinkedList<RouteStep>(), 0,
				new ArrayList<String>());

		route.setSteps(result.getSteps());
		return route;

	}

	private RouteExplorationResult exploreQuickestLink(final String from, final String to,
			final LinkedList<RouteStep> steps, final long alreadySpent, List<String> citiesAlreadyVisited)
			throws BaseException {

		City city = cityApiClient.getCity(from);
		citiesAlreadyVisited.add(from);

		Long bestTime = Long.MAX_VALUE;
		LinkedList<RouteStep> bestRoute = null;

		for (Link link : city.getLinks()) {
			if (!citiesAlreadyVisited.contains(link.getDestination())) {
				RouteStep step = new RouteStep();
				step.setDepartureCity(from);
				step.setArrivalCity(link.getDestination());
				step.setTimeSpent(link.getArrival().minusNanos(link.getDeparture().toNanoOfDay()));
				LinkedList<RouteStep> stepsTaken = new LinkedList<>();
				stepsTaken.addAll(steps);
				stepsTaken.add(step);
				if (!link.getDestination().equals(to)) {
					RouteExplorationResult routeResult = exploreQuickestLink(link.getDestination(), to, stepsTaken,
							alreadySpent + step.getTimeSpent().toNanoOfDay(), citiesAlreadyVisited);

					if (bestTime > routeResult.getTime()) {
						bestTime = routeResult.getTime();
						bestRoute = routeResult.getSteps();
					}
				} else {
					if (bestTime > step.getTimeSpent().toNanoOfDay()) {
						bestTime = (long) step.getTimeSpent().toNanoOfDay();
						bestRoute = stepsTaken;
					}
				}
			}
		}
		RouteExplorationResult result = new RouteExplorationResult();
		if (!bestTime.equals(Long.MAX_VALUE)) {
			result.setTime(bestTime + alreadySpent);
		} else {
			result.setTime(Long.MAX_VALUE);
		}
		result.setSteps(bestRoute);
		return result;
	}
}
