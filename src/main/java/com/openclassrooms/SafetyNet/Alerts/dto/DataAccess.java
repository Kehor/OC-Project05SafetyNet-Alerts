package com.openclassrooms.SafetyNet.Alerts.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNet.Alerts.model.Firestations;
import com.openclassrooms.SafetyNet.Alerts.model.JsonObject;
import com.openclassrooms.SafetyNet.Alerts.model.Medicalrecords;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DataAccess {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static File jsonFile = new File("src/main/resources/data.json");

    private static JsonObject jsonobject;


    public static JsonObject loadJsonObject() {
        if (jsonobject != null) {
            //System.out.println("JSON already mapped");
            return jsonobject;
        }

        try {
            jsonobject = objectMapper.readValue(jsonFile, JsonObject.class);
            System.out.println("JSON mapped");
        } catch (IOException e) {
            System.out.println("Error JSON mapping");
            e.printStackTrace();
        }
        return jsonobject;
    }

    public static List<Firestations> getFirestations() {
        return new ArrayList<>(loadJsonObject().getFirestations());
    }

    public static List<Person> getPersons() {
        return new ArrayList<>(loadJsonObject().getPersons());
    }

    public List<Medicalrecords> getMedicalrecords() {
        return new ArrayList<>(loadJsonObject().getMedicalrecords());
    }

    public static int getStationByAddressFromPerson(Person person) {
        if (person != null) {
            loadJsonObject();
            return getFirestations()
                    .stream()
                    .filter(fireStation -> person.getAddress().equals(fireStation.getAddress()))
                    .findFirst()
                    .map(Firestations::getStation)
                    .orElse(0);
        }
        return 0;
    }

    public static List<Person> getPersonsByFirestationNumber(int firestationNumber) {
        List<Person> result = new ArrayList<>();

        for (Person person : loadJsonObject().getPersons()) {
            if (getStationByAddressFromPerson(person) == firestationNumber) {
                result.add(person);
            }
        }
        return result;
    }


    public static int getAgeFromBirthdate(String birthdate) {
        LocalDate currentDate = LocalDate.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            formatter = formatter.withLocale(Locale.FRANCE);
            LocalDate birthDate = LocalDate.parse(birthdate, formatter);
            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            System.out.println("Birthdate not valid.");
        } catch (RuntimeException e) {
            System.out.println("Birthdate not valid.");
        }
        return 0;
    }

    public static int getAgeFromPerson(Person person) {
        if (person != null) {
            for (Medicalrecords medicalrecords : loadJsonObject().getMedicalrecords()) {
                if (Objects.equals(person.getFirstName(), medicalrecords.getFirstName()) &&
                        Objects.equals(person.getLastName(), medicalrecords.getLastName())) {
                    return getAgeFromBirthdate(medicalrecords.getBirthdate());
                }
            }
        }
        return 0;
    }

    public static List<Person> getPersonsByAddress(String address) {
        List<Person> result = new ArrayList<>();
        if (address != null) {
            for (Person person : loadJsonObject().getPersons()) {
                if (person.getAddress().equals(address)) {
                    result.add(person);
                }
            }
            System.out.println(result);
            return result;
        }
        return result;
    }

    public static Medicalrecords getMedicalrecordsByPerson(Person person) {
        if (person != null) {
            for (Medicalrecords medicalrecords : loadJsonObject().getMedicalrecords()) {
                if (Objects.equals(person.getFirstName(), medicalrecords.getFirstName()) &&
                        Objects.equals(person.getLastName(), medicalrecords.getLastName())) {
                    return medicalrecords;
                }
            }
        }
        return null;
    }

    public static Person getPersonByFirstAndLastName(String firstName, String lastName) {
        return getPersons()
                .stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public static Person getPersonMailByCity(String city) {
        return getPersons()
                .stream()
                .filter(person -> person.getCity().equals(city))
                .findFirst()
                .orElse(null);
    }
}