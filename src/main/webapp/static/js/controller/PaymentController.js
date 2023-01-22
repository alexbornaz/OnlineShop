import {CartController} from "./CartController.js";

export let PaymentController = {
    initPayment: function () {
        initPaymentButtons();
        initCardInputFields();
        initPaypalInputFields();
    },
    inputFields: {
        cardHolderName: null,
        cardNumber: null,
        expireDate: null,
        cvvField: null,
        paypalEmail: null,
        paypalPassword: null
    }
}

function initPaymentButtons() {
    let paypalPaymentButton = document.getElementById("paypalPayment");
    let cardPaymentButton = document.getElementById("cardPayment");

    paypalPaymentButton.addEventListener('click', payPalPayment);
    cardPaymentButton.addEventListener('click', cardPayment);
}

function initCardInputFields() {
    PaymentController.inputFields.cardHolderName = document.getElementById("cardHolderName");
    PaymentController.inputFields.cardHolderName.addEventListener('change', validateCardHolderName);

    PaymentController.inputFields.cardNumber = document.getElementById("cardNumber");
    PaymentController.inputFields.cardNumber.addEventListener('change', validateCardNumber);

    PaymentController.inputFields.expireDate = document.getElementById("expirationDate");
    PaymentController.inputFields.expireDate.addEventListener('change', validateExpireDate);

    PaymentController.inputFields.cvvField = document.getElementById("cvv");
    PaymentController.inputFields.cvvField.addEventListener('change', validateCVV);
}

function initPaypalInputFields() {
    PaymentController.inputFields.paypalEmail = document.getElementById("paypalEmail");
    PaymentController.inputFields.paypalEmail.addEventListener('change', validatePaypalEmail);

    PaymentController.inputFields.paypalPassword = document.getElementById("paypalPassword");
    PaymentController.inputFields.paypalPassword.addEventListener('change', validatePaypalPassword);
}

function cardPayment() {
    if (validateCardHolderName() &&
        validateCardNumber() &&
        validateExpireDate() &&
        validateCVV()) {
        $('#paymentModal').modal('hide');
        alert("Thank you for your order!");
        CartController.clearCart();
    }
}

function payPalPayment() {
    if (validatePaypalPassword() && validatePaypalEmail())
    {
        $('#paymentModal').modal('hide');
        alert("Thank you for your order!");
        CartController.clearCart();
    }
}

function validateCardHolderName() {
    const validate = /^((?:[A-Za-z]+ ?){1,3})$/;
    return validateInputField(validate, PaymentController.inputFields.cardHolderName);
}

function validateCardNumber() {
    const validate = /^(?:5[1-5][0-9]{14})$/;
    return validateInputField(validate, PaymentController.inputFields.cardNumber);
}

function validateExpireDate() {
    const validate = /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/;
    return validateInputField(validate, PaymentController.inputFields.expireDate);
}

function validateCVV() {
    const validate = /^[0-9]{3,4}$/;
    return validateInputField(validate, PaymentController.inputFields.cvvField);
}

function validatePaypalEmail() {
    const validate = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    return validateInputField(validate, PaymentController.inputFields.paypalEmail);
}

function validatePaypalPassword() {
    const validate = /^.{1,35}$/;
    return validateInputField(validate, PaymentController.inputFields.paypalPassword);
}

function validateInputField(expression, inputField) {
    if (inputField.value.match(expression)) {
        inputField.classList.remove("invalid");
        return true;
    } else {
        inputField.classList.add("invalid");
        return false;
    }
}