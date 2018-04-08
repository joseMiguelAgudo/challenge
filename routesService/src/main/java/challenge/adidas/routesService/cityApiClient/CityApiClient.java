package challenge.adidas.routesService.cityApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import challenge.adidas.api.city.dto.response.City;
import challenge.adidas.api.city.dto.response.CityApiError;
import challenge.adidas.routesService.exceptions.BaseException;
import challenge.adidas.routesService.exceptions.CityApiServerException;
import challenge.adidas.routesService.exceptions.ResourceNotFoundException;

/**
 * connection class with the cityAPI service
 * 
 * @author Lenovo
 *
 */

// IMPROVEMENT PENDING: REPLACE THIS CLASS BY A DOCKER CONNECTION LIB

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class CityApiClient {

	@Value("${cityapi.url}")
	String cityApiUrl;

	public String getCityApiUrl() {
		return cityApiUrl;
	}

	public void setCityApiUrl(String cityApiUrl) {
		this.cityApiUrl = cityApiUrl;
	}

	public City getCity(String city) throws BaseException {
		URL url;
		try {
			url = new URL(cityApiUrl + "/" + city);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			BufferedReader br;
			if (conn.getResponseCode() == HttpStatus.OK.value()) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}

			String output = "";
			String line;
			while ((line = br.readLine()) != null) {
				output = output + "\n" + line;
			}

			conn.disconnect();

			if (conn.getResponseCode() == HttpStatus.OK.value()) {
				return parseCityFromJSON(output.trim());
			} else {
				throw parseApiException(output.trim());
			}
		} catch (IOException e) {
			throw new CityApiServerException(e.getMessage());
		}
	}

	private BaseException parseApiException(String json) throws JsonParseException, JsonMappingException, IOException,
			ResourceNotFoundException, CityApiServerException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		CityApiError error = objectMapper.readValue(json, CityApiError.class);
		switch (error.getStatus()) {
		case BAD_REQUEST:
			return new ResourceNotFoundException(error.getMessage());
		default:
			return new CityApiServerException(error.getMessage());
		}
	}

	private City parseCityFromJSON(String json) throws JsonParseException, JsonMappingException, IOException,
			ResourceNotFoundException, CityApiServerException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(json, City.class);

	}

}
