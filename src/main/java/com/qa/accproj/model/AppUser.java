package com.qa.accproj.model;

public class AppUser {
    
    private int accountNumber;
    private String firstName;
    private String lastName;
 
    public AppUser(int accountNumber, String firstName, String lastName) {
        super();

        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;

    }
 
    public int getAccountNumber() {
        return accountNumber;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
