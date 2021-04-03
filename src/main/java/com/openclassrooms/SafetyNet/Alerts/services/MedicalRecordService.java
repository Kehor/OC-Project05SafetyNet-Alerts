package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.MedicalRecords;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicalRecordService {

    public MedicalRecordService() {
    }

    public static String medicalRecordPost(MedicalRecords newMedicalrecords){
        List<MedicalRecords> medicalRecordList = DataAccess.loadJsonObject().getMedicalrecords();
        medicalRecordList.add(newMedicalrecords);
        DataAccess.loadJsonObject().setMedicalrecords(medicalRecordList);
        System.out.print("\n"+newMedicalrecords);
        return "new Person add";
    }

    public static String medicalRecordPut(MedicalRecords newMedicalrecords){
        medicalRecordDelete(newMedicalrecords.getFirstName(),newMedicalrecords.getLastName());
        List<MedicalRecords> medicalRecordList = DataAccess.loadJsonObject().getMedicalrecords();
        medicalRecordList.add(newMedicalrecords);
        DataAccess.loadJsonObject().setMedicalrecords(medicalRecordList);
        System.out.print("\n"+newMedicalrecords);
        return "new Person add";
    }

    public static String medicalRecordDelete(String firstName, String lastName){
        List<MedicalRecords> medicalRecordList = DataAccess.loadJsonObject().getMedicalrecords();
        MedicalRecords findMedicalrecords = medicalRecordList.stream().filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)).findFirst().orElse(null);
        int index = medicalRecordList.indexOf(findMedicalrecords);
        if(index != -1){
            medicalRecordList.remove(index);
            DataAccess.loadJsonObject().setMedicalrecords(medicalRecordList);
            System.out.print("\n"+findMedicalrecords);
            return "person Deleted";
        }else{
            return firstName + "-" + lastName +" not found";
        }
    }

}
