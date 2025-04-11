package fr.isep.algo;

import java.util.ArrayList;
import java.util.List;

public class Pilote extends Employe {
    private static final long serialVersionUID = 1L;

    private String licence;
    private int heuresVol;
    private List<String> qualifications;
    private List<Vol> volsAssignes;

    public Pilote() {
        super();
        this.qualifications = new ArrayList<>();
        this.volsAssignes = new ArrayList<>();
    }

    public Pilote(String id, String nom, String prenom, String dateNaissance, String adresse,
                  String telephone, String email, String matricule, String dateEmbauche,
                  double salaire, String licence, int heuresVol) {
        super(id, nom, prenom, dateNaissance, adresse, telephone, email, matricule, dateEmbauche, "Pilote", salaire);
        this.licence = licence;
        this.heuresVol = heuresVol;
        this.qualifications = new ArrayList<>();
        this.volsAssignes = new ArrayList<>();
    }

    // Getters and Setters
    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public int getHeuresVol() {
        return heuresVol;
    }

    public void setHeuresVol(int heuresVol) {
        this.heuresVol = heuresVol;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void addQualification(String qualification) {
        this.qualifications.add(qualification);
    }

    public List<Vol> getVolsAssignes() {
        return volsAssignes;
    }

    public void assignerVol(Vol vol) {
        this.volsAssignes.add(vol);
    }

    public void retirerVol(Vol vol) {
        this.volsAssignes.remove(vol);
    }

    @Override
    public String obtenirInfos() {
        StringBuilder sb = new StringBuilder(super.obtenirInfos());
        sb.append("\nLicence: ").append(licence);
        sb.append("\nHeures de vol: ").append(heuresVol);

        sb.append("\nQualifications: ");
        if (qualifications.isEmpty()) {
            sb.append("Aucune");
        } else {
            sb.append(String.join(", ", qualifications));
        }

        sb.append("\nVols assignés: ");
        if (volsAssignes.isEmpty()) {
            sb.append("Aucun");
        } else {
            sb.append("\n");
            for (Vol vol : volsAssignes) {
                sb.append("  - ").append(vol.getNumeroVol()).append(": ");
                sb.append(vol.getOrigine()).append(" → ").append(vol.getDestination());
                sb.append(" (").append(vol.getDateDepart()).append(")\n");
            }
        }

        return sb.toString();
    }
}