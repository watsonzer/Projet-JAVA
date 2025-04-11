package fr.isep.algo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vol implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numeroVol;
    private String origine;
    private String destination;
    private String dateDepart;
    private String heureDepart;
    private String heureArrivee;
    private String duree;
    private double distance;
    private String statut; // "Planifié", "En vol", "Terminé", "Annulé", etc.

    private Avion avion;
    private Pilote pilote;
    private List<PersonnelCabine> equipageCabine;
    private List<Reservation> reservations;
    private int placesDisponiblesFirst;
    private int placesDisponiblesBusiness;
    private int placesDisponiblesEconomy;

    public Vol() {
        this.equipageCabine = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public Vol(String numeroVol, String origine, String destination, String dateDepart,
               String heureDepart, String heureArrivee, String duree, double distance) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.duree = duree;
        this.distance = distance;
        this.statut = "Planifié";
        this.equipageCabine = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Getters and Setters
    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
        if (avion != null) {
            this.placesDisponiblesFirst = avion.getCapaciteFirst();
            this.placesDisponiblesBusiness = avion.getCapaciteBusiness();
            this.placesDisponiblesEconomy = avion.getCapaciteEconomy();
        }
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
        if (pilote != null) {
            pilote.assignerVol(this);
        }
    }

    public List<PersonnelCabine> getEquipageCabine() {
        return equipageCabine;
    }

    public void ajouterPersonnelCabine(PersonnelCabine personnel) {
        this.equipageCabine.add(personnel);
        personnel.assignerVol(this);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void ajouterReservation(Reservation reservation) {
        this.reservations.add(reservation);
        // Mise à jour des places disponibles
        switch (reservation.getClasse()) {
            case "First":
                placesDisponiblesFirst--;
                break;
            case "Business":
                placesDisponiblesBusiness--;
                break;
            case "Economy":
                placesDisponiblesEconomy--;
                break;
        }
    }

    public boolean annulerReservation(String numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumeroReservation().equals(numeroReservation)) {
                reservations.remove(reservation);
                // Mise à jour des places disponibles
                switch (reservation.getClasse()) {
                    case "First":
                        placesDisponiblesFirst++;
                        break;
                    case "Business":
                        placesDisponiblesBusiness++;
                        break;
                    case "Economy":
                        placesDisponiblesEconomy++;
                        break;
                }
                return true;
            }
        }
        return false;
    }

    // Method to assign crew to the flight
    public void affecterEquipage(Pilote pilote, List<PersonnelCabine> equipage) {
        this.setPilote(pilote);
        for (PersonnelCabine personnel : equipage) {
            this.ajouterPersonnelCabine(personnel);
        }
    }

    // Method to get flight information
    public String obtenirVol() {
        StringBuilder sb = new StringBuilder();
        sb.append("Numéro de vol: ").append(numeroVol).append("\n");
        sb.append("Origine: ").append(origine).append("\n");
        sb.append("Destination: ").append(destination).append("\n");
        sb.append("Date de départ: ").append(dateDepart).append("\n");
        sb.append("Heure de départ: ").append(heureDepart).append("\n");
        sb.append("Heure d'arrivée: ").append(heureArrivee).append("\n");
        sb.append("Durée: ").append(duree).append("\n");
        sb.append("Distance: ").append(distance).append(" km\n");
        sb.append("Statut: ").append(statut).append("\n");

        sb.append("Avion: ");
        if (avion != null) {
            sb.append(avion.getImmatriculation()).append(" (").append(avion.getModele()).append(")\n");
        } else {
            sb.append("Non assigné\n");
        }

        sb.append("Pilote: ");
        if (pilote != null) {
            sb.append(pilote.getNom()).append(" ").append(pilote.getPrenom()).append("\n");
        } else {
            sb.append("Non assigné\n");
        }

        sb.append("Équipage de cabine: ");
        if (equipageCabine.isEmpty()) {
            sb.append("Non assigné\n");
        } else {
            sb.append("\n");
            for (PersonnelCabine personnel : equipageCabine) {
                sb.append("  - ").append(personnel.getNom()).append(" ").append(personnel.getPrenom()).append("\n");
            }
        }

        sb.append("Places disponibles:\n");
        sb.append("  - Première classe: ").append(placesDisponiblesFirst).append("\n");
        sb.append("  - Classe affaires: ").append(placesDisponiblesBusiness).append("\n");
        sb.append("  - Classe économique: ").append(placesDisponiblesEconomy).append("\n");

        return sb.toString();
    }

    // Method to cancel a flight
    public boolean annulerVol() {
        if (statut.equals("Terminé") || statut.equals("En vol")) {
            return false;
        }

        this.statut = "Annulé";

        // Free up the crew
        if (pilote != null) {
            pilote.retirerVol(this);
            pilote = null;
        }

        for (PersonnelCabine personnel : equipageCabine) {
            personnel.retirerVol(this);
        }
        equipageCabine.clear();

        // Free up the airplane
        if (avion != null) {
            avion.getVolsAssignes().remove(this);
            avion = null;
        }

        return true;
    }
}