package com.codeitforyou.dbmapper.type;

import com.codeitforyou.dbmapper.Column;
import com.codeitforyou.dbmapper.ObjectMapper;
import com.codeitforyou.dbmapper.TestClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class MySQLMapper {

    private Connection connectionSource;

    public MySQLMapper(String prefix, String host, int port, String database, String username, String password) {
        try {
            connectionSource = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            connectionSource.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void loadUser() {
        try {
            Statement stmt = connectionSource.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            ObjectMapper<TestClass> objectMapper = new ObjectMapper<TestClass>(TestClass.class);
            String testKey = objectMapper.map(rs).test;
        } catch (SQLException e) {

        }


    }
}
