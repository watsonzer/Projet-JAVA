package fr.isep.algo;

import java.util.ArrayList;
import java.util.List;

public class Passager extends Personne {
    private String passeportNum;
    private String nationalite;
    private List<Reservation> reservations;

    public Passager(String id, String nom, String prenom, String adresse, String telephone,
                    String email, String passeportNum, String nationalite) {
        super(id, nom, prenom, adresse, telephone, email);
        this.passeportNum = passeportNum;
        this.nationalite = nationalite;
        this.reservations = new ArrayList<>();
    }

    public String getPasseportNum() { return passeportNum; }
    public void setPasseportNum(String passeportNum) { this.passeportNum = passeportNum; }

    public String getNationalite() { return nationalite; }
    public void setNationalite(String nationalite) { this.nationalite = nationalite; }

    public List<Reservation> getReservations() { return reservations; }

    public Reservation reserverVol(Vol vol, String classe, double prix) {
        Reservation reservation = new Reservation(generateReservationNum(), this, vol, classe, prix);
        reservations.add(reservation);
        return reservation;
    }

    public boolean annulerReservation(String numeroReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getNumeroReservation().equals(numeroReservation)) {
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }

    private String generateReservationNum() {
        // Logique pour générer un numéro de réservation unique
        return "RES" + System.currentTimeMillis() + id.substring(0, 3);
    }

    @Override
    public String obtenirInfos() {
        return "Passager: " + id + ", " + nom + " " + prenom +
                ", Passeport: " + passeportNum + ", Nationalité: " + nationalite;
    }
}
