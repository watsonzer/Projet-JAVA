package fr.isep.algo;

import java.util.Date;

public abstract class Employe extends Personne {
    protected String poste;
    protected double salaire;
    protected Date dateEmbauche;

    public Employe(String id, String nom, String prenom, String adresse, String telephone,
                   String email, String poste, double salaire, Date dateEmbauche) {
        super(id, nom, prenom, adresse, telephone, email);
        this.poste = poste;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public double getSalaire() { return salaire; }
    public void setSalaire(double salaire) { this.salaire = salaire; }

    public Date getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(Date dateEmbauche) { this.dateEmbauche = dateEmbauche; }

    public abstract String obtenirRole();

    @Override
    public String obtenirInfos() {
        return "Employé: " + id + ", " + nom + " " + prenom + ", Poste: " + poste;
    }
}
