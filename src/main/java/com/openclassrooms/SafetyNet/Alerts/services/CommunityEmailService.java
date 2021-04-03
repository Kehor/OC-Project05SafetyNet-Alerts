package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommunityEmailService {
    public CommunityEmailService() {
    }

    public static List<String> mailListService(String city){
        List<String> mailList = new ArrayList<>();
        List<Person> personFind;
        personFind = DataAccess.getPersonByCity(city);
        for (Person person : personFind){
            if (person != null){
                mailList.add(person.getEmail());
                System.out.println(person.getEmail());
            }
        }
        return mailList;
    }
}
