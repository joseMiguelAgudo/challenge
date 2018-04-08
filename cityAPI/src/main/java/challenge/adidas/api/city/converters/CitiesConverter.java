package challenge.adidas.api.city.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import challenge.adidas.api.city.dto.response.City;
import challenge.adidas.api.city.model.CityLink;

/**
 * 
 * this converter will retrieve a list of cities from the provided cityLink list
 * 
 * @author joseam
 *
 */

@Component
public class CitiesConverter {

	@Autowired
	CityConverter cityConverter;

	public List<City> convert(List<CityLink> links) {

		// with this stream, links will be grouped by city
		Map<String, List<CityLink>> linksByCity = links.stream().collect(Collectors.groupingBy(l -> l.getCity()));

		List<City> cities = new ArrayList<>();

		// for-each cityName present in the map, we create a city and populate
		// its links
		for (String cityName : linksByCity.keySet()) {
			City city = cityConverter.convert(linksByCity.get(cityName));
			cities.add(city);
		}

		return cities;
	}

}
