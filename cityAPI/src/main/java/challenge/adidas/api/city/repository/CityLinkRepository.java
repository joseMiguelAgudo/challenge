package challenge.adidas.api.city.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import challenge.adidas.api.city.model.CityLink;

/**
 * JPA repository
 * 
 * @author joseam
 *
 */

public interface CityLinkRepository extends JpaRepository<CityLink, Long> {

	// customized query to look by cityName
	List<CityLink> findByCity(String city);

}
