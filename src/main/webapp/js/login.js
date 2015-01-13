var Login = function() {

    var handleLogin = function() {

        $('.form-signin').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "Username is required."
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   
                $('.alert-danger', $('.form-signin')).show();
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.login-wrap').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.login-wrap').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
                form.submit(); // form validation success, call ajax form submit
            }
        });

        $('.form-signin input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.form-signin').validate().form()) {
                    $('.form-signin').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    }

    return {
        //main function to initiate the module
        init: function() {
            handleLogin();
        }

    };

}();