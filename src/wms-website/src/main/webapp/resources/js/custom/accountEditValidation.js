;
(function($) {
  'use strict';

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