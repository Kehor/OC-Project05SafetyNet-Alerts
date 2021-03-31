package com.openclassrooms.SafetyNet.Alerts.model;

import java.util.List;


public class JsonObject {


    private List<Person> persons;

    private List<Firestations> firestations;

    private List<MedicalRecords> medicalrecords;

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

    public List<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }


    public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
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
}