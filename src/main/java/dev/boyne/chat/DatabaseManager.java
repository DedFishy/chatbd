package dev.boyne.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    Statement statement;
    Connection connection;

    public void prepareDatabase() {
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            //statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table if not exists users (username string, passwordhash string, passwordsalt string, pfp string)");
            statement.executeUpdate("create table if not exists groups (name string, photo string)");
            statement.executeUpdate("create table if not exists messages (groupname string, username string, content string)");
            statement.executeUpdate("create table if not exists usergroups (username string, groupname string)");
            //ResultSet rs = statement.executeQuery("select * from person");
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }
}
