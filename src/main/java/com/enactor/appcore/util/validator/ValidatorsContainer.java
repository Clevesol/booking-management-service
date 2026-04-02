package com.enactor.appcore.util.validator;

import com.enactor.appcore.appserver.core.didc.filter.impl.ValidatorDependencyFilter;
import com.enactor.appcore.util.BaseSingleton;

import java.util.List;

public class ValidatorsContainer extends BaseSingleton {

    private final ValidatorDependencyFilter validatorDependencyFilter;

    public ValidatorsContainer(){
        this.validatorDependencyFilter = ValidatorDependencyFilter.getInstance();
    }

    public List<BaseDTOValidator> getValidatorsByDTO(Object dto){
        return ((ValidatorDependencyFilter) this.validatorDependencyFilter).getFilterCriteria(dto);
    }

    public void chainValidate(Object dto) throws Exception{
        for(BaseDTOValidator validator :  this.getValidatorsByDTO(dto)){
            validator.validate(dto);
        }
    }

    public static ValidatorsContainer getInstance(){
        return BaseSingleton.getInstance(ValidatorsContainer.class);
    }

    public List<BaseDTOValidator> getValidator(Class<?> type){
        return this.validatorDependencyFilter.getValidators(type.getName());
    }


}
