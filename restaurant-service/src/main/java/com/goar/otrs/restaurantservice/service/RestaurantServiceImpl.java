package com.goar.otrs.restaurantservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goar.otrs.restaurantservice.entities.Restaurant;
import com.goar.otrs.restaurantservice.repo.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantRepo restaurantRepo;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepo restaurantRepo) {
		this.restaurantRepo = restaurantRepo;
	}

	@Override
	public Collection<Restaurant> findAll() throws Exception {
		return restaurantRepo.findAll();
	}

	@Override
	public void addOrUpdate(Restaurant restaurant) throws Exception {
		restaurantRepo.save(restaurant);
	}

	@Override
	public void delete(String id) throws Exception {
		restaurantRepo.deleteById(id);
	}

	@Override
	public Restaurant findById(String id) throws Exception {
		Optional<Restaurant> restaurant = restaurantRepo.findById(id);
		return restaurant.orElse(null);
	}

	@Override
	public Collection<Restaurant> findByFullname(String fullname) throws Exception {
		return restaurantRepo.findByFullname(fullname);
	}

	@Override
	public Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
		return null;
	}

}
