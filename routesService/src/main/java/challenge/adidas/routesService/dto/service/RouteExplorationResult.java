package challenge.adidas.routesService.dto.service;

import java.util.LinkedList;

import challenge.adidas.routesService.dto.response.RouteStep;

public class RouteExplorationResult {
	Long time;
	LinkedList<RouteStep> steps;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public LinkedList<RouteStep> getSteps() {
		return steps;
	}

	public void setSteps(LinkedList<RouteStep> steps) {
		this.steps = steps;
	}

}
