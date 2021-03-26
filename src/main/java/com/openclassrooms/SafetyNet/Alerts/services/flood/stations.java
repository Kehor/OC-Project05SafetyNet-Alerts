package com.openclassrooms.SafetyNet.Alerts.services.flood;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonDetailByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.ArrayList;
import java.util.List;

public class stations {
    public stations() {
    }

    public static List<PersonDetailByAddress> personDetailByFirestationService(List<Integer> stationNb){
        List<PersonDetailByAddress> personDetailByAddress = new ArrayList<>();
        for(int station : stationNb) {
            List<Person> personList = DataAccess.getPersonsByFirestationNumber(station);
            for (Person person : personList) {
                personDetailByAddress.add(new PersonDetailByAddress(person.getLastName(), person.getPhone(), DataAccess.getAgeFromPerson(person), DataAccess.getMedicalrecordsByPerson(person).getMedications(), DataAccess.getMedicalrecordsByPerson(person).getAllergies(), DataAccess.getStationByAddressFromPerson(person)));
            }
        }
        return personDetailByAddress;
    }
}
