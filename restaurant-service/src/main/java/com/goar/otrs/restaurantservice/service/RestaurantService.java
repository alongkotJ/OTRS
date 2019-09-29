package com.goar.otrs.restaurantservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.goar.otrs.restaurantservice.entities.Restaurant;

public interface RestaurantService {
	
	public Collection<Restaurant> findAll() throws Exception;

	public void addOrUpdate(Restaurant restaurant) throws Exception;

	public void delete(String id) throws Exception;

	public Restaurant findById(String id) throws Exception;

	public Collection<Restaurant> findByFullname(String fullname) throws Exception;

	public Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;

}
