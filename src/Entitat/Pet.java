package Entitat;

import java.sql.Date;

/**
 *
 * @author Manuel Espinosa Torres
 */
public class Pet {

    private String num_id;
    private String nom;
    private Date data_naixement;
    private String xip;
    private String comentaris;
    private String historial;
    private String codi_id;
    private Date data;
    private String tractament;
    private String especie;

    public String getNum_id() {
        return num_id;
    }

    public void setNum_id(String num_id) {
        this.num_id = num_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getData_naixement() {
        return data_naixement;
    }

    public void setData_naixement(Date data_naixment) {
        this.data_naixement = data_naixment;
    }

    public String getXip() {
        return xip;
    }

    public void setXip(String xip) {
        this.xip = xip;
    }

    public String getComentaris() {
        return comentaris;
    }

    public void setComentaris(String comentaris) {
        this.comentaris = comentaris;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getCodi_id() {
        return codi_id;
    }

    public void setCodi_id(String codi_id) {
        this.codi_id = codi_id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTractament() {
        return tractament;
    }

    public void setTractament(String Tractament) {
        this.tractament = Tractament;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        String result = "Número ID: " + num_id + "\n";
        result += "Nom: " + nom + "\n";
        result += "Especie: " + especie + "\n";
        result += "Comentaris: " + comentaris + "\n";
        result += "Client ID: " + codi_id + "\n";
        result += "Xip: " + xip + "\n";
        result += "Data de naixement: " + data_naixement.toString() + "\n";
        result += "Tractament: " + tractament + "\n";

        return result;
    }
}
