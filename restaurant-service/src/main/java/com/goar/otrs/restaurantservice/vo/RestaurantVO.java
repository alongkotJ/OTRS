package com.goar.otrs.restaurantservice.vo;

import java.util.List;

import com.goar.otrs.restaurantservice.entities.RestaurantTable;

public class RestaurantVO {

	private List<RestaurantTable> restaurantTables;

	private String fullname;

	private String id;

	private String address;

	private boolean isModified;

	public RestaurantVO() {
		super();
	}

	public RestaurantVO(String id, String fullname) {
		super();
		this.fullname = fullname;
		this.id = id;
		this.isModified = false;
	}

	public RestaurantVO(String id, String fullname, List<RestaurantTable> restaurantTables, String address) {
		this.id = id;
		this.fullname = fullname;
		this.restaurantTables = restaurantTables;
		this.address = address;
	}

	public List<RestaurantTable> getRestaurantTables() {
		return restaurantTables;
	}

	public void setRestaurantTables(List<RestaurantTable> restaurantTables) {
		this.restaurantTables = restaurantTables;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public boolean isModified() {
		return isModified;
	}

	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	@Override
	public String toString() {
		return "RestaurantVO [restaurantTables=" + restaurantTables + ", fullname=" + fullname + ", id=" + id
				+ ", address=" + address + ", isModified=" + isModified + "]";
	}

}
