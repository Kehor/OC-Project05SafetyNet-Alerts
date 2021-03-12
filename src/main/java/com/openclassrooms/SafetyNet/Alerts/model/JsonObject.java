package com.openclassrooms.SafetyNet.Alerts.model;

import java.util.List;


public class JsonObject {


    private List<Person> persons;

    private List<Firestations> firestations;

    private List<Medicalrecords> medicalrecords;

    public JsonObject() {

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Firestations> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestations> firestations) {
        this.firestations = firestations;
    }

    public List<Medicalrecords> getMedicalrecords() {
        return medicalrecords;
    }


    public void setMedicalrecords(List<Medicalrecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "persons=" + persons +
                ", firestations=" + firestations +
                ", medicalrecords=" + medicalrecords +
                '}';
    }
    /*

    ObjectMapper objectMapper = new ObjectMapper();
    File jsonData = new File("src/main/resources/data.json");

    {
        try {
            System.out.println(jsonData);
            Map<?, ?> map = objectMapper.readValue(jsonData, Map.class);
            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */
}