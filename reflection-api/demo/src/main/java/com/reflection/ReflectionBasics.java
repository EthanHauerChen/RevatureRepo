package com.reflection;

public class ReflectionBasics {
    
    static class Person {
        int age;
        String name;
    }

    public static void main(String[] args) throws Exception {
        //Different ways to get the Class Object

        //1. using Class syntax
        Class<Person> personClass1 = Person.class;

        //2. using getClass on an instance
        Person p = new Person();
        Class<?> personClass2 = p.getClass();

        //3. using Class.forName()
        Class<?> personClass3 = Class.forName("ReflectionBasics$Person");


    }
}
