package com.enactor.controller;

import com.enactor.appserver.core.annotation.Autowired;
import com.enactor.appserver.core.annotation.Controller;
import com.enactor.appserver.core.annotation.HTTPMethod;
import com.enactor.appserver.core.annotation.RequestMapping;
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
    public void checkAvailability(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String response = bookingService.getAvailability();
        this.initAndWriteResponse(resp, response);
    }

    @RequestMapping(path = "/api/bookings/reserve", method = HTTPMethod.POST)
    public void bookTickets(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        jsonMapper.toObject(req.getInputStream(), BookingRequestDTO.class);
    }
}
