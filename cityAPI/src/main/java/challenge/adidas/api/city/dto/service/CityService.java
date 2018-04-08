package challenge.adidas.api.city.dto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import challenge.adidas.api.city.converters.CitiesConverter;
import challenge.adidas.api.city.converters.CityConverter;
import challenge.adidas.api.city.dto.response.City;
import challenge.adidas.api.city.exceptions.ResourceNotFoundException;
import challenge.adidas.api.city.model.CityLink;
import challenge.adidas.api.city.repository.CityLinkRepository;

/**
 * This service represents the service logic, it accesses to the repository and
 * based on the response, it invokes to the corresponding converter
 * 
 * @author joseam
 *
 */

@Component
public class CityService {

	@Autowired
	CityLinkRepository repository;

	@Autowired
	CityConverter cityConverter;

	@Autowired
	CitiesConverter citiesConverter;

	public City getCity(String cityName) throws ResourceNotFoundException {
		List<CityLink> links = repository.findByCity(cityName);
		if (links == null || links.size() == 0) {
			throw new ResourceNotFoundException(cityName);
		}
		return cityConverter.convert(links);
	}

	public List<City> getAll() {
		List<CityLink> links = repository.findAll();
		return citiesConverter.convert(links);
	}
}
