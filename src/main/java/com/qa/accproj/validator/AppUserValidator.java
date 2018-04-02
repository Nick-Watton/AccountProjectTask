package com.qa.accproj.validator;

import com.qa.accproj.formbean.AppUserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class AppUserValidator implements Validator {
 
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "NotEmpty.appUserForm.accountNumber");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");

    }
 
}