package com.openclassrooms.SafetyNet.Alerts.services;

import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonService {
    public PersonService() {
    }

    public String personPost(Person newPerson){
        List<Person> personList = DataAccess.loadJsonObject().getPersons();
        personList.add(newPerson);
        DataAccess.loadJsonObject().setPersons(personList);
        return "new Person add";
    }

    public String personPut(Person newPerson){
        personDelete(newPerson.getFirstName(),newPerson.getLastName());
        List<Person> personList = DataAccess.loadJsonObject().getPersons();
        personList.add(newPerson);
        DataAccess.loadJsonObject().setPersons(personList);
        return "new Person add";
    }

    public String personDelete(String firstName, String lastName){
        List<Person> personList = DataAccess.loadJsonObject().getPersons();
        Person findPerson = personList.stream().filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)).findFirst().orElse(null);
        int index = personList.indexOf(findPerson);
        if(index != -1){
            personList.remove(index);
            DataAccess.loadJsonObject().setPersons(personList);
            return "person Deleted";
        }else{
            return firstName + "-" + lastName +" not found";
        }
    }

}
