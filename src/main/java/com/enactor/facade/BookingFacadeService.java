package com.enactor.facade;

import com.enactor.appcore.appserver.core.annotation.Autowired;
import com.enactor.appcore.appserver.core.annotation.Service;
import com.enactor.domain.dto.BookingAvailabilityDTO;
import com.enactor.domain.dto.BookingRequestDTO;
import com.enactor.domain.dto.BookingReservedDTO;
import com.enactor.domain.model.Booking;
import com.enactor.domain.model.Payment;
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


    public BookingAvailabilityDTO getAvailabilityAndPrice(){

        return null;
    }

    public BookingReservedDTO createBooking(BookingRequestDTO requestDTO) throws Exception{

        //validates requestDTO with all registered validators
        this.validatorsContainer.chainValidate(requestDTO);

        Booking booking = this.bookingService.reserveBooking(requestDTO);
        Payment payment = this.paymentService.createPayment(new Payment(booking.getId()));
        this.bookingService.confirmBooking(booking, payment);
        return null;
    }

}
