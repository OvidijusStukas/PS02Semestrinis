package com.cosisolutions.wms.website.validator;

import com.cosisolutions.wms.website.models.AccountModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class AccountEditValidator extends AccountModelValidator {

  @Override
  public void validate(Object target, Errors errors) {
    AccountModel model = (AccountModel) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName", "Last name is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName", "First name is required.");

    if(!model.getFirstName().matches(ONLY_LETTERS) && !errors.hasFieldErrors("firstName")) {
      errors.rejectValue("firstName", "error.firstName", "First name can only contain letters and spaces");
    }
    if(!model.getLastName().matches(ONLY_LETTERS) && !errors.hasFieldErrors("lastName")) {
      errors.rejectValue("lastName", "error.lastName", "Last name can only contain letters and spaces.");
    }
  }
}
