package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.ArrayList;
import java.util.List;

public class phoneAlert {
    public phoneAlert() {
    }

    public static List getPersonsByFirestationNumberService(int firestationNb){
        List<Person> personList = DataAccess.getPersonsByFirestationNumber(firestationNb);
        List phonenumber = new ArrayList<>();
        for(Person person : personList) {
            phonenumber.add(person.getPhone());
        }
        return phonenumber;
    }
}
