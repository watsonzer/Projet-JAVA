package fr.isep.algo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pilote extends Employe {
    private String licenceNum;
    private int heuresVol;
    private List<String> qualifications;

    public Pilote(String id, String nom, String prenom, String adresse, String telephone,
                  String email, String poste, double salaire, Date dateEmbauche,
                  String licenceNum, int heuresVol) {
        super(id, nom, prenom, adresse, telephone, email, poste, salaire, dateEmbauche);
        this.licenceNum = licenceNum;
        this.heuresVol = heuresVol;
        this.qualifications = new ArrayList<>();
    }

    public String getLicenceNum() { return licenceNum; }
    public void setLicenceNum(String licenceNum) { this.licenceNum = licenceNum; }

    public int getHeuresVol() { return heuresVol; }
    public void setHeuresVol(int heuresVol) { this.heuresVol = heuresVol; }

    public List<String> getQualifications() { return qualifications; }
    public void ajouterQualification(String qualification) {
        this.qualifications.add(qualification);
    }

    @Override
    public String obtenirRole() {
        return "Pilote";
    }

    @Override
    public String obtenirInfos() {
        return super.obtenirInfos() + ", Licence: " + licenceNum + ", Heures de vol: " + heuresVol;
    }
}
