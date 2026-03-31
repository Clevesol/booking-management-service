package com.enactor.domain.model;

import java.util.List;
import java.util.UUID;

public class Trip {

    private UUID id;

    private String name;

    private Bus bus;

    private Route route;

    private List<BusDriver> driverList;

    private List<BusConductor> conductorList;
}
