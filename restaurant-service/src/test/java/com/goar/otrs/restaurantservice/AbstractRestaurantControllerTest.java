package com.goar.otrs.restaurantservice;

import java.util.Collection;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.goar.otrs.restaurantservice.controller.RestaurantController;
import com.goar.otrs.restaurantservice.entities.Restaurant;
import com.goar.otrs.restaurantservice.vo.RestaurantVO;

public abstract class AbstractRestaurantControllerTest {

	protected static final String RESTURANT_ID = "1";
	protected static final String RESTURANT_NAME = "Magic Food";
	protected static final String RESTURANT_ADDRESS = "Don Mueng Airport";

	@Autowired
	private RestaurantController restaurantController;

	@Test
	public void validRestaurantById() throws Exception {
		Logger.getGlobal().info("Start validRestaurantById() test");

		ResponseEntity<Restaurant> response = restaurantController.findById(RESTURANT_ID);

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertTrue(response.hasBody());
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(RESTURANT_ID, response.getBody().getId());
		Assert.assertEquals(RESTURANT_NAME, response.getBody().getFullname());
		Assert.assertEquals(RESTURANT_ADDRESS, response.getBody().getAddress());

		Logger.getGlobal().info("End validRestaurantById() test");
	}

	@Test
	public void validRestaurantByName() throws Exception {
		Logger.getGlobal().info("Start validRestaurantByName() test");

		ResponseEntity<Collection<Restaurant>> response = restaurantController.findByFullname(RESTURANT_NAME);

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertTrue(response.hasBody());
		Assert.assertNotNull(response.getBody());
		Assert.assertFalse(response.getBody().isEmpty());

		Restaurant restaurant = response.getBody().stream().findFirst().orElse(null);

		Assert.assertEquals(RESTURANT_ID, restaurant.getId());
		Assert.assertEquals(RESTURANT_NAME, restaurant.getFullname());
		Assert.assertEquals(RESTURANT_ADDRESS, restaurant.getAddress());

		Logger.getGlobal().info("End validRestaurantByName() test");
	}

	@Test
	public void validAddRestaurant() throws Exception {
		Logger.getGlobal().info("Start validAddRestaurant() test");

		RestaurantVO restaurantVO = new RestaurantVO("20","TEST REST API NAME");

		ResponseEntity<Restaurant> response = restaurantController.add(restaurantVO);

		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

		Logger.getGlobal().info("End validAddRestaurant() test");
	}
}
