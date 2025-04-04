package fr.isep.algo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vol {
    private String numeroVol;
    private String origine;
    private String destination;
    private Date dateDepart;
    private Date dateArrivee;
    private Avion avion;
    private List<Employe> equipage;
    private List<Reservation> reservations;
    private String statut; // par exemple: "Planifié", "En vol", "Terminé", "Annulé"

    public Vol(String numeroVol, String origine, String destination,
               Date dateDepart, Date dateArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.equipage = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.statut = "Planifié";
    }

    public String getNumeroVol() { return numeroVol; }
    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol; }

    public String getOrigine() { return origine; }
    public void setOrigine(String origine) { this.origine = origine; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Date getDateDepart() { return dateDepart; }
    public void setDateDepart(Date dateDepart) { this.dateDepart = dateDepart; }

    public Date getDateArrivee() { return dateArrivee; }
    public void setDateArrivee(Date dateArrivee) { this.dateArrivee = dateArrivee; }

    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }

    public List<Employe> getEquipage() { return equipage; }
    public List<Reservation> getReservations() { return reservations; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public boolean affecterEquipage(List<Employe> nouveauEquipage) {
        boolean hasPilote = false;
        boolean hasPersonnelCabine = false;

        for (Employe employe : nouveauEquipage) {
            if (employe instanceof Pilote) {
                hasPilote = true;
            } else if (employe instanceof PersonnelCabine) {
                hasPersonnelCabine = true;
            }
        }

        if (hasPilote && hasPersonnelCabine) {
            this.equipage = nouveauEquipage;
            return true;
        }
        return false;
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public boolean annulerVol() {
        if (!statut.equals("Terminé") && !statut.equals("En vol")) {
            statut = "Annulé";

            for (Reservation reservation : reservations) {
                reservation.setStatut("Annulé");
            }

            return true;
        }
        return false;
    }

    public String obtenirVol() {
        return "Vol n° " + numeroVol + ": " + origine + " -> " + destination +
                ", Départ: " + dateDepart + ", Arrivée: " + dateArrivee +
                ", Statut: " + statut;
    }
}
