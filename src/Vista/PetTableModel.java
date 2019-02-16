package Vista;

import BLL.PetBLL;
import Entitat.Pet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class PetTableModel extends AbstractTableModel {

    ArrayList petData = new ArrayList<>();
    String[] petColumns = {
        "Id", "Nom", "Data naixement", "Especie", "Xip", "Comentaris"
    };

    Class[] types = new Class[]{
        java.lang.String.class, java.lang.String.class, java.lang.String.class,
        java.lang.String.class, java.lang.String.class, java.lang.String.class
    };

    public PetTableModel() {
        petData = new ArrayList<>();
    }

    public PetTableModel(String codi_id) {
        try {
            petData = (ArrayList<Pet>) new PetBLL().obtenirMascotes(codi_id);
        } catch (Exception ex) {
            Logger.getLogger(ClientTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnName(int col) {
        return petColumns[col];
    }

    @Override
    public int getRowCount() {
        return petData.size();
    }

    @Override
    public int getColumnCount() {
        return petColumns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Pet pet = (Pet) petData.get(row);

        Object result = null;
        switch (col) {
            case 0:
                result = pet.getNum_id();
                break;
            case 1:
                result = pet.getNom();
                break;
            case 2:
                result = pet.getData();
                break;
            case 3:
                result = pet.getEspecie();
                break;
            case 4:
                result = pet.getXip();
                break;
            case 5:
                result = pet.getComentaris();
                break;
        }
        return result;
    }

    public void addPet(Pet pet) {
        if (pet.getNum_id() == null) {
            try {
                pet.setNum_id(new PetBLL().maxCodi());
                new PetBLL().insertPet(pet);

                // Add row:
                petData.add(pet);
                fireTableDataChanged();
            } catch (Exception ex) {
                System.out.println("No s'ha pogut inserir la mascota. ERROR: " + ex.getMessage());
            }
        }
    }
}
