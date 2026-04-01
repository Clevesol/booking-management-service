package com.enactor.domain.stratergy.impl;

import com.enactor.domain.model.RouteSegment;
import com.enactor.domain.stratergy.PricingStrategy;
import com.enactor.appcore.util.BaseSingleton;

public class PerKMPricingStrategy extends BaseSingleton implements PricingStrategy {
    /**
     * @param a
     * @param b
     * @return
     */
    @Override
    public double calculateAngGetPrice(RouteSegment a, RouteSegment b) {
        return 0;
    }

    public static PerKMPricingStrategy getInstance(){
        return BaseSingleton.getInstance(PerKMPricingStrategy.class);
    }
}
