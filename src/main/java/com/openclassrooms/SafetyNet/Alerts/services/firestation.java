package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonByStationNumber;
import com.openclassrooms.SafetyNet.Alerts.model.Firestations;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.List;

public class firestation {
    public firestation() {
    }

    public static PersonByStationNumber personByStationNumberService(int stationNb){
        PersonByStationNumber personByStationNumber = new PersonByStationNumber(DataAccess.getPersonsByFirestationNumber(stationNb),0,0);
        for(Person person : personByStationNumber.getPerson()) {
            if(DataAccess.getAgeFromPerson(person) <= 18){
                personByStationNumber.setChild(personByStationNumber.getChild()+1);
            }else {
                personByStationNumber.setAdult(personByStationNumber.getAdult()+1);
            }
        }
        return personByStationNumber;
    }

    public static String firestationPost(String address, int station){
        List<Firestations> firestationsList = DataAccess.loadJsonObject().getFirestations();
        Firestations newFirestation = new Firestations(address,station);
        firestationsList.add(newFirestation);
        DataAccess.loadJsonObject().setFirestations(firestationsList);
        return "new Firestation add";
    }

    public static String firestationPut(String address, int station){
        List<Firestations> firestationsList = DataAccess.loadJsonObject().getFirestations();
        Firestations newFirestation = new Firestations(address,station);
        firestationsList.add(newFirestation);
        DataAccess.loadJsonObject().setFirestations(firestationsList);
        firestationDelete(newFirestation.getAddress(),firestationsList.stream().filter(firestation -> firestation.getAddress().equals(address) && firestation.getStation()!=station).findFirst().orElse(null).getStation());
        return "new Firestation ";
    }

    public static String firestationDelete(String address, int station){
        List<Firestations> firestationsList = DataAccess.loadJsonObject().getFirestations();
        Firestations findFirestation = firestationsList.stream().filter(firestation -> firestation.getAddress().equals(address) && firestation.getStation()==station).findFirst().orElse(null);
        int index = firestationsList.indexOf(findFirestation);
        if(index != -1){
            firestationsList.remove(index);
            DataAccess.loadJsonObject().setFirestations(firestationsList);
            return "Firestation Deleted";
        }else{
            return address + "-" + station +" not found";
        }
    }
}
