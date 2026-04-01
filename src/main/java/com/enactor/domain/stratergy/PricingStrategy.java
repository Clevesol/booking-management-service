package com.enactor.domain.stratergy;

import com.enactor.domain.model.RouteSegment;

public interface PricingStrategy {
    double calculateAngGetPrice(RouteSegment a, RouteSegment b);
}
