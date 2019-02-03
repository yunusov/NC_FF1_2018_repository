var enAlphabetPattern = /^[A-Z]+$/i;
var ruAlphabetPattern = /^[А-ЯЁ]+$/i;
var numberPattern = /^[0-9]+$/;

var passengerForm = document.querySelector('.passenger_form');
var firstName = passengerForm.querySelector('.firstName');
var lastName = passengerForm.querySelector('.lastName');
var middleName = passengerForm.querySelector('.middleName');
var sex = passengerForm.querySelector('.sex');
var birthday = passengerForm.querySelector('.birthday');
var citizenship = passengerForm.querySelector('.citizenship');
var documentNo = passengerForm.querySelector('.documentNo');
var documentExpiry = passengerForm.querySelector('.documentExpiry');

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

function checkSelectedOptions(form) {
    var selects = form.getElementsByTagName('select')
	for (var i = 0; i < selects.length; i++) {
        var selectedOption = selects[i].options.selectedIndex;
		if (selectedOption == 0) {
			showError(selects[i], 'Необходимо выбрать значения из списка');
		}
	}
}

// Passenger validation
passengerForm.addEventListener('submit', function(event) {
    event.preventDefault();
    cleanErrors(passengerForm);

    //First name validation
    if(!firstName.value.match(ruAlphabetPattern) && !firstName.value.match(enAlphabetPattern)) {
        showError(firstName, 'Имя должно состоять только из букв');
    }

     //Last name validation
     if(!lastName.value.match(ruAlphabetPattern) && !lastName.value.match(enAlphabetPattern)) {
        showError(lastName, 'Фамилия должна состоять только из букв');
     }

    //Middle name validation
    if(!middleName.value.match(ruAlphabetPattern) && !middleName.value.match(enAlphabetPattern)) {
        showError(middleName, 'Отчество должно состоять только из букв');
    }

    //Birthday validation
    if(Date.parse(birthday.value) > Date.now()) {
        showError(birthday, 'Дата рождения указано неверно');
    }

    //Document number validation
    if(!documentNo.value.match(numberPattern)) {
        showError(documentNo, 'Номер документа должен состоять из цифр');
    }
    else if (documentNo.value.length != 10) {
        showError(documentNo, 'Номер документа должен состоять из 10 цифр');
    }

    //Document expiry validation
    if(Date.parse(documentExpiry.value) < Date.now()) {
        showError(documentExpiry, 'Срок действия документа указан неверно');
    }

    checkSelectedOptions(passengerForm);
    checkEmptyFields(passengerForm);

	if (!hasError) {
	    passengerForm.submit();
	}
});