package com.goar.otrs.restaurantservice.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT_TABLE")
public class RestaurantTable extends BaseEntity<BigInteger> {

	@Column(name = "CAPACITY")
	private int capacity;

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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
