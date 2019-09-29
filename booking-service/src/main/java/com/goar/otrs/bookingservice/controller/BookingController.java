package com.goar.otrs.bookingservice.controller;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goar.otrs.bookingservice.entities.Booking;
import com.goar.otrs.bookingservice.exception.DuplicateBookingException;
import com.goar.otrs.bookingservice.exception.InvalidBookingException;
import com.goar.otrs.bookingservice.service.BookingService;
import com.goar.otrs.bookingservice.vo.BookingVO;

@RestController
@RequestMapping("/v1/booking")
public class BookingController {

	private BookingService bookingService;

	private static final Logger logger = Logger.getLogger(BookingController.class.getName());

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@GetMapping({ "", "/" })
	public ResponseEntity<Collection<Booking>> findAll() throws Exception {
		logger.info("booking-service findAll() invoked");
		Collection<Booking> bookings = bookingService.findAll();
		return bookings.size() > 0 ? new ResponseEntity<>(bookings, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/")
	public ResponseEntity<Booking> add(@RequestBody BookingVO bookingVo) throws Exception {

		logger.info(String.format("booking-service add() invoked: %s for %s", bookingService.getClass().getName(),
				bookingVo.getName()));

		Booking booking = Booking.getDummyBooking();
		BeanUtils.copyProperties(bookingVo, booking);

		try {
			bookingService.addOrUpdate(booking);
		} catch (DuplicateBookingException | InvalidBookingException ex) {
			logger.log(Level.WARNING, "Exception raised add Booking REST Call {0}", ex);
			throw ex;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception raised add Booking REST Call {0}", ex);
			throw ex;
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{booking_id}")
	public ResponseEntity<Booking> findById(@PathVariable("booking_id") String id) throws Exception {
		logger.info(String.format("booking-service findById() invoked:{} for {} ", bookingService.getClass().getName(),
				id));

		Booking booking = null;
		try {
			booking = bookingService.findById(id.trim());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Exception raised findById REST Call {0}", ex);
			throw ex;
		}
		return booking != null ? new ResponseEntity<>(booking, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
