package com.enactor.facade;

import com.enactor.appcore.appserver.core.annotation.Autowired;
import com.enactor.appcore.appserver.core.annotation.Service;
import com.enactor.domain.dto.BookingAvailabilityResponse;
import com.enactor.domain.dto.BookingRequest;
import com.enactor.domain.dto.BookingConfirmation;
import com.enactor.domain.model.Booking;
import com.enactor.domain.model.Payment;
import com.enactor.domain.model.Ticket;
import com.enactor.service.BookingService;
import com.enactor.service.PaymentService;
import com.enactor.service.RouteSegmentService;
import com.enactor.appcore.util.validator.ValidatorsContainer;

@Service
public class BookingFacadeService {

    private final BookingService bookingService;

    private final PaymentService paymentService;

    private final RouteSegmentService segmentService;

    private final ValidatorsContainer validatorsContainer;

    @Autowired
    public BookingFacadeService(final BookingService bookingService, final PaymentService paymentService, final RouteSegmentService segmentRepo){
        this.bookingService = bookingService;
        this.paymentService = paymentService;
        this.segmentService = segmentRepo;
        this.validatorsContainer = ValidatorsContainer.getInstance();
    }


    public BookingAvailabilityResponse getAvailabilityAndPrice(){

        return null;
    }

    public BookingConfirmation createAndConfirmBooking(BookingRequest requestDTO) throws Exception{

        //validates requestDTO with all registered validators
        this.validatorsContainer.chainValidate(requestDTO);
        Booking booking = this.createBooking(requestDTO);
        BookingConfirmation confirmation = this.createBookingConfirmation(booking);
        return confirmation;
    }

    private Booking createBooking(BookingRequest requestDTO) throws Exception{
        Booking booking = this.bookingService.initiateBooking(requestDTO);
        Payment payment = this.paymentService.createPayment(new Payment(booking.getId()));
        booking = this.bookingService.confirmBooking(booking, payment);
        return booking;
    }

    private BookingConfirmation createBookingConfirmation(Booking booking) throws Exception{

        if(booking == null) throw new RuntimeException("Invalid booking reference");

        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(booking.getId());
        confirmation.setBookingDateTime(booking.getBookingDateTime());
        double price =  booking.getTickets().stream().mapToDouble(Ticket::getPrice).sum();
        confirmation.setPriceTotal(price);

        return confirmation;
    }



}
