package pl.training.module07;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JsonXmlConverter {

    public static void main(String[] args) throws Exception {
        Person person = new Person("Alice", 30);

        // JSON Serialization
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonString = jsonMapper.writeValueAsString(person);
        System.out.println("JSON String:\n" + jsonString);

        // JSON Deserialization
        Person personFromJson = jsonMapper.readValue(jsonString, Person.class);
        System.out.println("Deserialized from JSON:\n" + personFromJson);

        // XML Serialization
        XmlMapper xmlMapper = new XmlMapper();
        String xmlString = xmlMapper.writeValueAsString(person);
        System.out.println("XML String:\n" + xmlString);

        // XML Deserialization
        Person personFromXml = xmlMapper.readValue(xmlString, Person.class);
        System.out.println("Deserialized from XML:\n" + personFromXml);
    }
}

class Person {

    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }

}