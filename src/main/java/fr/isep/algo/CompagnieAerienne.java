package fr.isep.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompagnieAerienne {
    private String nom;
    private String code;
    private List<Avion> avions;
    private List<Employe> employes;
    private List<Vol> vols;
    private List<Passager> passagers;

    public CompagnieAerienne(String nom, String code) {
        this.nom = nom;
        this.code = code;
        this.avions = new ArrayList<>();
        this.employes = new ArrayList<>();
        this.vols = new ArrayList<>();
        this.passagers = new ArrayList<>();
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public void ajouterAvion(Avion avion) {
        avions.add(avion);
    }

    public boolean supprimerAvion(String immatriculation) {
        for (int i = 0; i < avions.size(); i++) {
            if (avions.get(i).getImmatriculation().equals(immatriculation)) {
                avions.remove(i);
                return true;
            }
        }
        return false;
    }

    public Avion rechercherAvion(String immatriculation) {
        for (Avion avion : avions) {
            if (avion.getImmatriculation().equals(immatriculation)) {
                return avion;
            }
        }
        return null;
    }

    public void ajouterEmploye(Employe employe) {
        employes.add(employe);
    }

    public boolean supprimerEmploye(String id) {
        for (int i = 0; i < employes.size(); i++) {
            if (employes.get(i).getId().equals(id)) {
                employes.remove(i);
                return true;
            }
        }
        return false;
    }

    public Employe rechercherEmploye(String id) {
        for (Employe employe : employes) {
            if (employe.getId().equals(id)) {
                return employe;
            }
        }
        return null;
    }

    public void planifierVol(Vol vol) {
        vols.add(vol);
    }

    public boolean annulerVol(String numeroVol) {
        for (Vol vol : vols) {
            if (vol.getNumeroVol().equals(numeroVol)) {
                return vol.annulerVol();
            }
        }
        return false;
    }

    public Vol rechercherVol(String numeroVol) {
        for (Vol vol : vols) {
            if (vol.getNumeroVol().equals(numeroVol)) {
                return vol;
            }
        }
        return null;
    }

    public void ajouterPassager(Passager passager) {
        passagers.add(passager);
    }

    public boolean supprimerPassager(String id) {
        for (int i = 0; i < passagers.size(); i++) {
            if (passagers.get(i).getId().equals(id)) {
                passagers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Passager rechercherPassager(String id) {
        for (Passager passager : passagers) {
            if (passager.getId().equals(id)) {
                return passager;
            }
        }
        return null;
    }

    public Map<String, Integer> statistiquesDestinationsPopulaires() {
        Map<String, Integer> destinations = new HashMap<>();

        for (Vol vol : vols) {
            String destination = vol.getDestination();
            destinations.put(destination, destinations.getOrDefault(destination, 0) + vol.getReservations().size());
        }

        return destinations;
    }

    public double calculerRevenusTotal() {
        double total = 0;
        for (Vol vol : vols) {
            for (Reservation reservation : vol.getReservations()) {
                if (!reservation.getStatut().equals("Annulé")) {
                    total += reservation.getPrix();
                }
            }
        }
        return total;
    }
}
