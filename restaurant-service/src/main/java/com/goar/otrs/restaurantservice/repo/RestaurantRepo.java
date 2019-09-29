package com.goar.otrs.restaurantservice.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goar.otrs.restaurantservice.entities.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, String> {

	boolean findByFullnameContaining(String fullname) throws Exception;

	public Collection<Restaurant> findByFullname(String fullname) throws Exception;

}
