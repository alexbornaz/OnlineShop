import {Customer} from "../model/Customer.js";

export let CheckOutController = {
    inputEmail: null,
    inputFirstName: null,
    inputLastName: null,
    inputAddress1: null,
    inputAddress2: null,
    inputCity: null,
    inputZipCode: null,

    checkOutButton: null,
    customer: null,
    initCheckOutController: function () {
        CheckOutController.inputEmail = document.getElementById("inputEmail");
        CheckOutController.inputFirstName = document.getElementById("inputFirstName");
        CheckOutController.inputLastName = document.getElementById("inputLastName");
        CheckOutController.inputAddress1 = document.getElementById("inputAddress");
        CheckOutController.inputAddress2 = document.getElementById("inputAddress2");
        CheckOutController.inputCity = document.getElementById("inputCity");
        CheckOutController.inputZipCode = document.getElementById("inputZip");
        CheckOutController.checkOutButton = document.getElementById("checkoutButton");
        CheckOutController.checkOutButton.addEventListener('click', CheckOutController.startCheckOut);
    },
    startCheckOut: function () {
        if (validateInputFields()) {
            CheckOutController.customer = new Customer(
                CheckOutController.inputEmail.value,
                CheckOutController.inputFirstName.value,
                CheckOutController.inputLastName.value,
                CheckOutController.inputAddress1.value,
                CheckOutController.inputAddress2.value,
                CheckOutController.inputCity.value,
                CheckOutController.inputZipCode.value);
            $('#cartModal').modal('hide');
            $('#paymentModal').modal('show');
        } else {
            alert("Invalid inputs!");
        }
    }
}

function validateInputFields() {
    return validateEmailInputField() &&
        validateFirstNameInputField() &&
        validateLastNameInputField() &&
        validateAddress1InputField() &&
        validateCityInputField() &&
        validateZipCodeInputField();
}

function validateEmailInputField() {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(CheckOutController.inputEmail.value);

}

function validateFirstNameInputField() {
    return CheckOutController.inputFirstName.value.length >= 2;

}

function validateLastNameInputField() {
    return CheckOutController.inputLastName.value.length >= 2;

}

function validateAddress1InputField() {
    return CheckOutController.inputAddress1.value.length >= 2;

}

function validateCityInputField() {
    return CheckOutController.inputCity.value.length >= 2;

}

function validateZipCodeInputField() {
    if (CheckOutController.inputZipCode.value.length === 4) {
        const zipCode = parseInt(CheckOutController.inputZipCode.value);
        if (!isNaN(zipCode)) {
            if (zipCode >= 1000 && zipCode <= 9999) {
                return true;
            }
        }
    }
    return false;
}