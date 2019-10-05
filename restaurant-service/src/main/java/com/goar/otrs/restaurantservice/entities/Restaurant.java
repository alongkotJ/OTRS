package com.goar.otrs.restaurantservice.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant extends BaseEntity<String> {

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "restaurant")
//	@Column(name = "TABLE_ID")
//	@JoinColumn(name = "ID")
	private List<RestaurantTable> restaurantTables;

	@Column(name = "ADDRESS")
	private String address;

	public Restaurant() {
		super();
	}

	public Restaurant(String id, String name, List<RestaurantTable> restaurantTables, String address) {
		super(id, name);
		this.restaurantTables = restaurantTables;
		this.address = address;
	}

	public Restaurant(String id, String name) {
		super(id, name);
		this.restaurantTables = new ArrayList<RestaurantTable>();
	}

	public static Restaurant getDummyRestaurant() {
		return new Restaurant(null, null);
	}

	public List<RestaurantTable> getRestaurantTables() {
		return restaurantTables;
	}

	public void setRestaurantTables(List<RestaurantTable> restaurantTables) {
		this.restaurantTables = restaurantTables;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + this.getId() + " fullname=" + this.getFullname() + " isModified=" + this.isModified()
				+ " restaurantTables=" + restaurantTables + ", address=" + address + "]";
	}

}
