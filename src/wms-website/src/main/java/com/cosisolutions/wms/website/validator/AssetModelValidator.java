package com.cosisolutions.wms.website.validator;

import com.cosisolutions.wms.website.models.AssetModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AssetModelValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return AssetModel.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AssetModel model = (AssetModel) target;

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name", "Name is required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "error.code", "Code is required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.address", "address is required");

    String code = model.getCode();
    if((code.length() < 2 || code.length() > 6) && !errors.hasFieldErrors("code"))
      errors.rejectValue("code", "error.code", "Code must be between 2 and 6 characters long");
  }
}
