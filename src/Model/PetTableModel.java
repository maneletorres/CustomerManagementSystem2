package Model;

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

    private ArrayList petData = new ArrayList<>();
    private String[] petColumns = {
        "Id", "Nom", "Data naixement", "Especie", "Xip", "Comentaris"
    };

    private Class[] types = new Class[]{
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
                result = pet.getData_naixement();
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

    public void setPetData(ArrayList petData) {
        this.petData = petData;
    }

    public void addPet(Pet pet) {
        try {
            pet.setNum_id(new PetBLL().maxCodi());
            new PetBLL().insertPet(pet);

            // Add row:
            petData.add(pet);
            fireTableDataChanged();
        } catch (Exception ex) {
            System.out.println("No s'ha pogut inserir la mascota de nom " + pet.getNom() + ". ERROR: " + ex.getMessage());
        }
    }

    public String getMaxCodi() {
        try {
            return new PetBLL().maxCodi();
        } catch (Exception ex) {
            System.out.println("No s'ha pogut obtenir el codi màxim de les mascotes. ERROR: " + ex.getMessage());
            return null;
        }
    }

    public ArrayList getPetData(String codi_id) {
        try {
            return new PetBLL().obtenirMascotes(codi_id);
        } catch (Exception ex) {
            System.out.println("No s'han pogut obtenir les mascotes. ERROR: " + ex.getMessage());
            return null;
        }
    }
}
