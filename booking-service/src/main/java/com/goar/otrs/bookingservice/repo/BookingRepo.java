package com.goar.otrs.bookingservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goar.otrs.bookingservice.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, String> {

}
