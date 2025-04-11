package fr.isep.algo;

public class Employe extends Person {
    private static final long serialVersionUID = 1L;

    private String matricule;
    private String dateEmbauche;
    private String poste;
    private double salaire;

    public Employe() {
        super();
    }

    public Employe(String id, String nom, String prenom, String dateNaissance, String adresse,
                   String telephone, String email, String matricule, String dateEmbauche,
                   String poste, double salaire) {
        super(id, nom, prenom, dateNaissance, adresse, telephone, email);
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.poste = poste;
        this.salaire = salaire;
    }

    // Getters and Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    @Override
    public String obtenirRole() {
        return poste;
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() +
                "\nMatricule: " + matricule +
                "\nDate d'embauche: " + dateEmbauche +
                "\nPoste: " + poste +
                "\nSalaire: " + salaire;
    }
}