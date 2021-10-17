package com.codeitforyou.dbmapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper<T> {

    public Class clazz;

    public ObjectMapper(Class clazz) {
        this.clazz = clazz;
    }

    public T map(ResultSet rs) {
        try {
            while (rs.next()) {
                T dto = (T) clazz.getConstructor().newInstance();
                Field[] fields = dto.getClass().getDeclaredFields();

                for (Field field : fields) {
                    Column col = field.getAnnotation(Column.class);
                    if (col != null) {
                        String name = col.key();
                        try {
                            String value = rs.getString(name);
                            field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                return dto;
            }
        } catch (NoSuchMethodException e) {

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
