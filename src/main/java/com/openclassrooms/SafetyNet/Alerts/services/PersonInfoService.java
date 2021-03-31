package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonDetailByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PersonInfoService {
    public PersonInfoService() {
    }

    public static List<PersonDetailByAddress> personDetailByFirestationService(String firstName, String lastName){
        List<PersonDetailByAddress> personDetailByAddress = new ArrayList<>();
        Person person = DataAccess.getPersonByFirstAndLastName(firstName,lastName);
        personDetailByAddress.add(new PersonDetailByAddress(person.getLastName(), person.getPhone(), DataAccess.getAgeFromPerson(person), DataAccess.getMedicalrecordsByPerson(person).getMedications(), DataAccess.getMedicalrecordsByPerson(person).getAllergies(), DataAccess.getStationByAddressFromPerson(person)));

        return personDetailByAddress;
    }
}
