package com.enactor.service;

import com.enactor.appcore.appserver.core.annotation.Service;
import com.enactor.domain.dto.BookingAvailabilityDTO;
import com.enactor.domain.dto.BookingRequestDTO;
import com.enactor.domain.model.Booking;
import com.enactor.domain.model.Payment;
import com.enactor.repo.BookingRepo;

@Service
public class BookingService {

    private BookingRepo bookingRepo;

    public BookingAvailabilityDTO getAvailability() {
        return new BookingAvailabilityDTO();
    }

    public Booking reserveBooking(final BookingRequestDTO requestDTO) throws Exception{
        validate(requestDTO);

        return null;
    }

    public Booking confirmBooking(final Booking booking){
        return booking;
    }

    public Booking confirmBooking(final Booking booking, final Payment payment){
        return booking;
    }

    private void validate(final BookingRequestDTO dto){
        //validate if the provided details are valid

        //validate if origin and destination segments are valid
        dto.getOriginSegmentId();
        dto.getDestinationSegmentId();

    }
}
