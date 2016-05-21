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

  $('#account-edit').validate({
    errorClass: 'validation-error',
    errorElement: 'span',
    rules: {
     firstName: {
        required: true,
        lettersOnly: true
      },
      lastName: {
        required: true,
        lettersOnly: true
      }
    }
  });
}(jQuery));