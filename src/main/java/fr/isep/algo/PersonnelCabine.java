package fr.isep.algo;

import java.util.ArrayList;
import java.util.List;

public class PersonnelCabine extends Employe {
    private static final long serialVersionUID = 1L;

    private String certification;
    private List<String> languesParlees;
    private List<Vol> volsAssignes;

    public PersonnelCabine() {
        super();
        this.languesParlees = new ArrayList<>();
        this.volsAssignes = new ArrayList<>();
    }

    public PersonnelCabine(String id, String nom, String prenom, String dateNaissance, String adresse,
                           String telephone, String email, String matricule, String dateEmbauche,
                           double salaire, String certification) {
        super(id, nom, prenom, dateNaissance, adresse, telephone, email, matricule, dateEmbauche, "Personnel Cabine", salaire);
        this.certification = certification;
        this.languesParlees = new ArrayList<>();
        this.volsAssignes = new ArrayList<>();
    }

    // Getters and Setters
    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public List<String> getLanguesParlees() {
        return languesParlees;
    }

    public void addLangue(String langue) {
        this.languesParlees.add(langue);
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
        sb.append("\nCertification: ").append(certification);

        sb.append("\nLangues parlées: ");
        if (languesParlees.isEmpty()) {
            sb.append("Aucune");
        } else {
            sb.append(String.join(", ", languesParlees));
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
