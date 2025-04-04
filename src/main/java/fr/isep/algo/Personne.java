package fr.isep.algo;

import java.util.ArrayList;
import java.util.List;

public abstract class Personne {
    protected String id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String telephone;
    protected String email;

    public Personne(String id, String nom, String prenom, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public abstract String obtenirInfos();
}
