package DAO;

import Entitat.Client;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 2DAM IES EL JUST
 */
public class ClientDAO extends Connexio {

    public ClientDAO() throws Exception {
        super();
    }

    public ArrayList<Client> ObtenirClients() throws Exception {
        ArrayList<Client> llistaClients = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client; ");

            while (resultSet.next()) {
                Client c = new Client();
                c.setCodi_id(resultSet.getString("codi_id"));
                c.setDni(resultSet.getString("dni"));
                c.setNom(resultSet.getString("nom"));
                c.setCarrer(resultSet.getString("carrer"));
                c.setCodi_postal(resultSet.getString("codi_postal"));
                c.setN_de_portal(resultSet.getString("n_de_portal"));

                llistaClients.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new Exception("Error obtenint Clients");
        }
        return llistaClients;
    }

    public String maxCodi() throws SQLException, Exception {
        int valor = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT max(codi_id) FROM client; ");

            while (resultSet.next()) {
                String val = resultSet.getString(1);
                valor = Integer.parseInt(val);
                valor++;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new Exception("Error obtenint el maxim codi");
        }

        return Integer.toString(valor);
    }

    public boolean insertClient(Client c) throws SQLException, Exception {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("Insert into client (codi_id,dni,nom,carrer,codi_postal,n_de_portal) values (?,?,?,?,?,?)");

            ps.setString(1, c.getCodi_id());
            ps.setString(2, c.getDni());
            ps.setString(3, c.getNom());
            ps.setString(4, c.getCarrer());
            ps.setString(5, c.getCodi_postal());
            ps.setString(6, c.getN_de_portal());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception("No s'ha pogut inserir el client");
        }

        return true;
    }

    public boolean updateClient(Client c) throws SQLException {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("update client set dni = ?,nom=?,carrer = ?, codi_postal=?,n_de_portal=? where codi_id=?");

            ps.setString(1, c.getDni());
            ps.setString(2, c.getNom());
            ps.setString(3, c.getCarrer());
            ps.setString(4, c.getCodi_postal());
            ps.setString(5, c.getN_de_portal());
            ps.setString(6, c.getCodi_id());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new SQLException();
        }

        return true;
    }

    public boolean deleteClient(Client c) throws SQLException {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("delete from client where codi_id = ?");
            ps.setString(1, c.getCodi_id());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new SQLException();
        }

        return true;
    }

    public ArrayList<Client> filterClients(int selectedColumn, String searchText) throws Exception {
        ArrayList<Client> llistaClients = new ArrayList<>();
        try {
            String columnToSearch = "";
            switch (selectedColumn) {
                case 0:
                    columnToSearch = "codi_id";
                    break;
                case 1:
                    columnToSearch = "dni";
                    break;
                case 2:
                    columnToSearch = "nom";
                    break;
                case 3:
                    columnToSearch = "carrer";
                    break;
                case 4:
                    columnToSearch = "codi_postal";
                    break;
                case 5:
                    columnToSearch = "n_de_portal";
                    break;
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client WHERE " + columnToSearch + " LIKE '%" + searchText + "%'; ");

            while (resultSet.next()) {
                Client c = new Client();
                c.setCodi_id(resultSet.getString("codi_id"));
                c.setDni(resultSet.getString("dni"));
                c.setNom(resultSet.getString("nom"));
                c.setCarrer(resultSet.getString("carrer"));
                c.setCodi_postal(resultSet.getString("codi_postal"));
                c.setN_de_portal(resultSet.getString("n_de_portal"));

                llistaClients.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new Exception("Error obtenint Clients");
        }
        return llistaClients;
    }
}
