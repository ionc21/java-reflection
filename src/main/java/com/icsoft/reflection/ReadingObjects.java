package com.icsoft.reflection;

import com.icsoft.reflection.beanmanager.BeanManager;
import com.icsoft.reflection.model.Person;
import com.icsoft.reflection.orm.EntityManager;
import com.icsoft.reflection.orm.ManagedEntityManager;

public class ReadingObjects {

    public static void main(String[] args) throws Exception {

        BeanManager beanManager = BeanManager.getInstance();
        EntityManager<Person> entityManager = beanManager.getInstance(ManagedEntityManager.class);

        Person linda = entityManager.find(Person.class, 1L);
        Person james = entityManager.find(Person.class, 2L);
        Person susan = entityManager.find(Person.class, 3L);
        Person john = entityManager.find(Person.class, 4L);

        System.out.println(linda);
        System.out.println(james);
        System.out.println(susan);
        System.out.println(john);
    }
}
