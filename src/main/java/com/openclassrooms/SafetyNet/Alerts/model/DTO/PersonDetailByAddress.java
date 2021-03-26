package com.openclassrooms.SafetyNet.Alerts.model.DTO;

import java.util.List;

public class PersonDetailByAddress {

    private String name;

    private String phone;

    private int age;

    private List<String> medications;

    private List<String> allergies;

    private int stationNb;

    public PersonDetailByAddress() {
    }
    public PersonDetailByAddress(String name, String phone, int age, List<String> medications, List<String> allergies, int stationNb) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
        this.stationNb = stationNb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public int getStationNb() {
        return stationNb;
    }

    public void setStationNb(int stationNb) {
        this.stationNb = stationNb;
    }
}
