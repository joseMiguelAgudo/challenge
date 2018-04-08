package challenge.adidas.api.city.endpoint;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import challenge.adidas.api.city.dto.response.City;
import challenge.adidas.api.city.dto.service.CityService;
import challenge.adidas.api.city.errors.CityApiError;
import challenge.adidas.api.city.exceptions.ResourceNotFoundException;

/**
 * Service endpoint
 * 
 * @author joseam
 *
 */

@RestController
public class CityController {

	@Autowired
	CityService cityService;

	/**
	 * returns a City with all its links based on the provided cityName
	 * 
	 * @param cityName
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{cityName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody City getCityLinks(@PathVariable("cityName") String cityName) throws ResourceNotFoundException {
		return cityService.getCity(cityName);
	}

	/**
	 * returns all the cities hosted in the database
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<City> getAll() {
		return cityService.getAll();
	}

	// ERROR HANDLING SECTION

	/**
	 * handles a situation of a resourceNotFound event, it is encapsulated into
	 * a CityApiError object with http-badrequest status
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleConflict(ResourceNotFoundException ex, WebRequest request) {
		CityApiError error = new CityApiError();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(ex.getMessage());
		error.setDate(LocalDate.now());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * the rest of exceptions will be treated here, they are encapsulated into a
	 * CityApiError object with http-internalservererror status
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {
		CityApiError error = new CityApiError();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setMessage(ex.getMessage());
		error.setDate(LocalDate.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}