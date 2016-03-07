/**
 * Created by Ovidijus Stukas on 2/13/2016.
 * For CosISolutions
 */

/**
 * Loads registration form and removes login form
 */
$('#signin-register-link').on('click', function (e) {
    'use strict';
    e && e.preventDefault();
    var $container = $('#signin-container');
    var email = $('#signin-email').val();

    $.ajax({
        url: 'login/registration',
        method: 'GET',
        success: function (html) {
            $container.fadeOut('slow', function () {
                $container.html(html);
                $container.find('#registration-email').val(email || '');
                $container.fadeIn('slow');
            });
        }
    });
});