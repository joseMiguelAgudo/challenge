package challenge.adidas.routesService.exceptions;

/**
 * requested city has not been found in the cityAPI
 * 
 * @author joseam
 *
 */
public class ResourceNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	final String resourceName;

	public ResourceNotFoundException(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public String getMessage() {
		return resourceName;
	}
}
