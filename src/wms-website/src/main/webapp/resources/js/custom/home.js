$(document).ready(function () {
  'use strict';
  var $win = $(window);
  var $navbar = $('#main-navbar');

  /**
   * Binds scroll of page to navbar
   * Changes opacity levels upon scrolling
   * Takes a look for navbar height since there is nothing behind it at top and hole it's height
   */
  $win.scroll(function () {
    $win.scrollTop() - $navbar.height() <= 0 ? $navbar.css('opacity', '1') : $navbar.css('opacity', '0.8');
  });
});

function logout() {
  var form = document.getElementById("logoutForm") || '';
  if(form !== '')
    form.submit();
}

// Register custom validation methods
(function ($) {
  $.validator.addMethod('passwordRequirement', function(value, element) {
    var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
    return this.optional(element)
      || regex.test(value)
  }, 'Password must be longer than 6 symbols and contain at least one number and uppercase letter');

  $.validator.addMethod("lettersOnly", function(value, element) {
    var regex = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/;
    return this.optional(element)
      || regex.test(value)
  }, "Letters and spaces only please");
}(jQuery));
