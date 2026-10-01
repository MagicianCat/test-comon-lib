package com.test.common.utils;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassUtils {
    public static Class<?> getPaternClass(Class<?> c) {
        ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
        Type type = pt.getActualTypeArguments()[0];
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }

        return (Class<?>) ((ParameterizedType) type).getRawType();
    }

    public static List<Class<?>> getPaternClasses(Class<?> c) {
        ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
        Type[] types = pt.getActualTypeArguments();

        List<Class<?>> classList = Lists.newArrayList();
        for (Type type : types) {
            if (type instanceof Class<?>) {
                classList.add((Class<?>) type);
            } else {
                classList.add((Class<?>) ((ParameterizedType) type).getRawType());
            }
        }

        return classList;
    }
}
