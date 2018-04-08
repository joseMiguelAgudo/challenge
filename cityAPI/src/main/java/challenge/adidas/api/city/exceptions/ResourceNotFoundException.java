package challenge.adidas.api.city.exceptions;

public class ResourceNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String resourceName;
	
	final String RESOURCE_NOT_FOUND_EXCEPTION= "Could not find any resource by name ";
	
	public ResourceNotFoundException(String resourceName){
		this.resourceName=resourceName;
	}
	
	@Override
	public String getMessage() {
		return RESOURCE_NOT_FOUND_EXCEPTION+resourceName;
	}
} 
