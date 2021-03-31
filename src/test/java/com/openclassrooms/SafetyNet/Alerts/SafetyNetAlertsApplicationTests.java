package com.openclassrooms.SafetyNet.Alerts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.SafetyNet.Alerts.dto.DataAccess;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.ChildByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonByStationNumber;
import com.openclassrooms.SafetyNet.Alerts.model.DTO.PersonDetailByAddress;
import com.openclassrooms.SafetyNet.Alerts.model.Firestations;
import com.openclassrooms.SafetyNet.Alerts.model.JsonObject;
import com.openclassrooms.SafetyNet.Alerts.model.MedicalRecords;
import com.openclassrooms.SafetyNet.Alerts.model.Person;
import com.openclassrooms.SafetyNet.Alerts.services.*;
import com.openclassrooms.SafetyNet.Alerts.services.Flood.StationsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SafetyNetAlertsApplicationTests {

	@Autowired
	PersonService personService;
	PersonInfoService personInfoService;
	MedicalRecordService medicalRecordsService;
	FirestationService firestationService;
	FireService fireService;
	CommunityEmailService communityEmailService;
	ChildAlertService childAlertService;
	PhoneAlertService phoneAlertService;
	StationsService stationsService;
	private static DataAccess dataAccess = new DataAccess();
	private static JsonObject json;

	@BeforeAll
	private static void setUpJsonTest() {
		dataAccess.setJsonFile(new File("src/test/resources/data.json"));
		json = dataAccess.loadJsonObject();

		assertEquals(23, json.getPersons().size());
		assertEquals(13, json.getFirestations().size());
		assertEquals(23, json.getMedicalrecords().size());
	}

	@AfterAll
	public static void DeleteTest() {
		PersonService personService = new PersonService();
		personService.personDelete("test", "moi");

		MedicalRecordService medicalRecordService = new MedicalRecordService();
		medicalRecordService.medicalRecordDelete("test", "moi");

		FirestationService firestationService = new FirestationService();
		firestationService.firestationDelete("ici", 10);
	}

	@Test
	public void ParseAPerson_thenConvertItToJavaObject() throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String input = "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }";

		Person person = objectMapper.readValue(input, Person.class);

		assertEquals("John", person.getFirstName());
		assertEquals("Boyd", person.getLastName());
		assertEquals("1509 Culver St", person.getAddress());
		assertEquals("Culver", person.getCity());
		assertEquals("97451", person.getZip());
		assertEquals("841-874-6512", person.getPhone());
		assertEquals("jaboyd@email.com", person.getEmail());
	}

	@Test
	public void ParseAMedicalRecord_thenConvertItToJavaObject() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String input = "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }";

		MedicalRecords medicalRecord = objectMapper.readValue(input, MedicalRecords.class);

		assertEquals("John", medicalRecord.getFirstName());
		assertEquals("Boyd", medicalRecord.getLastName());
		assertEquals("03/06/1984", medicalRecord.getBirthdate());
		assertEquals("aznol:350mg", medicalRecord.getMedications().get(0));
		assertEquals("hydrapermazol:100mg", medicalRecord.getMedications().get(1));
		assertEquals("nillacilan", medicalRecord.getAllergies().get(0));
	}

	@Test
	public void ParseAFireStation_thenConvertItToJavaObject() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String input = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";

		Firestations fireStation = objectMapper.readValue(input, Firestations.class);

		assertEquals("1509 Culver St", fireStation.getAddress());
		assertEquals(3, fireStation.getStation());

	}

	@Test
	public void GetFromJsonPersonByStationNumber_thenVerify() {
		PersonByStationNumber personByStationNumber = firestationService.personByStationNumberService(1);

		assertEquals(6, personByStationNumber.getPerson().size());
		assertEquals(5, personByStationNumber.getAdult());
		assertEquals(1, personByStationNumber.getChild());

		assertEquals("Peter", personByStationNumber.getPerson().get(0).getFirstName());
		assertEquals("Duncan", personByStationNumber.getPerson().get(0).getLastName());
		assertEquals("Culver", personByStationNumber.getPerson().get(0).getCity());
		assertEquals("97451", personByStationNumber.getPerson().get(0).getZip());
	}

	@Test
	public void GetFromJsonChildByAddress_thenVerify() {
		ChildByAddress childByAddress = new ChildByAddress();
		childAlertService.setChildByAddress(DataAccess.getPersonsByAddress("1509 Culver St"),childByAddress);


		assertEquals(3, childByAddress.getAdult().size());
		assertEquals(2, childByAddress.getChild().size());

		assertEquals("John", childByAddress.getAdult().get(0).getFirstName());
		assertEquals("Boyd", childByAddress.getAdult().get(0).getLastName());
		assertEquals("Culver", childByAddress.getAdult().get(0).getCity());
		assertEquals("97451", childByAddress.getAdult().get(0).getZip());

		assertEquals("Tenley", childByAddress.getChild().get(0).getFirstName());
		assertEquals("Boyd", childByAddress.getChild().get(0).getLastName());
		assertEquals("Culver", childByAddress.getChild().get(0).getCity());
		assertEquals("97451", childByAddress.getChild().get(0).getZip());
	}

	@Test
	public void GetFromJsonPhonesNumbersByFirestationNumber_thenVerify() {
		List<String> phonenumber = phoneAlertService.getPhonesNumbersByFirestationNumberService(1);

		assertEquals(6, phonenumber.size());

		assertEquals("841-874-6512", phonenumber.get(0));
		assertEquals("841-874-8547", phonenumber.get(1));
		assertEquals("841-874-7462", phonenumber.get(2));
		assertEquals("841-874-7784", phonenumber.get(3));
	}

	@Test
	public void GetFromJsonPersonDetailByAddress_thenVerify() {
		List<PersonDetailByAddress> personDetailByAddress = fireService.personDetailByAddressService("1509 Culver St");

		assertEquals(5, personDetailByAddress.size());


		assertEquals("Boyd", personDetailByAddress.get(0).getName());
		assertEquals("841-874-6512", personDetailByAddress.get(0).getPhone());
		assertEquals(37, personDetailByAddress.get(0).getAge());
		assertEquals(2, personDetailByAddress.get(0).getMedications().size());
		assertEquals(1, personDetailByAddress.get(0).getAllergies().size());
		assertEquals(3, personDetailByAddress.get(0).getStationNb());
	}

	@Test
	public void GetFromJsonPersonDetailByFirestationNumberList_thenVerify() {
		List<PersonDetailByAddress> personDetailByAddress = stationsService.personDetailByFirestationService(Arrays.asList(1,2));

		assertEquals(11, personDetailByAddress.size());


		assertEquals("Duncan", personDetailByAddress.get(0).getName());
		assertEquals("841-874-6512", personDetailByAddress.get(0).getPhone());
		assertEquals(20, personDetailByAddress.get(0).getAge());
		assertEquals(0, personDetailByAddress.get(0).getMedications().size());
		assertEquals(1, personDetailByAddress.get(0).getAllergies().size());
		assertEquals(1, personDetailByAddress.get(0).getStationNb());
	}

	@Test
	public void GetFromJsonPersonDetailByFirstAndLastName_thenVerify() {
		List<PersonDetailByAddress> personDetailByAddress = personInfoService.personDetailByFirestationService("John", "Boyd");

		assertEquals(1, personDetailByAddress.size());


		assertEquals("Boyd", personDetailByAddress.get(0).getName());
		assertEquals("841-874-6512", personDetailByAddress.get(0).getPhone());
		assertEquals(37, personDetailByAddress.get(0).getAge());
		assertEquals(2, personDetailByAddress.get(0).getMedications().size());
		assertEquals(1, personDetailByAddress.get(0).getAllergies().size());
		assertEquals(3, personDetailByAddress.get(0).getStationNb());
	}

	@Test
	public void GetFromJsonMailListByCity_thenVerify() {
		List<String> mailList = communityEmailService.mailListService("Culver");

		assertEquals(1, mailList.size());


		assertEquals("jaboyd@email.com", mailList.get(0));
	}

	@Test
	public void PostPutDeletePerson_thenVerify() throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		String input = "{\"firstName\":\"test\", \"lastName\":\"moi\", \"address\":\"ici\", \"city\":\"Culver\", \"zip\":\"00000\", \"phone\":\"841-874-6512\", \"email\":\"moi@email.com\"}";

		Person person = objectMapper.readValue(input, Person.class);
		personService.personPost(person);
		Person personFind = DataAccess.getPersonByFirstAndLastName("test", "moi");

		assertEquals("test", personFind.getFirstName());
		assertEquals("moi", personFind.getLastName());
		assertEquals("ici", personFind.getAddress());
		assertEquals("Culver", personFind.getCity());
		assertEquals("00000", personFind.getZip());
		assertEquals("841-874-6512", personFind.getPhone());
		assertEquals("moi@email.com", personFind.getEmail());

		input = "{\"firstName\":\"test\", \"lastName\":\"moi\", \"address\":\"ici\", \"city\":\"Culver\", \"zip\":\"06660\", \"phone\":\"841-874-6512\", \"email\":\"test@email.com\"}";
		person = objectMapper.readValue(input, Person.class);
		personService.personPut(person);
		personFind = DataAccess.getPersonByFirstAndLastName("test", "moi");

		assertEquals("test", personFind.getFirstName());
		assertEquals("moi", personFind.getLastName());
		assertEquals("ici", personFind.getAddress());
		assertEquals("Culver", personFind.getCity());
		assertEquals("06660", personFind.getZip());
		assertEquals("841-874-6512", personFind.getPhone());
		assertEquals("test@email.com", personFind.getEmail());
	}

	@Test
	public void PostPutDeleteMedicalRecord_thenVerify() throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		String input = "{ \"firstName\":\"test\", \"lastName\":\"moi\", \"birthdate\":\"08/08/1800\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }";

		MedicalRecords medicalRecord = objectMapper.readValue(input, MedicalRecords.class);
		medicalRecordsService.medicalRecordPost(medicalRecord);
		MedicalRecords medicalRecordFind = DataAccess.getMedicalrecordsByPerson(DataAccess.getPersonByFirstAndLastName("test", "moi"));


		assertEquals(2, medicalRecord.getMedications().size());
		assertEquals(1, medicalRecord.getAllergies().size());

		assertEquals("test", medicalRecord.getFirstName());
		assertEquals("moi", medicalRecord.getLastName());
		assertEquals("08/08/1800", medicalRecord.getBirthdate());

		input = "{ \"firstName\":\"test\", \"lastName\":\"moi\", \"birthdate\":\"01/01/2021\", \"medications\":[\"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }";
		medicalRecord = objectMapper.readValue(input, MedicalRecords.class);
		medicalRecordsService.medicalRecordPost(medicalRecord);
		medicalRecordFind = DataAccess.getMedicalrecordsByPerson(DataAccess.getPersonByFirstAndLastName("test", "moi"));

		assertEquals(1, medicalRecord.getMedications().size());
		assertEquals(1, medicalRecord.getAllergies().size());

		assertEquals("test", medicalRecord.getFirstName());
		assertEquals("moi", medicalRecord.getLastName());
		assertEquals("01/01/2021", medicalRecord.getBirthdate());
	}

	@Test
	public void PostPutDeleteFirestationService_thenVerify() throws JsonProcessingException {


		firestationService.firestationPost("ici",10);
		Firestations firestationsFind = DataAccess.getFirestations()
				.stream()
				.filter(fireStation -> fireStation.getAddress().equals("ici"))
				.findFirst()
				.orElse(null);

		assertEquals("ici", firestationsFind.getAddress());
		assertEquals(10, firestationsFind.getStation());

		firestationService.firestationPut("ici",8);
		firestationsFind = DataAccess.getFirestations()
				.stream()
				.filter(fireStation -> fireStation.getAddress().equals("ici"))
				.findFirst()
				.orElse(null);

		assertEquals("ici", firestationsFind.getAddress());
		assertEquals(8, firestationsFind.getStation());
	}
}
