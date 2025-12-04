package com.example.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerializationExample {
    public static void main(String[] args) {
        String filename = "person.ser";

        serializePerson(filename);

        deserializePerson(filename);
    }

    private static void serializePerson(String filename) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Person person = new Person("John Doe", 30, "secret123", new Date());

            //serialize object
            oos.writeObject(person);
            System.out.println("Serialized: " + person);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializePerson(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            //Read object
            Person p1 = (Person) ois.readObject();
            System.out.println("Deserialized person: " + p1);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
