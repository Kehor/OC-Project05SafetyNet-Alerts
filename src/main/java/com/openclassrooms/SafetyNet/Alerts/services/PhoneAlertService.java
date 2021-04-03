package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PhoneAlertService {
    public PhoneAlertService() {
    }

    public static List getPhonesNumbersByFirestationNumberService(int firestationNb){
        List<Person> personList = DataAccess.getPersonsByFirestationNumber(firestationNb);
        List<String> phonenumber = new ArrayList<>();
        for(Person person : personList) {
            phonenumber.add(person.getPhone());
        }
        System.out.print("Phone number : "+phonenumber);
        return phonenumber;
    }
}
