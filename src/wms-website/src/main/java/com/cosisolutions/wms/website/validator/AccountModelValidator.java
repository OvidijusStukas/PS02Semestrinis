package com.cosisolutions.wms.website.validator;

import com.cosisolutions.wms.website.models.AccountModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountModelValidator implements Validator {
  private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$";
  private static final String ONLY_LETTERS = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$";

  @Override
  public boolean supports(Class<?> clazz) {
    return AccountModel.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AccountModel model = (AccountModel) target;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email", "Email is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName", "Last name is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName", "First name is required.");

    if(!model.getFirstName().matches(ONLY_LETTERS)) {
      errors.rejectValue("firstName", "error.firstName", "First name can only contain letters and spaces");
    }
    if(!model.getLastName().matches(ONLY_LETTERS)) {
      errors.rejectValue("lastName", "error.lastName", "Last name can only contain letters and spaces.");
    }

    // Password was not provided.
    if(errors.hasFieldErrors("password")) return;

    String password = model.getPassword();
    if(StringUtils.isNotBlank(model.getPasswordRepeat()) && !password.equals(model.getPasswordRepeat())) {
      errors.rejectValue("passwordRepeat", "error.passwordRepeat", "Passwords must match.");
    }
    if(StringUtils.containsWhitespace(password)) {
      errors.rejectValue("password", "error.password", "Password cannot contain any whitespace.");
    }
    if(!password.matches(PASSWORD_REGEX)) {
      errors.rejectValue("password", "error.password", "Password must be longer than 6 symbols and contain at least one number and uppercase letter");
    }
  }
}
