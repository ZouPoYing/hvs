package com.graduation.hvs.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EtityUtils {
    /**
     * @description: 实体类转Map
     */
    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(object);
                map.put(field.getName(), o);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

//    public static <T> T mapToEntity(List<Map<String, Object>> list, Class<T> entity) {
//        T t = null;
//        if (list.isEmpty()) {
//            return t;
//        }
//        for (Map<String, Object> map : list) {
//            t = mapToEntity(map, entity);
//        }
//    }

    /**
     * @description: Map转实体类
     */
    public static <T> T mapToEntity(Map<String, Object> map, Class<T> entity) {
        T t = null;
        try {
            t = entity.newInstance();
            for (Field field : entity.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                        field.set(t, object);
                    }
                    field.setAccessible(flag);
                }
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
