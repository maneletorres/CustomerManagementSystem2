package Entitat;

/**
 *
 * @author 2DAM IES EL JUST
 */
public class Client {

    private String codi_id;
    private String dni;
    private String nom;
    private String carrer;
    private String codi_postal;
    private String n_de_portal;

    public String getCodi_id() {
        return codi_id;
    }

    public void setCodi_id(String codi_id) {
        this.codi_id = codi_id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public String getCodi_postal() {
        return codi_postal;
    }

    public void setCodi_postal(String codi_postal) {
        this.codi_postal = codi_postal;
    }

    public String getN_de_portal() {
        return n_de_portal;
    }

    public void setN_de_portal(String n_de_portal) {
        this.n_de_portal = n_de_portal;
    }

    @Override
    public String toString() {
        String result = "Número ID: " + codi_id + "\n";
        result += "D.N.I.: " + dni + "\n";
        result += "Nom: " + nom + "\n";
        result += "Carrer: " + carrer + "\n";
        result += "Codi postal: " + codi_postal + "\n";
        result += "Nº de portal: " + n_de_portal;
        return result;
    }
}
