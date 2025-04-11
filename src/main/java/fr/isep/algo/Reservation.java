package fr.isep.algo;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numeroReservation;
    private Passager passager;
    private Vol vol;
    private String dateReservation;
    private String classe; // "First", "Business", "Economy"
    private double prix;
    private String statut; // "Confirmée", "Annulée", "En attente"

    public Reservation() {
        this.dateReservation = getCurrentDateTime();
        this.statut = "Confirmée";
    }

    public Reservation(String numeroReservation, Passager passager, Vol vol, String classe, double prix) {
        this.numeroReservation = numeroReservation;
        this.passager = passager;
        this.vol = vol;
        this.dateReservation = getCurrentDateTime();
        this.classe = classe;
        this.prix = prix;
        this.statut = "Confirmée";

        // Ajouter cette réservation au vol
        vol.ajouterReservation(this);
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    // Getters and Setters
    public String getNumeroReservation() {
        return numeroReservation;
    }

    public void setNumeroReservation(String numeroReservation) {
        this.numeroReservation = numeroReservation;
    }

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    // Method to obtain reservation information
    public String obtenirReservations() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numéro de réservation: ").append(numeroReservation).append("\n");
        sb.append("Passager: ").append(passager.getNom()).append(" ").append(passager.getPrenom()).append("\n");
        sb.append("Vol: ").append(vol.getNumeroVol()).append("\n");
        sb.append("  - ").append(vol.getOrigine()).append(" → ").append(vol.getDestination()).append("\n");
        sb.append("  - Date: ").append(vol.getDateDepart()).append("\n");
        sb.append("  - Heure: ").append(vol.getHeureDepart()).append("\n");
        sb.append("Date de réservation: ").append(dateReservation).append("\n");
        sb.append("Classe: ").append(classe).append("\n");
        sb.append("Prix: ").append(prix).append(" €\n");
        sb.append("Statut: ").append(statut).append("\n");

        return sb.toString();
    }

    // Method to cancel this reservation
    public boolean annulerReservation() {
        if (statut.equals("Annulée")) {
            return false;
        }

        statut = "Annulée";
        vol.annulerReservation(numeroReservation);
        return true;
    }
}