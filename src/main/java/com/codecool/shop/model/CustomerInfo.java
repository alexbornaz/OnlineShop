package com.codecool.shop.model;

public class CustomerInfo {
    private String firstName;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String cardName;
    private String cardNumber;
    private String expDate;
    private String cvv;

    public CustomerInfo(String firstname, String email, String address,
                        String city, String state, String zip, String cardname,
                        String cardnumber, String expDate, String cvv) {
        this.firstName = firstname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.cardName = cardname;
        this.cardNumber = cardnumber;
        this.expDate = expDate;
        this.cvv = cvv;
    }


    public void printAll(){
        System.out.println(firstName+"-"+email+"-"+address+"-"+city+"-"+state+"-"+zip+"-"+cardName+"-"+cardNumber+"-"+expDate+"-"+cvv);
    }
}
