package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.ArrayList;
import java.util.List;

public class communityEmail {
    public communityEmail() {
    }

    public static List<String> mailListService(String city){
        List<String> mailList = new ArrayList<>();
        Person personFind;
        personFind = DataAccess.getPersonMailByCity(city);
        mailList.add(personFind.getEmail());
        return mailList;
    }
}
