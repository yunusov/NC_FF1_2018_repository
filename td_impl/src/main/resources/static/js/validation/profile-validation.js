var emailPattern = /^[a-zA-Z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([a-zA-Z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([a-zA-Z])+$/;
var passwordPattern = /^[a-zA-Z0-9\*\$\_]+$/;
var hasError = false;

// Account form
var accountForm = document.querySelector('.account_info');
var email = accountForm.querySelector('.email');

// Password form
var passwordForm = document.querySelector('.password_change');
var oldPassword = passwordForm.querySelector('.oldPassword');
var password = passwordForm.querySelector('.newPassword');
var confirmPassword = passwordForm.querySelector('.confirmPassword');

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

// Account info validation
accountForm.addEventListener('submit', function(event) {
    event.preventDefault();
    cleanErrors(accountForm);

	// Email validation
	if (!email.value.match(emailPattern)) {
		showError(email, 'Неправильный формат E-mail');
	}

    checkEmptyFields(accountForm);

	if (!hasError) {
	    accountForm.submit();
	}
});

// Changing password validation
passwordForm.addEventListener('submit', function(event) {
    event.preventDefault();
    cleanErrors(passwordForm);

    // Passwords validation
	if (newPassword.value.length < 4) {
		showError(password, 'Пароль должен содержать не менее 4 символов');
	}
	else if (newPassword.value.length > 16) {
		showError(password, 'Пароль должен содержать не более 16 символов');
	}
	else if (!newPassword.value.match(passwordPattern)) {
		showError(password, 'Пароль может содержать только латинские буквы, цифры и символы: _*$');
	}
	else if (newPassword.value !== confirmPassword.value) {
		showError(password, 'Пароли не совпадают');
		showError(confirmPassword, 'Пароли не совпадают');
	}

    checkEmptyFields(passwordForm);

	if (!hasError) {
	    passwordForm.submit();
	}
});