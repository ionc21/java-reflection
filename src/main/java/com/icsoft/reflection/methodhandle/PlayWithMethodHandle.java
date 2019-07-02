package com.icsoft.reflection.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import com.icsoft.reflection.model.Person;

public class PlayWithMethodHandle {

	public static void main(String[] args) throws Throwable {

            Lookup lookup = MethodHandles.lookup();

            MethodType emptyConstructorMethodtype = MethodType.methodType(void.class);
            MethodHandle emptyConstructor = lookup.findConstructor(Person.class, emptyConstructorMethodtype);

            Person p = (Person)emptyConstructor.invoke();
            System.out.println(p);

            MethodType constructorMethodtype = MethodType.methodType(void.class, String.class, int.class);
            MethodHandle constructor = lookup.findConstructor(Person.class, constructorMethodtype);

            Person p2 = (Person)constructor.invoke("James", 28);
            System.out.println(p2);

            MethodType nameGetterMethodType = MethodType.methodType(String.class);
            MethodHandle nameGetter = lookup.findVirtual(Person.class, "getName", nameGetterMethodType);

            String name = (String)nameGetter.invoke(p2);
            System.out.println(name);

            MethodType nameSetterMethodType = MethodType.methodType(void.class, String.class);
            MethodHandle nameSetter = lookup.findVirtual(Person.class, "setName", nameSetterMethodType);

            nameSetter.invoke(p2, "Linda");
            System.out.println(p2);

            Lookup privateLookup = MethodHandles.privateLookupIn(Person.class, lookup);
            MethodHandle nameReader = privateLookup.findGetter(Person.class, "name", String.class);
            String name2 = (String)nameReader.invoke(p2);
            System.out.println(name2);

        }
}
