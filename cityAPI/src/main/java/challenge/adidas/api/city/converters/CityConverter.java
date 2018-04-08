package challenge.adidas.api.city.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import challenge.adidas.api.city.dto.response.City;
import challenge.adidas.api.city.dto.response.Link;
import challenge.adidas.api.city.model.CityLink;

/**
 * 
 * this converter will retrieve a City based on the cityLinks provided
 * 
 * @author joseam
 *
 */

@Component
public class CityConverter {

	public City convert(List<CityLink> links) {

		City city = new City();

		city.setName(links.get(0).getCity());

		List<Link> newLinks = new ArrayList<>();

		for (CityLink link : links) {
			Link newLink = new Link();
			newLink.setDestination(link.getDestinyCity());
			newLink.setArrival(link.getArrivalTime());
			newLink.setDeparture(link.getDepartureTime());

			newLinks.add(newLink);
		}

		city.setLinks(newLinks);
		return city;
	}
}
