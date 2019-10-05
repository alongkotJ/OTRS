package com.goar.otrs.restaurantservice.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity<T> {

	@Id
	@Column(name = "ID")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private T id;

	@Column(name = "FULLNAME")
	private String fullname;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", fullname=" + fullname + "]";
	}

}
