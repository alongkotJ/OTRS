package com.goar.otrs.restaurantservice.entities;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT_TABLE")
public class RestaurantTable extends BaseEntity<BigInteger> {

	@Column(name = "CAPACITY")
	private int capacity;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;

	public RestaurantTable() {
		super();
	}

	public RestaurantTable(int capacity) {
		super();
		this.capacity = capacity;
	}

	public RestaurantTable(BigInteger id, String name, int capacity) {
		super(id, name);
		this.capacity = capacity;
	}

	public RestaurantTable(BigInteger id, String name, int capacity, Restaurant restaurant) {
		super(id, name);
		this.capacity = capacity;
		this.restaurant = restaurant;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
