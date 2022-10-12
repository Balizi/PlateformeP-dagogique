package simplonClone;

import simplonClone.Apprenants;

import java.util.ArrayList;

public class Promos {

    private int idPromo;
    private int idApprenants;
    private String nomClasse;
    private String anneeScolaire;

    private ArrayList<Apprenants> apprenants = new ArrayList<Apprenants>();

    private Formateurs formateurs;

    public ArrayList<Apprenants> getApprenants() {
        return apprenants;
    }

    public void setApprenants(ArrayList<Apprenants> apprenants) {
        this.apprenants = apprenants;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public int getIdApprenants() {
        return idApprenants;
    }

    public void setIdApprenants(Apprenants Apprenants) {
        this.idApprenants = idApprenants;
    }

    public Formateurs getFormateurs() {
        return formateurs;
    }

    public void setFormateurs(Formateurs formateurs) {
        this.formateurs = formateurs;
    }

    public void addApprenantToPomo(Apprenants apprenants)
    {
        this.apprenants.add(apprenants);
    }
}
