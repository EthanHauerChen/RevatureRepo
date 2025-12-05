package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectInspector {
    //inspect any object and prints its structure

    public static void inspectObject(Object obj) {
        Class<?> clazz = obj.getClass();

        
        System.out.println("Inspecting: " + clazz.getSimpleName());
        System.out.println("Full Class name: " + clazz.getName());
        System.out.println("Package: " + clazz.getPackage().getName());
        System.out.println("Superclass: " + clazz.getSuperclass().getSimpleName());


        //Fields
        System.out.println("---Fields---");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String modifiers = Modifier.toString(field.getModifiers());
            System.out.printf(" %s %s %s - %s%n", modifiers, field.getType().getSimpleName(), field.getName(), field.get(obj));
        }
        
    }
}
