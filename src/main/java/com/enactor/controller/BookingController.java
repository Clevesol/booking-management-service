package com.enactor.controller;

import com.enactor.appcore.appserver.core.annotation.Autowired;
import com.enactor.appcore.appserver.core.annotation.Controller;
import com.enactor.appcore.appserver.core.annotation.HTTPMethod;
import com.enactor.appcore.appserver.core.annotation.RequestMapping;
import com.enactor.domain.dto.BookingAvailabilityRequest;
import com.enactor.domain.dto.BookingAvailabilityResponse;
import com.enactor.domain.dto.BookingRequest;
import com.enactor.domain.dto.BookingConfirmation;
import com.enactor.facade.BookingFacadeService;
import com.enactor.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.UUID;

@Controller
public class BookingController extends BaseController {

    private final BookingService bookingService;

    private final BookingFacadeService bookingFacadeService;

    @Autowired
    public BookingController(final BookingService bookingService, final BookingFacadeService bookingFacadeService) {
        this.bookingService = bookingService;
        this.bookingFacadeService = bookingFacadeService;
    }

    @RequestMapping(path = "/api/bookings/check-availability-price", method = HTTPMethod.GET)
    public BookingAvailabilityResponse checkAvailability(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        BookingAvailabilityRequest request = new BookingAvailabilityRequest();
        request.setOrigin(UUID.fromString(req.getParameter("origin")));
        request.setDestination(UUID.fromString(req.getParameter("destination")));
        request.setPassengersCount(Integer.parseInt(req.getParameter("passengerCount")));

        return bookingService.getAvailability(request);
    }

    @RequestMapping(path = "/api/bookings/reserve", method = HTTPMethod.POST)
    public BookingConfirmation createAndConfirmBooking(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        jsonMapper.toObject(req.getInputStream(), BookingRequest.class);
        return this.bookingFacadeService.createAndConfirmBooking(wrapToDTO(req.getInputStream(), BookingRequest.class));
    }


}
