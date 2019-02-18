package BLL;

import DAO.PetDAO;
import Entitat.Pet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class PetBLL {

    public ArrayList<Pet> obtenirMascotes(String codi_id) throws Exception {
        try {
            PetDAO pdao = new PetDAO();
            return pdao.obtenirMascotes(codi_id);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String maxCodi() throws Exception {
        PetDAO pdao = new PetDAO();
        return pdao.maxCodi();
    }

    public boolean insertPet(Pet p) throws Exception {
        try {
            PetDAO pdao = new PetDAO();
            pdao.insertPet(p);
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean updatePet(Pet p) throws Exception {
        try {
            PetDAO pdao = new PetDAO();
            pdao.updatePet(p);
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean deletePet(Pet p) throws Exception {
        try {
            PetDAO pdao = new PetDAO();
            pdao.deletePet(p);
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
