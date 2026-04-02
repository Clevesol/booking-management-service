package com.enactor.controller;

import com.enactor.appcore.appserver.core.annotation.Autowired;
import com.enactor.appcore.appserver.core.annotation.Controller;
import com.enactor.appcore.appserver.core.annotation.HTTPMethod;
import com.enactor.appcore.appserver.core.annotation.RequestMapping;
import com.enactor.domain.dto.BookingAvailabilityDTO;
import com.enactor.domain.dto.BookingRequestDTO;
import com.enactor.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookingController extends BaseController {

    private final BookingService bookingService;


    @Autowired
    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(path = "/api/bookings/check-availability-price", method = HTTPMethod.GET)
    public BookingAvailabilityDTO checkAvailability(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return bookingService.getAvailability();
    }

    @RequestMapping(path = "/api/bookings/reserve", method = HTTPMethod.POST)
    public void bookTickets(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        jsonMapper.toObject(req.getInputStream(), BookingRequestDTO.class);
    }
}
