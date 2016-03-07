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
            $(label).parent().find('input').addClass('registration-valid');
        }
    });

    $.validator.addMethod('passwordRequirement', function(value, element) {
       var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
       return this.optional(element)
        || regex.test(value)
    }, 'Your password must be longer than 8 symbols and contain at least one number, uppercase letter and special symbol');

    $.validator.addMethod("lettersOnly", function(value, element) {
        var regex = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/;
        return this.optional(element)
        || regex.test(value)
    }, "Letters and spaces only please");

    $('#register-form').validate({
        errorClass: 'registration-error',
        errorElement: 'span',
        rules: {
            email: {
                required: true,
                email:true,
                remote: 'registration/validate'
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