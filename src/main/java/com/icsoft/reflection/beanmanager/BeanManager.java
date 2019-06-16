package com.icsoft.reflection.beanmanager;

import com.icsoft.reflection.anotation.Inject;
import com.icsoft.reflection.anotation.Provides;
import com.icsoft.reflection.provider.H2ConnectionProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BeanManager {

    private static BeanManager instance = new BeanManager();

    public static BeanManager getInstance() {
        return instance;
    }

    private Map<Class<?>, Supplier<?>> registry = new HashMap<>();

    private BeanManager() {
        List<Class<?>> classes = List.of(H2ConnectionProvider.class);
        for (Class<?> clss : classes) {
            Method[] methods = clss.getDeclaredMethods();
            for (Method method : methods) {
                Provides provides = method.getAnnotation(Provides.class);
                if (provides != null) {
                    Class<?> returnType = method.getReturnType();
                    Supplier<?> supplier = () -> {
                        try {
                            if (!Modifier.isStatic(method.getModifiers())) {
                                Object o = clss.getConstructor().newInstance();
                                return method.invoke(o);
                            } else {
                                return method.invoke(null);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    };
                    registry.put(returnType, supplier);
                }
            }
        }
    }

    public <T> T getInstance(Class<T> clss) {

        try {
            T t = clss.getConstructor().newInstance();

            Field[] fields = clss.getDeclaredFields();
            for (Field field : fields) {
                Inject inject = field.getAnnotation(Inject.class);
                if (inject != null) {
                    Class<?> injectedFieldType = field.getType();
                    Supplier<?> supplier = registry.get(injectedFieldType);
                    Object objectToInject = supplier.get();
                    field.setAccessible(true);
                    field.set(t, objectToInject);
                }
            }

            return t;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
