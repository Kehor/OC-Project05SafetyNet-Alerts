package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.ChildByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChildAlertService {

    public ChildAlertService() {
    }

    public static void setChildByAddress(List<Person> personList, ChildByAddress childByAddress) {
        List<Person> adult = new ArrayList<>();
        List<Person> child = new ArrayList<>();
        for(Person person : personList) {
            if(DataAccess.getAgeFromPerson(person) <= 18){
                child.add(person);
            }else {
                adult.add(person);
            }
        }
        childByAddress.setChild(child);
        childByAddress.setAdult(adult);
        System.out.print("\nAdult : "+childByAddress.getAdult());
        System.out.print("\nChild : "+childByAddress.getChild());
    }
}
