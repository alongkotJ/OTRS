package com.goar.otrs.restaurantservice.controller;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goar.otrs.restaurantservice.entities.Entity;
import com.goar.otrs.restaurantservice.entities.Restaurant;
import com.goar.otrs.restaurantservice.exception.DuplicateRestaurantException;
import com.goar.otrs.restaurantservice.exception.InvalidRestaurantException;
import com.goar.otrs.restaurantservice.service.RestaurantService;
import com.goar.otrs.restaurantservice.vo.RestaurantVO;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

	private RestaurantService restaurantService;

	private static final Logger logger = Logger.getLogger(RestaurantController.class.getName());

	@Autowired
	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping({ "", "/" })
	public ResponseEntity<Collection<Restaurant>> findAll() throws Exception {
		logger.info("restaurant-service findAll() invoked");
		Collection<Restaurant> restaurants = restaurantService.findAll();
		return restaurants.size() > 0 ? new ResponseEntity<>(restaurants, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Restaurant> add(@RequestBody RestaurantVO restaurantVo) throws Exception {

		logger.info(String.format("restaurant-service add() invoked: %s for %s", restaurantService.getClass().getName(),
				restaurantVo.getFullname()));
		
		logger.info("Before BEAN COPY RestaurantVO " + restaurantVo.toString());

		Restaurant restaurant = Restaurant.getDummyRestaurant();
		BeanUtils.copyProperties(restaurantVo, restaurant);

		logger.info("BEAN COPY RestaurantVO " + restaurantVo.toString() + " -> Restaurant " + restaurant.toString());

		try {
			restaurantService.addOrUpdate(restaurant);
		} catch (DuplicateRestaurantException | InvalidRestaurantException ex) {
			logger.log(Level.WARNING, "Exception raised add Restaurant REST Call {0}", ex);
			throw ex;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception raised add Restaurant REST Call " + ex);
			throw ex;
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{restaurant_id}")
	public ResponseEntity<Restaurant> findById(@PathVariable("restaurant_id") String id) throws Exception {
		logger.info(String.format("restaurant-service findById() invoked:{} for {} ",
				restaurantService.getClass().getName(), id));

		Restaurant restaurant = null;
		try {
			restaurant = restaurantService.findById(id.trim());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception raised findById REST Call {0}", ex);
			throw ex;
		}
		return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/")
	public ResponseEntity<Collection<Restaurant>> findByFullname(@RequestParam("fullname") String fullname)
			throws Exception {
		logger.info(String.format("restaurant-service findByFullname() invoked:{} for {} ",
				restaurantService.getClass().getName(), fullname));
		Collection<Restaurant> restaurants = null;
		try {
			restaurants = restaurantService.findByFullname(fullname.trim());
		} catch (Exception e) {
			throw e;
		}
		return restaurants.size() > 0 ? new ResponseEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK)
				: new ResponseEntity<Collection<Restaurant>>(HttpStatus.NOT_FOUND);
	}

}
