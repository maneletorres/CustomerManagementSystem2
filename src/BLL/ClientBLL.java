package BLL;

import DAO.ClientDAO;
import Entitat.Client;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 2DAM IES EL JUST
 */
public class ClientBLL {

    public ArrayList<Client> obtenirClients() throws Exception {
        try {
            ClientDAO cdao = new ClientDAO();
            return cdao.ObtenirClients();
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String maxCodi() throws Exception {
        ClientDAO cdao = new ClientDAO();
        return cdao.maxCodi();
    }

    public boolean insertClient(Client c) throws Exception {
        try {
            ClientDAO cdao = new ClientDAO();
            cdao.insertClient(c);
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean updateClient(Client c) throws Exception {
        try {
            ClientDAO cdao = new ClientDAO();
            cdao.updateClient(c);
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean deleteClient(Client c) throws Exception {
        try {
            ClientDAO cdao = new ClientDAO();
            cdao.deleteClient(c);
            return true;
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ArrayList<Client> filterClients(int selectedColumn, String searchText) throws Exception {
        try {
            ClientDAO cdao = new ClientDAO();
            return cdao.filterClients(selectedColumn, searchText);
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
