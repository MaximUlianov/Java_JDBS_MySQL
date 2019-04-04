package com.bsuir.ief.lab4.backend.sql;

import com.bsuir.ief.lab4.backend.sql.db_configuration.Configs;
import com.bsuir.ief.lab4.backend.sql.db_configuration.DBConst;

import java.sql.*;

public class DBHandler implements Configs, DBConst {

    Connection connection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionStr = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionStr,
                dbUser, dbPassword);
        return connection;
    }

    public ResultSet showTable() throws ClassNotFoundException, SQLException{

        ResultSet resultSet = getDbConnection().createStatement().executeQuery("SELECT * FROM " + TABLE);

        return resultSet;
    }

    public void addInfo(String name, String dean,  int specialitiesQuantity,
                        int studentsQuantity, int teachersQuantity, String address) throws ClassNotFoundException, SQLException{

        String insertQuary = "INSERT INTO " + TABLE + " (" +
                NAME + ", " + DEAN + ", " +
                SPECIALITIES_QUANTITY + ", " + STUDENTS_QUANTITY + ", " +
                TEACHERS_QUANTITY + ", " + ADDRESS + ") VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDbConnection().prepareStatement(insertQuary);
        statement.setString(1, name);
        statement.setString(2, dean);
        statement.setInt(3, specialitiesQuantity);
        statement.setInt(4, studentsQuantity);
        statement.setInt(5, teachersQuantity);
        statement.setString(6, address);

        statement.executeUpdate();

    }

    public void deleteInfo(String name) throws ClassNotFoundException, SQLException{

        String deleteQuary = "Delete FROM " + TABLE + " WHERE name = ?";

        PreparedStatement statement = getDbConnection().prepareStatement(deleteQuary);
        statement.setString(1, name);

        statement.executeUpdate();
    }

    public void updateInfo(String oldName, String newName, String dean,
                           int specialitiesQuantity, int studentsQuantity, int teachersQuantity,
                           String address) throws ClassNotFoundException, SQLException{

        String updateQuery = "UPDATE " + TABLE + " SET " + NAME + " = ?, " +
                DEAN + " = ?, " + SPECIALITIES_QUANTITY + " =?, " +
                STUDENTS_QUANTITY + " =?, " + TEACHERS_QUANTITY + " =?, " +
                ADDRESS + " = ? WHERE " + NAME + " = ?";

        PreparedStatement statement = getDbConnection().prepareStatement(updateQuery);

        statement.setString(1, newName);
        statement.setString(2, dean);
        statement.setInt(3, specialitiesQuantity);
        statement.setInt(4, studentsQuantity);
        statement.setInt(5, teachersQuantity);
        statement.setString(6, address);
        statement.setString(7, oldName);

        statement.executeUpdate();
    }
}
