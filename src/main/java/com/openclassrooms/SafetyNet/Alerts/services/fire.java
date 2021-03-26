package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonDetailByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.ArrayList;
import java.util.List;

public class fire {
    public fire() {
    }

    public static List<PersonDetailByAddress> personDetailByAddressService(String address){
        List<Person> personList = DataAccess.getPersonsByAddress(address);
        List<PersonDetailByAddress> personDetailByAddress = new ArrayList<>();
        for(Person person : personList) {
            personDetailByAddress.add(new PersonDetailByAddress(person.getLastName(),person.getPhone(),DataAccess.getAgeFromPerson(person),DataAccess.getMedicalrecordsByPerson(person).getMedications(),DataAccess.getMedicalrecordsByPerson(person).getAllergies(),DataAccess.getStationByAddressFromPerson(person)));
        }
        return personDetailByAddress;
    }
}
