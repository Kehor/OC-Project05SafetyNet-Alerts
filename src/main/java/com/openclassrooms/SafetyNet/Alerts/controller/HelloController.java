package com.openclassrooms.SafetyNet.Alerts.controller;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.ChildByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonByStationNumber;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonDetailByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.JsonObject;
import com.openclassrooms.SafetyNet.Alerts.model.Medicalrecords;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import com.openclassrooms.SafetyNet.Alerts.services.*;
import com.openclassrooms.SafetyNet.Alerts.services.flood.stations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PersonByStationNumber> personByStationNumber(@RequestParam("stationNumber") int stationNb) {
        PersonByStationNumber personByStationNumber = firestation.personByStationNumberService(stationNb);
        return new ResponseEntity<>(personByStationNumber,HttpStatus.OK);
    }

    @GetMapping(value = "/childAlert")
    public ResponseEntity<ChildByAddress> childByAddress(@RequestParam("address") String address) {
        ChildByAddress childByAddress = new ChildByAddress();
        childAlert.setChildByAddress(DataAccess.getPersonsByAddress(address),childByAddress);
        return new ResponseEntity<>(childByAddress,HttpStatus.OK);
    }

    @GetMapping(value = "/phoneAlert")
    public ResponseEntity<List> phonenumberList(@RequestParam("firestation") int firestationNb) {
        List phonenumber = phoneAlert.getPersonsByFirestationNumberService(firestationNb);
        return new ResponseEntity<>(phonenumber,HttpStatus.OK);
    }

    @GetMapping(value = "/fire")
    public ResponseEntity<List<PersonDetailByAddress>> personDetailByAddress(@RequestParam("address") String address) {
        List<PersonDetailByAddress> personDetailByAddress = fire.personDetailByAddressService(address);
        return new ResponseEntity<>(personDetailByAddress,HttpStatus.OK);
    }

    @GetMapping(value = "/flood/stations")
    public ResponseEntity<List<PersonDetailByAddress>> personDetailByFirestation(@RequestParam("stations") List<Integer> stationNb) {
        List<PersonDetailByAddress> personDetailByAddress = stations.personDetailByFirestationService(stationNb);
        return new ResponseEntity<>(personDetailByAddress,HttpStatus.OK);
    }

    @GetMapping(value = "/personInfo")
    public ResponseEntity<List<PersonDetailByAddress>> personDetailByFirestation(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName) {
        List<PersonDetailByAddress> personDetailByAddress = personInfo.personDetailByFirestationService(firstName, lastName);
        return new ResponseEntity<>(personDetailByAddress,HttpStatus.OK);
    }

    @GetMapping(value = "/communityEmail")
    public ResponseEntity<List<String>> mailList(@RequestParam("city") String city) {
        List<String> mailList = communityEmail.mailListService(city);
        return new ResponseEntity<>(mailList, HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity<String> postPerson(@RequestBody Person newPerson){
        String response = person.personPost(newPerson);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/person")
    public ResponseEntity<String> putPerson(@RequestBody Person newPerson){
        String response = person.personPut(newPerson);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/person")
    public ResponseEntity<String> deletePerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        String response = person.personDelete(firstName, lastName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/firestation")
    public ResponseEntity<String> postFirestation(@RequestParam("address") String address, @RequestParam("station") int station){
        String response = firestation.firestationPost(address,station);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/firestation")
    public ResponseEntity<String> putFirestation(@RequestParam("address") String address, @RequestParam("station") int station){
        String response = firestation.firestationPut(address,station);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/firestation")
    public ResponseEntity<String> deleteFirestation(@RequestParam("address") String address, @RequestParam("station") int station){
        String response = firestation.firestationDelete(address,station);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<String> postMedicalRecord(@RequestBody Medicalrecords newMedicalrecords){
        String response = medicalRecord.medicalRecordPost(newMedicalrecords);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/medicalRecord")
    public ResponseEntity<String> putMedicalRecord(@RequestBody Medicalrecords newMedicalrecords){
        String response = medicalRecord.medicalRecordPut(newMedicalrecords);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/medicalRecord")
    public ResponseEntity<String> deleteMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        String response = medicalRecord.medicalRecordDelete(firstName,lastName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
