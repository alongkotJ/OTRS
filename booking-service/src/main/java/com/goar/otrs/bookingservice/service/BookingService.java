package com.goar.otrs.bookingservice.service;

import java.util.Collection;

import com.goar.otrs.bookingservice.entities.Booking;

public interface BookingService {

	public Collection<Booking> findAll() throws Exception;

	public void addOrUpdate(Booking booking) throws Exception;

	public void delete(String id) throws Exception;

	public Booking findById(String id) throws Exception;

}
