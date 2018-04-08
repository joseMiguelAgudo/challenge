package challenge.adidas.routesService.exceptions;

/**
 * communication exception, may be the cityAPI is not reachable or is not
 * working properly
 * 
 * @author joseam
 *
 */

public class CityApiServerException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String error;

	public CityApiServerException(String error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		return error;
	}
}
