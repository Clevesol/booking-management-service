package com.enactor.facade;

import com.enactor.appserver.core.annotation.Autowired;
import com.enactor.appserver.core.annotation.Service;
import com.enactor.domain.dto.BookingAvailabilityDTO;
import com.enactor.service.BookingService;

@Service
public class BookingFacadeService {

    private final BookingService bookingService;



    @Autowired
    public BookingFacadeService(final BookingService bookingService){
        this.bookingService = bookingService;
    }


    public BookingAvailabilityDTO getAvailabilityAndPrice(){

        return null;
    }
}
