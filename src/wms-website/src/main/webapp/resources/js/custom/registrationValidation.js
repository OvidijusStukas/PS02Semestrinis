;
(function($) {
  'use strict';

  $.validator.setDefaults({
    highlight: function(element) {
      $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function(element) {
      $(element).closest('.form-group').removeClass('has-error');
    },
    errorPlacement: function(error, element) {
      $(element).parent().append(error);
    },
    success: function(label) {
      $(label).parent().find('input').addClass('validation-valid');
    }
  });

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