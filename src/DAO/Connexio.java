package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 2DAM IES EL JUST
 */
public class Connexio {

    protected Connection connection = null;
    protected ResultSet resultSet = null;
    protected Statement statement = null;
    protected String db = "lib/Veterinaria.sqlite";

    public void Connexio() {
    }

    // Constructor de clase que es conecta a la base de dades SQLite:
    public Connexio() throws Exception {
        System.out.println("Conectant");

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.db);
            System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new Exception("Error connectant");
        }
    }
}
