package com.openclassrooms.SafetyNet.Alerts.model.Get;

import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.List;

public class PersonByStationNumber {
    private List<Person> person;
    private int child;
    private int adult;

    public void setPersonChildAdult(List<Person> person, int child,int adult) {
        this.person = person;
        this.child = child;
        this.adult = adult;
    }

    @Override
    public String toString() {
        return  person+
                "\nAdult=" + adult +
                "\nChild=" + child;
    }
}
