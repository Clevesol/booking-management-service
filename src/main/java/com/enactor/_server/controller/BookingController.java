package com.enactor._server.controller;

import com.enactor._server.core.annotation.Autowired;
import com.enactor._server.core.annotation.Controller;
import com.enactor._server.core.annotation.HTTPMethod;
import com.enactor._server.core.annotation.RequestMapping;
import com.enactor.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(final BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(path = "/api/check-availability", method = HTTPMethod.GET)
    public void checkAvailability(HttpServletRequest req, HttpServletResponse resp){

    }

    @RequestMapping(path = "/api/book/", method = HTTPMethod.POST)
    public void bookTickets(HttpServletRequest req, HttpServletResponse resp){

    }
}
