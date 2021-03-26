package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Medicalrecords;
import com.openclassrooms.SafetyNet.Alerts.model.Person;

import java.util.List;

public class medicalRecord {

    public medicalRecord() {
    }

    public static String medicalRecordPost(Medicalrecords newMedicalrecords){
        List<Medicalrecords> medicalRecordList = DataAccess.loadJsonObject().getMedicalrecords();
        medicalRecordList.add(newMedicalrecords);
        DataAccess.loadJsonObject().setMedicalrecords(medicalRecordList);
        return "new Person add";
    }

    public static String medicalRecordPut(Medicalrecords newMedicalrecords){
        medicalRecordDelete(newMedicalrecords.getFirstName(),newMedicalrecords.getLastName());
        List<Medicalrecords> medicalRecordList = DataAccess.loadJsonObject().getMedicalrecords();
        medicalRecordList.add(newMedicalrecords);
        DataAccess.loadJsonObject().setMedicalrecords(medicalRecordList);
        return "new Person add";
    }

    public static String medicalRecordDelete(String firstName, String lastName){
        List<Medicalrecords> medicalRecordList = DataAccess.loadJsonObject().getMedicalrecords();
        Medicalrecords findMedicalrecords = medicalRecordList.stream().filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)).findFirst().orElse(null);
        int index = medicalRecordList.indexOf(findMedicalrecords);
        if(index != -1){
            medicalRecordList.remove(index);
            DataAccess.loadJsonObject().setMedicalrecords(medicalRecordList);
            return "person Deleted";
        }else{
            return firstName + "-" + lastName +" not found";
        }
    }

}
