package com.goar.otrs.bookingservice.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKING")
public class Booking extends BaseEntity<String> {

	@Column(name = "RESTAUANT_ID")
	private String restaurantId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "DATE")
	private LocalDate date;

	@Column(name = "TIME")
	private LocalTime time;

	@Column(name = "TABLE_ID")
	private String tableId;

	public Booking() {
		super();
	}

	private Booking(String id, String name) {
		super(id, name);
	}

	public static Booking getDummyBooking() {
		return new Booking(null, null);
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

}
