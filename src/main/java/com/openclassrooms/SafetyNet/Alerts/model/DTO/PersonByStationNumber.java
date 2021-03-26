package com.openclassrooms.SafetyNet.Alerts.model.DTO;

import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.List;

public class PersonByStationNumber {
    private List<Person> person;
    private int child;
    private int adult;

    public PersonByStationNumber() {
    }
    public PersonByStationNumber(List<Person> person, int child, int adult) {
        this.person = person;
        this.child = child;
        this.adult = adult;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }
}
