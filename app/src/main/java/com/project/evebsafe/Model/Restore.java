package com.project.evebsafe.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restore {

    @SerializedName("Number")
    @Expose
    String Number;

    @SerializedName("Name")
    @Expose
    String Name;

    @SerializedName("Email")
    @Expose
    String Email;

    @SerializedName("Address")
    @Expose
    String Address;

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
