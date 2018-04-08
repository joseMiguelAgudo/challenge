package challenge.adidas.api.city.dto.response;

import java.util.List;

/**
 * 
 * Describes the object City returned by cityAPI service
 * 
 * @author joseam
 *
 */

public class City {

	String name;

	List<Link> links;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
