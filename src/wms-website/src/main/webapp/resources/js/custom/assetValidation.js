;
(function($) {
  'use strict';

  $('#asset-form').validate({
    errorClass: 'validation-error',
    errorElement: 'span',
    rules: {
      name: {
        required: true
      },
      code: {
        required: true,
        minlength: 2,
        maxlength: 6
      },
      address: {
        required: true
      }
    }
  });
}(jQuery));