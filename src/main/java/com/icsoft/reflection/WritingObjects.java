package com.icsoft.reflection;

import com.icsoft.reflection.beanmanager.BeanManager;
import com.icsoft.reflection.model.Person;
import com.icsoft.reflection.orm.EntityManager;
import com.icsoft.reflection.orm.ManagedEntityManager;

public class WritingObjects {

    public static void main(String[] args) throws Exception {

        BeanManager beanManager = BeanManager.getInstance();
        EntityManager<Person> entityManager = beanManager.getInstance(ManagedEntityManager.class);

        Person linda = new Person("Linda", 31);
        Person james = new Person("James", 24);
        Person susan = new Person("Susan", 34);
        Person john = new Person("John", 33);

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);

        System.out.println("Writing to DB");

        entityManager.persist(linda);
        entityManager.persist(james);
        entityManager.persist(susan);
        entityManager.persist(john);

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);
    }
}
