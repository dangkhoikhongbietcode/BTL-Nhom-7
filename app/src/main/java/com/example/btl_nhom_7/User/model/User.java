package com.example.btl_nhom_7.User.model;

public class User {
    private String phoneNumber;
    private String password;

    public User(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean checkValid8Char(){
        if (this.getPassword().length()>=8){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkValidCapsChar(){
        for (int i=0;i<this.getPassword().length();i++){
            if (Character.isUpperCase(this.getPassword().charAt(i))){
                return true;
            }
        }
        return false;
    }
    public boolean checkValidNumberChar(){
        for (int i=0;i<this.getPassword().length();i++){
            if (Character.isDigit(this.getPassword().charAt(i))){
                return true;
            }
        }
        return false;
    }
}
