package people;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import people.generated.*;


public class JSONMarshaller {  	
	public static PeopleType people = new PeopleType();

	// Creating the 1st person with healthprofile

	public static void initializeDB() {
		PersonType laura = new PersonType();
		laura.setId(0002);
		laura.setFirstname("Laura");
		laura.setLastname("Pausini");
		laura.setBirthdate("1974-05-16T18:00:00.000+02:00");

		HealthProfileType hp = new HealthProfileType();
		hp.setLastupdate("2014-09-20T18:10:00.000+02:00");
		hp.setWeight(56);
		hp.setHeight(1.69);
		hp.setBmi(19.60);

		laura.setHealthprofile(hp);

		// Creating the 2nd person with healthprofile

		PersonType madonna = new PersonType();
		madonna.setId(0003);
		madonna.setFirstname("Madonna Louise Veronica");
		madonna.setLastname("Ciccone");
		madonna.setBirthdate("1958-08-16T18:00:00.000+02:00");

		HealthProfileType hp1 = new HealthProfileType();
		hp1.setLastupdate("2014-09-20T18:15:00.000+02:00");
		hp1.setWeight(50);
		hp1.setHeight(1.64);
		hp1.setBmi(18.60);

		madonna.setHealthprofile(hp1);

		// Creating the 3rd person with healthprofile

		PersonType gordon = new PersonType();
		gordon.setId(0004);
		gordon.setFirstname("Gordon");
		gordon.setLastname("Sumner");
		gordon.setBirthdate("1951-10-02T18:00:00.000+02:00");

		HealthProfileType hp2 = new HealthProfileType();
		hp2.setLastupdate("2014-09-20T18:20:00.000+02:00");
		hp2.setWeight(89);
		hp2.setHeight(1.76);
		hp2.setBmi(28.70);

		gordon.setHealthprofile(hp2);

		people.getPerson().add(laura);
		people.getPerson().add(madonna);
		people.getPerson().add(gordon);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
    }
}
