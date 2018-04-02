package com.qa.accproj.validator;

import com.qa.accproj.dao.AppUserDAO;
import com.qa.accproj.formbean.AppUserForm;
import com.qa.accproj.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class AppUserValidator implements Validator {
 
    @Autowired
    private AppUserDAO appUserDAO;
 
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }
 
    @Override
    public void validate(Object target, Errors errors) {
        AppUserForm appUserForm = (AppUserForm) target;
 
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "NotEmpty.appUserForm.accountNumber");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
 
        if (!errors.hasFieldErrors("accountNumber")) {
            AppUser dbUser = appUserDAO.findAppUserByAccountNumber(appUserForm.getAccountNumber());
            if (dbUser != null) {
                errors.rejectValue("accountNumber", "Duplicate.appUserForm.accountNumber");
            }
        }
    }
 
}