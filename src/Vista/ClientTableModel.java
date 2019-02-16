package Vista;

import BLL.ClientBLL;
import Entitat.Client;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class ClientTableModel extends AbstractTableModel {

    private boolean isCellEditable;
    private boolean isClientEditing;
    ArrayList clientData = new ArrayList<>();
    ArrayList auxClientData = new ArrayList<>();
    String[] clientColumns = {
        "Codi ID", "DNI", "Nom", "Carrer", "Codi postal", "Nº de portal"
    };

    Class[] types = new Class[]{
        java.lang.String.class, java.lang.String.class, java.lang.String.class,
        java.lang.String.class, java.lang.String.class, java.lang.String.class
    };

    public ClientTableModel(boolean isCellEditable) {
        try {
            this.isCellEditable = isCellEditable;
            this.isClientEditing = false;
            clientData = (ArrayList<Client>) new ClientBLL().obtenirClients();
        } catch (Exception ex) {
            Logger.getLogger(ClientTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnName(int col) {
        return clientColumns[col];
    }

    @Override
    public int getRowCount() {
        return clientData.size();
    }

    @Override
    public int getColumnCount() {
        return clientColumns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Client client = (Client) clientData.get(row);

        Object result = null;
        switch (col) {
            case 0:
                result = client.getCodi_id();
                break;
            case 1:
                result = client.getDni();
                break;
            case 2:
                result = client.getNom();
                break;
            case 3:
                result = client.getCarrer();
                break;
            case 4:
                result = client.getCodi_postal();
                break;
            case 5:
                result = client.getN_de_portal();
                break;
        }
        return result;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0) {
            return false;
        }
        return isCellEditable;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value.toString().equals("")) {
            System.out.println("No pots introduïr un valor buit per a una cel.la.");
            return;
        }

        Client client = (Client) clientData.get(row);
        if (client.getCodi_id() == null) { // INSERT
            switch (col) {
                case 1:
                    client.setDni(value.toString());
                    break;
                case 2:
                    client.setNom(value.toString());
                    break;
                case 3:
                    client.setCarrer(value.toString());
                    break;
                case 4:
                    client.setCodi_postal(value.toString());
                    break;
                case 5:
                    client.setN_de_portal(value.toString());
                    break;
            }

            if (checkClient(client)) {
                try {
                    ClientBLL cbll = new ClientBLL();
                    client.setCodi_id(cbll.maxCodi());
                    cbll.insertClient(client);
                    isClientEditing = false;
                } catch (Exception ex) {
                    System.out.println("No s'ha pogut inserir el client. ERROR: " + ex.getMessage());
                }
            } else {
                isClientEditing = true;
            }
        } else { // UPDATE

            switch (col) {
                case 1:
                    client.setDni(value.toString());
                    break;
                case 2:
                    client.setNom(value.toString());
                    break;
                case 3:
                    client.setCarrer(value.toString());
                    break;
                case 4:
                    client.setCodi_postal(value.toString());
                    break;
                case 5:
                    client.setN_de_portal(value.toString());
                    break;
            }

            if (checkClient(client)) {
                try {
                    ClientBLL cbll = new ClientBLL();
                    cbll.updateClient(client);
                    isClientEditing = false;
                } catch (Exception ex) {
                    System.out.println("No s'ha pogut actualitzar el client. ERROR: " + ex.getMessage());
                }
            } else {
                isClientEditing = true;
                System.out.println("Has d'omplir totes les columnes per actualitzar un nou client.");
            }
        }

        fireTableDataChanged();
    }

    public boolean getIsClientEditing() {
        return isClientEditing;
    }

    public void addClient(Client client, int row) {
        if (checkClient(client)) {
            try {
                client.setCodi_id(new ClientBLL().maxCodi());

                new ClientBLL().insertClient(client);
                isClientEditing = false;
            } catch (Exception ex) {
                System.out.println("No s'ha pogut inserir el client. ERROR: " + ex.getMessage());
            }
        } else {
            isClientEditing = true;
        }

        // Add row:
        clientData.add(client);
        fireTableDataChanged();
    }

    public void removeRow(int row) {
        Client client = (Client) clientData.get(row);

        try {
            new ClientBLL().deleteClient(client);

            clientData.remove(client);
            fireTableDataChanged();
        } catch (Exception ex) {
            System.out.println("No s'ha pogut eliminar el client. ERROR: " + ex.getMessage());
        }
    }

    // Borrar la comprobació dels equals:
    public boolean checkClient(Client client) {
        return client.getDni() != null && !client.getDni().equals("") && client.getNom() != null && !client.getNom().equals("")
                && client.getCarrer() != null && !client.getCarrer().equals("") && client.getCodi_postal() != null && !client.getCodi_postal().equals("")
                && client.getN_de_portal() != null && !client.getN_de_portal().equals("");
    }

    // Mètodes per al filtrat de clients:
    // Alternative 1:
    public void updateData(String searchText) {
        // Primera lletra.
        if (auxClientData.isEmpty()) {
            auxClientData = (ArrayList<Client>) clientData.clone();
        }

        if (searchText.length() == 0) { // Búsqueda des de zero.
            clientData.removeAll(clientData);
            clientData = (ArrayList<Client>) auxClientData.clone();
        } else {
            for (int i = 0; i < auxClientData.size(); i++) {
                Client c = (Client) auxClientData.get(i);

                if (!c.getNom().toLowerCase().contains(searchText.toLowerCase())) {
                    clientData.remove(c);
                }
            }
        }

        fireTableDataChanged();
    }

    // Alternative 2:
    public void performSearch(int selectedColumn, String searchText) {
        try {
            clientData = (ArrayList<Client>) new ClientBLL().filterClients(selectedColumn, searchText);
            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(ClientTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
