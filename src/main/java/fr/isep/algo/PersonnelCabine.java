package fr.isep.algo;

// Fichier: PersonnelCabine.java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonnelCabine extends Employe {
    private String certification;
    private List<String> languesParlees;

    public PersonnelCabine(String id, String nom, String prenom, String adresse, String telephone,
                           String email, String poste, double salaire, Date dateEmbauche,
                           String certification) {
        super(id, nom, prenom, adresse, telephone, email, poste, salaire, dateEmbauche);
        this.certification = certification;
        this.languesParlees = new ArrayList<>();
    }

    // Getters et Setters
    public String getCertification() { return certification; }
    public void setCertification(String certification) { this.certification = certification; }

    public List<String> getLanguesParlees() { return languesParlees; }
    public void ajouterLangue(String langue) {
        this.languesParlees.add(langue);
    }

    @Override
    public String obtenirRole() {
        return "Personnel de Cabine";
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + ", Certification: " + certification +
                ", Langues: " + String.join(", ", languesParlees);
    }
}
