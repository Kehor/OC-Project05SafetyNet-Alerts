package com.openclassrooms.SafetyNet.Alerts.model.DTO;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.ArrayList;
import java.util.List;

public class ChildByAddress {
    private List<Person> adult = new ArrayList<>();
    private List<Person> child = new ArrayList<>();

    public ChildByAddress() {
    }
    public ChildByAddress(List<Person> adult, List<Person> child) {
        this.adult = adult;
        this.child = child;
    }



    public List<Person> getAdult() {
        return adult;
    }

    public void setAdult(List<Person> adult) {
        this.adult = adult;
    }

    public List<Person> getChild() {
        return child;
    }

    public void setChild(List<Person> child) {
        this.child = child;
    }
}
