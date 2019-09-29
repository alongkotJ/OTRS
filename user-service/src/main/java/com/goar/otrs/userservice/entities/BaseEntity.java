package com.goar.otrs.userservice.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<T> extends Entity<T> {

	@Column(name = "IS_MODIFIED")
	private boolean isModified;

	public BaseEntity() {
		super();
	}

	public BaseEntity(T id, String fullname) {
		super.setId(id);
		super.setFullname(fullname);
		this.isModified = false;
	}

	public boolean isModified() {
		return isModified;
	}

	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

}
