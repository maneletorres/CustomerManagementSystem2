package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class Connexio {

    protected Connection connection = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    protected String db = "lib/Veterinaria.sqlite";

    public Connexio() throws Exception {
        System.out.println("Connectant...");

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.db);
            System.out.println("Connectat a la base de dades SQLite [ " + this.db + "].");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new Exception("Error connectant amb la base de dades.");
        }
    }
}
