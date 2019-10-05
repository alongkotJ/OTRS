package com.goar.otrs.restaurantservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goar.otrs.restaurantservice.entities.Restaurant;
import com.goar.otrs.restaurantservice.entities.RestaurantTable;
import com.goar.otrs.restaurantservice.vo.RestaurantVO;
import com.goar.otrs.restaurantservice.AbstractRestaurantControllerTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"management.server.port=0", "management.context-path=/admin" })
public class RestaurantControllerIntegrationTest extends AbstractRestaurantControllerTest {

	public static final ObjectMapper objectMapper = new ObjectMapper();
	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@LocalServerPort
	private int port;

	@Test
	public void testGetById() {
		Logger.getGlobal().info("Start testGetById() test");

		Restaurant response = restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/1",
				Restaurant.class);

		assertNotNull(response);

		String id = response.getId().toString();
		assertNotNull(id);
		assertEquals(RESTURANT_ID, id);

		String fullname = response.getFullname().toString();
		assertNotNull(fullname);
		assertEquals(RESTURANT_NAME, fullname);

		boolean isModified = (boolean) response.isModified();
		assertEquals(false, isModified);

		List<RestaurantTable> restaurantTables = (List<RestaurantTable>) response.getRestaurantTables();
		assertNotNull(restaurantTables);

		Logger.getGlobal().info("End testGetById() test");
	}

	@Test
	public void testGetById_NOT_FOUND() {
		Logger.getGlobal().info("Start testGetById_NOT_FOUND() test");

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> entity = new HttpEntity<>(headers);

		ResponseEntity<Restaurant> response = restTemplate.exchange("http://localhost:" + port + "/v1/restaurants/99",
				HttpMethod.GET, entity, Restaurant.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

		Logger.getGlobal().info("End testGetById_NOT_FOUND() test");
	}

	@Test
	public void testGetByName() {
		Logger.getGlobal().info("Start testGetByName() test");

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> entity = new HttpEntity<>(headers);

		Restaurant uriVariables = new Restaurant();
		uriVariables.setFullname("Don Mueng Airport");

		ResponseEntity<Restaurant[]> rawResponse = restTemplate.exchange(
				"http://localhost:" + port + "/v1/restaurants?fullname={fullname}", HttpMethod.GET, entity,
				Restaurant[].class, uriVariables);

		assertNotNull(rawResponse);
		assertEquals(HttpStatus.OK, rawResponse.getStatusCode());

		Restaurant[] responses = rawResponse.getBody();
		assertNotNull(responses);

		Restaurant response = responses[0];
		assertNotNull(response);

		String id = response.getId().toString();
		assertNotNull(id);
		assertEquals(RESTURANT_ID, id);

		String fullname = response.getFullname().toString();
		assertNotNull(fullname);
		assertEquals(RESTURANT_NAME, fullname);

		boolean isModified = (boolean) response.isModified();
		assertEquals(false, isModified);

		List<RestaurantTable> restaurantTables = (List<RestaurantTable>) response.getRestaurantTables();
		assertNotNull(restaurantTables);

		Logger.getGlobal().info("End testGetByName() test");
	}

	@Test
	public void testAdd() throws JsonProcessingException {
		Logger.getGlobal().info("Start testAdd() test");

		String id_insert = "11";
		String fullname_insert = "La Plaza Restaurant";
		String address_insert = "address of La Plaza Restaurant";

		RestaurantVO requestBody = new RestaurantVO(id_insert, fullname_insert, new ArrayList<>(), address_insert);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

		ResponseEntity<Restaurant> rawResponsee = restTemplate.exchange("http://localhost:" + port + "/v1/restaurants/",
				HttpMethod.POST, entity, Restaurant.class);

		assertNotNull(rawResponsee);
		assertEquals(HttpStatus.CREATED, rawResponsee.getStatusCode());

		Restaurant response = restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/11",
				Restaurant.class);
		assertNotNull(response);

		String id_result = response.getId().toString();
		assertNotNull(id_result);
		assertEquals(id_insert, id_result);

		String fullname_result = response.getFullname().toString();
		assertNotNull(fullname_result);
		assertEquals(fullname_insert, fullname_result);

		String address_result = response.getAddress().toString();
		assertNotNull(address_result);
		assertEquals(address_insert, address_result);

		boolean isModified_result = (boolean) response.isModified();
		assertEquals(false, isModified_result);

		Logger.getGlobal().info("End testAdd() test");
	}

}
