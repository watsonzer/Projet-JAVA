package fr.isep.algo;

import java.util.ArrayList;
import java.util.List;

public class Passager extends Person {
    private static final long serialVersionUID = 1L;

    private String numeroPasseport;
    private String nationalite;
    private List<Reservation> reservations;

    public Passager() {
        super();
        this.reservations = new ArrayList<>();
    }

    public Passager(String id, String nom, String prenom, String dateNaissance, String adresse,
                    String telephone, String email, String numeroPasseport, String nationalite) {
        super(id, nom, prenom, dateNaissance, adresse, telephone, email);
        this.numeroPasseport = numeroPasseport;
        this.nationalite = nationalite;
        this.reservations = new ArrayList<>();
    }

    // Getters and Setters
    public String getNumeroPasseport() {
        return numeroPasseport;
    }

    public void setNumeroPasseport(String numeroPasseport) {
        this.numeroPasseport = numeroPasseport;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // Method to reserve a flight
    public Reservation reserverVol(Vol vol, String classe, double prix) {
        Reservation reservation = new Reservation(generateReservationNumber(), this, vol, classe, prix);
        this.reservations.add(reservation);
        return reservation;
    }

    // Method to cancel a reservation
    public boolean annulerReservation(String numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumeroReservation().equals(numeroReservation)) {
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    // Method to get reservation by ID
    public Reservation obtenirReservation(String numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumeroReservation().equals(numeroReservation)) {
                return reservation;
            }
        }
        return null;
    }

    // Helper method to generate a reservation number
    private String generateReservationNumber() {
        // Simple implementation: combining passenger ID with timestamp
        return "R" + getId() + System.currentTimeMillis() % 10000;
    }

    @Override
    public String obtenirRole() {
        return "Passager";
    }

    @Override
    public String obtenirInfos() {
        StringBuilder sb = new StringBuilder(super.obtenirInfos());
        sb.append("\nNuméro de passeport: ").append(numeroPasseport);
        sb.append("\nNationalité: ").append(nationalite);

        sb.append("\nRéservations: ");
        if (reservations.isEmpty()) {
            sb.append("Aucune");
        } else {
            sb.append("\n");
            for (Reservation res : reservations) {
                sb.append("  - ").append(res.getNumeroReservation()).append(": ");
                sb.append(res.getVol().getOrigine()).append(" → ").append(res.getVol().getDestination());
                sb.append(" (").append(res.getVol().getDateDepart()).append(")\n");
            }
        }

        return sb.toString();
    }
}
