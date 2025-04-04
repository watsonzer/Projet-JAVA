package fr.isep.algo;

import java.util.Date;

public class Reservation {
    private String numeroReservation;
    private Passager passager;
    private Vol vol;
    private Date dateReservation;
    private String classe;
    private double prix;
    private String statut;

    public Reservation(String numeroReservation, Passager passager, Vol vol,
                       String classe, double prix) {
        this.numeroReservation = numeroReservation;
        this.passager = passager;
        this.vol = vol;
        this.dateReservation = new Date();
        this.classe = classe;
        this.prix = prix;
        this.statut = "Confirmée";

        vol.ajouterReservation(this);
    }

    public String getNumeroReservation() { return numeroReservation; }
    public void setNumeroReservation(String numeroReservation) { this.numeroReservation = numeroReservation; }

    public Passager getPassager() { return passager; }
    public void setPassager(Passager passager) { this.passager = passager; }

    public Vol getVol() { return vol; }
    public void setVol(Vol vol) { this.vol = vol; }

    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String obtenirReservations() {
        return "Réservation n° " + numeroReservation +
                "\nPassager: " + passager.getNom() + " " + passager.getPrenom() +
                "\nVol: " + vol.getNumeroVol() + ", " + vol.getOrigine() + " -> " + vol.getDestination() +
                "\nDate de départ: " + vol.getDateDepart() +
                "\nClasse: " + classe +
                "\nPrix: " + prix +
                "\nStatut: " + statut;
    }
}
