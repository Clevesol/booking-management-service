package com.enactor.service;

import com.enactor.appserver.core.annotation.Service;
import com.enactor.domain.dto.BookingRequestDTO;
import com.enactor.domain.model.Booking;
import com.enactor.repo.BookingRepo;

@Service
public class BookingService {

    private BookingRepo bookingRepo;

    public String getAvailability() {
        return "200";
    }

    public Booking reserveBooking(final BookingRequestDTO requestDTO){
        validate(requestDTO);

        return null;
    }

    private void validate(final BookingRequestDTO dto){
        //validate if the provided details are valid


    }
}
