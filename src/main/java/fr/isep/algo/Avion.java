package fr.isep.algo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String immatriculation;
    private String modele;
    private String fabricant;
    private int capacite;
    private int capaciteFirst;
    private int capaciteBusiness;
    private int capaciteEconomy;
    private double autonomie; // en km
    private int anneeFabrication;
    private List<Vol> volsAssignes;

    public Avion() {
        this.volsAssignes = new ArrayList<>();
    }

    public Avion(String immatriculation, String modele, String fabricant, int capaciteFirst,
                 int capaciteBusiness, int capaciteEconomy, double autonomie, int anneeFabrication) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.fabricant = fabricant;
        this.capaciteFirst = capaciteFirst;
        this.capaciteBusiness = capaciteBusiness;
        this.capaciteEconomy = capaciteEconomy;
        this.capacite = capaciteFirst + capaciteBusiness + capaciteEconomy;
        this.autonomie = autonomie;
        this.anneeFabrication = anneeFabrication;
        this.volsAssignes = new ArrayList<>();
    }

    // Getters and Setters
    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getCapaciteFirst() {
        return capaciteFirst;
    }

    public void setCapaciteFirst(int capaciteFirst) {
        this.capaciteFirst = capaciteFirst;
        updateCapacite();
    }

    public int getCapaciteBusiness() {
        return capaciteBusiness;
    }

    public void setCapaciteBusiness(int capaciteBusiness) {
        this.capaciteBusiness = capaciteBusiness;
        updateCapacite();
    }

    public int getCapaciteEconomy() {
        return capaciteEconomy;
    }

    public void setCapaciteEconomy(int capaciteEconomy) {
        this.capaciteEconomy = capaciteEconomy;
        updateCapacite();
    }

    private void updateCapacite() {
        this.capacite = this.capaciteFirst + this.capaciteBusiness + this.capaciteEconomy;
    }

    public double getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(double autonomie) {
        this.autonomie = autonomie;
    }

    public int getAnneeFabrication() {
        return anneeFabrication;
    }

    public void setAnneeFabrication(int anneeFabrication) {
        this.anneeFabrication = anneeFabrication;
    }

    public List<Vol> getVolsAssignes() {
        return volsAssignes;
    }

    // Method to check availability for a specific date and time
    public boolean verifierDisponibilite(String date, String heureDepart, String heureArrivee) {
        for (Vol vol : volsAssignes) {
            // If the airplane is already assigned to a flight on the same day
            if (vol.getDateDepart().equals(date)) {
                // Check for time overlap
                if ((vol.getHeureDepart().compareTo(heureArrivee) < 0 &&
                        vol.getHeureArrivee().compareTo(heureDepart) > 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to assign a flight to this airplane
    public boolean affecterVol(Vol vol) {
        if (verifierDisponibilite(vol.getDateDepart(), vol.getHeureDepart(), vol.getHeureArrivee())) {
            volsAssignes.add(vol);
            vol.setAvion(this);
            return true;
        }
        return false;
    }

    // Method to get information about the airplane
    public String obtenirInfos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Immatriculation: ").append(immatriculation).append("\n");
        sb.append("Modèle: ").append(modele).append("\n");
        sb.append("Fabricant: ").append(fabricant).append("\n");
        sb.append("Année de fabrication: ").append(anneeFabrication).append("\n");
        sb.append("Capacité totale: ").append(capacite).append(" sièges\n");
        sb.append("  - Première classe: ").append(capaciteFirst).append(" sièges\n");
        sb.append("  - Classe affaires: ").append(capaciteBusiness).append(" sièges\n");
        sb.append("  - Classe économique: ").append(capaciteEconomy).append(" sièges\n");
        sb.append("Autonomie: ").append(autonomie).append(" km\n");

        sb.append("Vols assignés: ");
        if (volsAssignes.isEmpty()) {
            sb.append("Aucun");
        } else {
            sb.append("\n");
            for (Vol vol : volsAssignes) {
                sb.append("  - ").append(vol.getNumeroVol()).append(": ");
                sb.append(vol.getOrigine()).append(" → ").append(vol.getDestination());
                sb.append(" (").append(vol.getDateDepart()).append(" ").append(vol.getHeureDepart()).append(")\n");
            }
        }

        return sb.toString();
    }
}