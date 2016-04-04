package model;

import enums.Categories;
import enums.Titles;

/**
 * Created by robert on 04.04.16.
 */
public class Person {
    private String name, surname, email, address, phoneNo;
    private Categories category;
    private Titles title;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Titles getTitle() {
        return title;
    }

    public void setTitle(Titles title) {
        this.title = title;
    }
}
