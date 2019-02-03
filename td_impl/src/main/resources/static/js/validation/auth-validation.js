var usernamePattern = /^[a-zA-Z0-9]+$/;
var emailPattern = /^[a-zA-Z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([a-zA-Z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([a-zA-Z])+$/;
var passwordPattern = /^[a-zA-Z0-9\*\$\_]+$/;
var hasError = false;

//Registration form
var regForm = document.querySelector('.registration');
var username = regForm.querySelector('.username');
var email = regForm.querySelector('.email');
var password = regForm.querySelector('.password');
var confirmPassword = regForm.querySelector('.confirmPassword');

function showError(field, text) {
 	var validError = field.nextElementSibling;
 	validError.innerText = text;
 	validError.style.transform = 'scaleX(1)';
 	hasError = true;
}

function cleanErrors(form) {
	var errors = form.querySelectorAll('.error_block');
	for (var i = 0; i < errors.length; i++) {
		errors[i].innerText = '';
		errors[i].style.transform = 'scaleX(0)';
	}
	hasError = false;
}

function checkEmptyFields(form) {
    var fields = form.querySelectorAll('.field');
	for (var i = 0; i < fields.length; i++) {
		if (!fields[i].value) {
			showError(fields[i], 'Необходимо заполнить данное поле');
		}
	}
}

//Registration form validation
regForm.addEventListener('submit', function(event) {
    event.preventDefault();
    cleanErrors(regForm);

	// Username validation
	if (!username.value.match(usernamePattern)) {
		showError(username, 'Имя пользователя должно содержать только латинские буквы и цифры');
	}
	else if (username.value.length < 4) {
		showError(username, 'Имя пользователя должно содержать не менее 4 символов');
	}
	else if (username.value.length > 16) {
		showError(username, 'Имя пользователя должно содержать не более 16 символов');
	}

	// Email validation
	if (!email.value.match(emailPattern)) {
		showError(email, 'Неправильный формат E-mail');
	}

	// Passwords validation
	if (password.value.length < 4) {
		showError(password, 'Пароль должен содержать не менее 4 символов');
	}
	else if (password.value.length > 16) {
		showError(password, 'Пароль должен содержать не более 16 символов');
	}
	else if (!password.value.match(passwordPattern)) {
		showError(password, 'Пароль может содержать только латинские буквы, цифры и символы: _*$');
	}
	else if (password.value !== confirmPassword.value) {
		showError(password, 'Пароли не совпадают');
		showError(confirmPassword, 'Пароли не совпадают');
	}

    checkEmptyFields(regForm);

	if (!hasError) {
	    regForm.submit();
	}
});