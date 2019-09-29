package com.goar.otrs.restaurantservice.vo;

import java.util.List;
import java.util.Optional;

import com.goar.otrs.restaurantservice.entities.RestaurantTable;

public class RestaurantVO {

	private Optional<List<RestaurantTable>> tables = Optional.empty();

	private String name;

	private String id;

	private String address;

	public Optional<List<RestaurantTable>> getTables() {
		return tables;
	}

	public void setTables(Optional<List<RestaurantTable>> tables) {
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
