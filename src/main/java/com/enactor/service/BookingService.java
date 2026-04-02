package com.enactor.service;

import com.enactor.appcore.appserver.core.annotation.Service;
import com.enactor.appcore.util.validator.ValidatorsContainer;
import com.enactor.domain.dto.BookingAvailabilityRequest;
import com.enactor.domain.dto.BookingAvailabilityResponse;
import com.enactor.domain.dto.BookingRequest;
import com.enactor.domain.enums.BookingStatusEnum;
import com.enactor.domain.model.Booking;
import com.enactor.domain.model.Payment;
import com.enactor.repo.BookingRepo;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private BookingRepo bookingRepo;

    private final ValidatorsContainer validatorsContainer = ValidatorsContainer.getInstance();

    public BookingAvailabilityResponse getAvailability(BookingAvailabilityRequest dto) throws Exception{
        this.validate(dto);
        return this.bookingRepo.findByAvailabilityRequestDTO();
    }

    public Booking initiateBooking(final BookingRequest requestDTO) throws Exception {
        return this.bookingRepo.add(bookingRequestToEntity(requestDTO));
    }

    public Booking confirmBooking(final Booking booking) {
        return booking;
    }

    public Booking confirmBooking(final Booking booking, final Payment payment) {

        return booking;
    }

    private void validate(BookingAvailabilityRequest dto) throws Exception{
        validatorsContainer.getValidatorsByDTO(BookingAvailabilityRequest.class).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Suitable validator not found"))
                .validate(dto);

    }

    private Booking bookingRequestToEntity(BookingRequest request){
        Booking booking = new Booking();
        booking.setBookingDateTime(request.getTripDate());
        booking.setStatus(BookingStatusEnum.INITIATED);
        booking.setCreatedDateTime(LocalDateTime.now());
        return booking;
    }
}
