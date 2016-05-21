;
(function($) {
  'use strict';

  $('#register-form').validate({
    errorClass: 'validation-error',
    errorElement: 'span',
    rules: {
      email: {
        required: true,
        email:true,
        remote: 'registration/validation'
      },
      firstName: {
        required: true,
        lettersOnly: true
      },
      lastName: {
        required: true,
        lettersOnly: true
      },
      password: {
        required: true,
        passwordRequirement: true
      },
      passwordRepeat: {
        required: true,
        equalTo: "#registration-password"
      }
    },
    messages: {
      email: {
        remote: $.validator.format("{0} is already taken.")
      }
    }
  });
}(jQuery));