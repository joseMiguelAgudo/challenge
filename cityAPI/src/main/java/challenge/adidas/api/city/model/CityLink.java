package challenge.adidas.api.city.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * model stored in the database
 * 
 * @author joseam
 *
 */

@Entity
@Table(name = "CITY_LINK")
public class CityLink {

	@Id
	Long id;

	@Column
	String city;

	@Column
	String destinyCity;

	@Column
	LocalTime departureTime;

	@Column
	LocalTime arrivalTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDestinyCity() {
		return destinyCity;
	}

	public void setDestinyCity(String destinyCity) {
		this.destinyCity = destinyCity;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
