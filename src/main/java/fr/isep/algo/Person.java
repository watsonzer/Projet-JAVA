package fr.isep.algo;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresse;
    private String telephone;
    private String email;

    public Person() {
    }

    public Person(String id, String nom, String prenom, String dateNaissance, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Required methods
    public String obtenirInfos() {
        return "ID: " + id +
                "\nNom: " + nom +
                "\nPrénom: " + prenom +
                "\nDate de naissance: " + dateNaissance +
                "\nAdresse: " + adresse +
                "\nTéléphone: " + telephone +
                "\nEmail: " + email;
    }

    public abstract String obtenirRole();
}