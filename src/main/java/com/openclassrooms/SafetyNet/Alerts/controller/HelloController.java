package com.openclassrooms.SafetyNet.Alerts.controller;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Get.PersonByStationNumber;
import com.openclassrooms.SafetyNet.Alerts.model.JsonObject;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    DataAccess dataAccess = new DataAccess();
    JsonObject json = dataAccess.loadJsonObject();

    @GetMapping(value = "/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<String>("Hello world", HttpStatus.FOUND);
    }
    @GetMapping(value = "/json")
    public List<JsonObject> jsonObjectList() {
        ArrayList<JsonObject> jsonArrayList = new ArrayList();
        jsonArrayList.add(json);
        return jsonArrayList;
    }
    @GetMapping(value = "/firestation")
    public String personList(@RequestParam("stationNumber") int stationNb) {
        PersonByStationNumber personByStationNumber = new PersonByStationNumber();
        List<Person> personList;
        int child = 0;
        int adult = 0;
        personList = DataAccess.getPersonsByFirestationNumber(stationNb);
        for(Person person : personList) {
            if(DataAccess.getAgeFromPerson(person) <= 18){
                child++;
            }else {
                adult++;
            }
        }
        personByStationNumber.setPersonChildAdult(personList,child,adult);
        return personByStationNumber.toString();
    }

}
