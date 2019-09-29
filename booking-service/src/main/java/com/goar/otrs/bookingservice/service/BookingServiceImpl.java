package com.goar.otrs.bookingservice.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goar.otrs.bookingservice.entities.Booking;
import com.goar.otrs.bookingservice.repo.BookingRepo;

@Service
public class BookingServiceImpl implements BookingService {

	private BookingRepo bookingRepo;

	@Autowired
	public BookingServiceImpl(BookingRepo bookingRepo) {
		this.bookingRepo = bookingRepo;
	}

	@Override
	public Collection<Booking> findAll() throws Exception {
		return bookingRepo.findAll();
	}

	@Override
	public void addOrUpdate(Booking booking) throws Exception {
		bookingRepo.save(booking);
	}

	@Override
	public void delete(String id) throws Exception {
		bookingRepo.deleteById(id);
	}

	@Override
	public Booking findById(String id) throws Exception {
		Optional<Booking> booking = bookingRepo.findById(id);
		return booking.orElse(null);
	}

}
