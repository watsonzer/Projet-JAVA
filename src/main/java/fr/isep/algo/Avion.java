package fr.isep.algo;

// Fichier: Avion.java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private boolean disponible;
    private List<Vol> vols;

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
        this.disponible = true;
        this.vols = new ArrayList<>();
    }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public List<Vol> getVols() { return vols; }

    public boolean verifierDisponibilite(Date dateDepart, Date dateArrivee) {
        for (Vol vol : vols) {
            if ((vol.getDateDepart().before(dateArrivee) && vol.getDateArrivee().after(dateDepart)) ||
                    vol.getDateDepart().equals(dateDepart) || vol.getDateArrivee().equals(dateArrivee)) {
                return false;
            }
        }
        return true;
    }

    public boolean affecterVol(Vol vol) {
        if (verifierDisponibilite(vol.getDateDepart(), vol.getDateArrivee())) {
            vols.add(vol);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Avion: " + immatriculation + ", Modèle: " + modele + ", Capacité: " + capacite;
    }
}
