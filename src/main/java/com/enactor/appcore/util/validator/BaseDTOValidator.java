package com.enactor.appcore.util.validator;

public interface BaseDTOValidator<T> {

    void validate(T dto) throws Exception;

}
