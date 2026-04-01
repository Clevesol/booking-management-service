package com.enactor.domain.stratergy;

import com.enactor.domain.stratergy.impl.FixedSegmentPricingStrategy;
import com.enactor.domain.stratergy.impl.PerKMPricingStrategy;
import com.enactor.domain.stratergy.impl.PerSegmentPricingStrategy;
import com.enactor.util.PricingStratergyEnum;

public class PricingStrategyFactory {

    private final PricingStratergyEnum DEFAULT_PRICING_STRATEGY = PricingStratergyEnum.FIXED_PRICING_STRATEGY;

    public PricingStrategy getDefaultPricingStrategy(){

        switch (DEFAULT_PRICING_STRATEGY){
            case FIXED_PRICING_STRATEGY:
                return FixedSegmentPricingStrategy.getInstance();
            case PER_SEGMENT_STRATEGY:
                return PerSegmentPricingStrategy.getInstance();
            case PER_KM_PRICING_STRATEGY:
                return PerKMPricingStrategy.getInstance();
            default:
                return FixedSegmentPricingStrategy.getInstance();
        }
    }

}
