package DAO;

import Entitat.Pet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class PetDAO extends Connexio {

    public PetDAO() throws Exception {
        super();
    }

    public ArrayList<Pet> obtenirMascotes(String codi_id) throws Exception {
        ArrayList<Pet> llistaMascotes = new ArrayList<>();
        try {
            statement = connection.createStatement();

            if (codi_id.equals("")) {
                resultSet = statement.executeQuery("SELECT * FROM animal; ");
            } else {
                resultSet = statement.executeQuery("SELECT * FROM animal WHERE codi_id = " + codi_id + "; ");
            }

            while (resultSet.next()) {
                Pet p = new Pet();
                p.setNum_id(resultSet.getString("num_id"));
                p.setNom(resultSet.getString("nom"));
                p.setData_naixement(resultSet.getDate("data_naixement"));
                p.setXip(resultSet.getString("chip"));
                p.setComentaris(resultSet.getString("comentaris"));
                p.setHistorial(resultSet.getString("historial"));
                p.setCodi_id(resultSet.getString("codi_id"));
                p.setData(resultSet.getDate("data"));
                p.setTractament(resultSet.getString("tractament"));
                p.setEspecie(resultSet.getString("especie"));

                llistaMascotes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new Exception("Error obtenint Mascotes");
        }
        return llistaMascotes;
    }

    public String maxCodi() throws SQLException, Exception {
        int valor = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT max(num_id) FROM animal; ");

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

    public boolean insertPet(Pet p) throws SQLException {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("Insert into animal (num_id,nom,data_naixement,chip,comentaris,historial,codi_id,data,tractament,especie) values (?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, p.getNum_id());
            ps.setString(2, p.getNom());
            ps.setDate(3, p.getData_naixement());
            ps.setString(4, p.getXip());
            ps.setString(5, p.getComentaris());
            ps.setString(6, p.getHistorial());
            ps.setString(7, p.getCodi_id());
            ps.setDate(8, p.getData());
            ps.setString(9, p.getTractament());
            ps.setString(10, p.getEspecie());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No s'ha pogut inserir la mascota. ERROR: " + ex.getMessage());
            throw new SQLException();
        }

        return true;
    }

    public boolean updatePet(Pet p) throws SQLException {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("update animal set nom = ?,data_naixement=?,chip = ?, comentaris=?,historial=?,codi_id=?,data=?,tractament=?,especie=? where num_id=?");

            ps.setString(1, p.getNom());
            ps.setDate(2, p.getData_naixement());
            ps.setString(3, p.getXip());
            ps.setString(4, p.getComentaris());
            ps.setString(5, p.getHistorial());
            ps.setString(6, p.getCodi_id());
            ps.setDate(7, p.getData());
            ps.setString(8, p.getTractament());
            ps.setString(9, p.getEspecie());
            ps.setString(10, p.getNum_id());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("No s'ha pogut actualitzar la mascota. ERROR: " + ex.getMessage());
            throw new SQLException();
        }

        return true;
    }

    public boolean deletePet(Pet p) throws SQLException {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("delete from animal where num_id = ?");
            ps.setString(1, p.getNum_id());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("No s'ha pogut eliminar la mascota. ERROR: " + ex.getMessage());
            throw new SQLException();
        }

        return true;
    }
}
