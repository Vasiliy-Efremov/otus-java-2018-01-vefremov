package ru.otus.l91;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Executor {

    public <T extends DataSet> void save(T user) throws Exception {
        String query = "INSERT INTO [DataSet](name, age) VALUES (?, ?)";
        DBService serviceConnection = new DBService();
        PreparedStatement preparedStatement = serviceConnection.getConnection().prepareStatement(query);

        Field[] fields = user.getClass().getDeclaredFields();
        String name = "";
        int age = 0;

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals("name")) {
                name = (String) fields[i].get(user);
            }
            if (fields[i].getName().equals("age")) {
                age = (Integer) fields[i].get(user);
            }
        }
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, age);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        serviceConnection.close();
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws Exception {
        String query = ("SELECT * FROM [DataSet] WHERE ID = (?)");
        DBService serviceConnection = new DBService();
        PreparedStatement preparedStatement = serviceConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet executeQuery = preparedStatement.executeQuery();

        String name = "";
        int age = 0;
        while (executeQuery.next()) {
            name = executeQuery.getString("name");
            age = Integer.parseInt(executeQuery.getString("age"));
        }

        UserDataSet userDataSet = (UserDataSet) clazz.newInstance();
        userDataSet.setName(name);
        userDataSet.setAge(age);

        preparedStatement.close();
        serviceConnection.close();
        return (T) userDataSet;
    }
}
