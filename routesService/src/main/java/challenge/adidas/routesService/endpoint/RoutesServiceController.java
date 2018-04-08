package challenge.adidas.routesService.endpoint;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import challenge.adidas.routesService.dto.response.Route;
import challenge.adidas.routesService.dto.service.RoutesService;
import challenge.adidas.routesService.endpoint.request.RouteCriteria;
import challenge.adidas.routesService.errors.RoutesServiceError;
import challenge.adidas.routesService.exceptions.BaseException;
import challenge.adidas.routesService.exceptions.ResourceNotFoundException;
import challenge.adidas.routesService.exceptions.RouteNotFoundException;

/**
 * service controller
 * 
 * @author joseam
 */
@RestController
public class RoutesServiceController {

	@Autowired
	RoutesService service;

	/**
	 * 
	 * @param from
	 * @param to
	 * @param criteria
	 * @return
	 * @throws IOException
	 * @throws BaseException
	 */
	@RequestMapping(value = "/routes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Route getCityLinks(@RequestParam(name = "from", required = true) String from,
			@RequestParam(name = "to", required = true) String to,
			@RequestParam(name = "criteria", required = true) RouteCriteria criteria) throws BaseException {
		return service.findRoute(from, to, criteria);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<RoutesServiceError> handleInternalServerError(Exception ex, WebRequest request) {
		ex.printStackTrace();
		RoutesServiceError error = new RoutesServiceError();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setMessage(ex.getMessage());
		error.setDate(LocalDate.now());
		return new ResponseEntity<RoutesServiceError>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<RoutesServiceError> handleResourceNotFoundError(ResourceNotFoundException ex,
			WebRequest request) {
		ex.printStackTrace();
		RoutesServiceError error = new RoutesServiceError();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(ex.getMessage());
		error.setDate(LocalDate.now());
		return new ResponseEntity<RoutesServiceError>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(RouteNotFoundException.class)
	protected ResponseEntity<RoutesServiceError> handleRouteNotFoundError(RouteNotFoundException ex,
			WebRequest request) {
		ex.printStackTrace();
		RoutesServiceError error = new RoutesServiceError();
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setMessage(ex.getMessage());
		error.setDate(LocalDate.now());
		return new ResponseEntity<RoutesServiceError>(error, HttpStatus.BAD_REQUEST);

	}

}
