package com.enactor.appcore.util.validator.impl;

import com.enactor.appcore.appserver.core.annotation.Autowired;
import com.enactor.appcore.appserver.core.annotation.DTOValidator;
import com.enactor.appcore.util.validator.BaseDTOValidator;
import com.enactor.domain.dto.BookingRequestDTO;
import com.enactor.service.RouteSegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@DTOValidator
public class BookingRequestSegmentValidator implements BaseDTOValidator<BookingRequestDTO> {

    private final RouteSegmentService segmentService;

    private static final Logger LOG = LoggerFactory.getLogger(BookingRequestSegmentValidator.class);

    @Autowired
    public BookingRequestSegmentValidator(final RouteSegmentService segmentService){
        this.segmentService = segmentService;
    }


    /**
     * @param dto
     * @throws Exception
     */
    @Override
    public void validate(BookingRequestDTO dto) throws Exception {
        LOG.info("Validating {}" ,dto.getClass().getName());
    }
}
