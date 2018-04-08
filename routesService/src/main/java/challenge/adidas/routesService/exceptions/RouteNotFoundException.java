package challenge.adidas.routesService.exceptions;

/**
 * represents the exception of not being able to create a route to the specified
 * destiny
 * 
 * @author Lenovo
 *
 */
public class RouteNotFoundException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String from;
	final String to;

	final String ROUTE_NOT_FOUND_EXCEPTION = "Could not find any route from %s to %s";

	public RouteNotFoundException(String from, String to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String getMessage() {
		return String.format(ROUTE_NOT_FOUND_EXCEPTION, from, to);
	}
}
