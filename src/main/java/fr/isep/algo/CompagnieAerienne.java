package fr.isep.algo;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompagnieAerienne implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nom;
    private String code;
    private String adresse;
    private String telephone;
    private String email;
    private String siteWeb;

    private List<Avion> flotte;
    private List<Employe> employes;
    private List<Passager> passagers;
    private List<Vol> vols;
    private Map<String, Vol> volsParNumero;

    public CompagnieAerienne() {
        this.flotte = new ArrayList<>();
        this.employes = new ArrayList<>();
        this.passagers = new ArrayList<>();
        this.vols = new ArrayList<>();
        this.volsParNumero = new HashMap<>();
    }

    public CompagnieAerienne(String nom, String code, String adresse, String telephone, String email, String siteWeb) {
        this.nom = nom;
        this.code = code;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.siteWeb = siteWeb;

        this.flotte = new ArrayList<>();
        this.employes = new ArrayList<>();
        this.passagers = new ArrayList<>();
        this.vols = new ArrayList<>();
        this.volsParNumero = new HashMap<>();
    }

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    // Aircraft management
    public List<Avion> getFlotte() {
        return flotte;
    }

    public void ajouterAvion(Avion avion) {
        flotte.add(avion);
    }

    public boolean supprimerAvion(String immatriculation) {
        for (Avion avion : flotte) {
            if (avion.getImmatriculation().equals(immatriculation)) {
                flotte.remove(avion);
                return true;
            }
        }
        return false;
    }

    public Avion rechercherAvion(String immatriculation) {
        for (Avion avion : flotte) {
            if (avion.getImmatriculation().equals(immatriculation)) {
                return avion;
            }
        }
        return null;
    }

    // Employee management
    public List<Employe> getEmployes() {
        return employes;
    }

    public void ajouterEmploye(Employe employe) {
        employes.add(employe);
    }

    public boolean supprimerEmploye(String id) {
        for (Employe employe : employes) {
            if (employe.getId().equals(id)) {
                employes.remove(employe);
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

    public String obtenirRoleEmploye(String id) {
        Employe employe = rechercherEmploye(id);
        if (employe != null) {
            return employe.obtenirRole();
        }
        return "Employé non trouvé";
    }

    public List<Pilote> getPilotes() {
        List<Pilote> pilotes = new ArrayList<>();
        for (Employe employe : employes) {
            if (employe instanceof Pilote) {
                pilotes.add((Pilote) employe);
            }
        }
        return pilotes;
    }

    public List<PersonnelCabine> getPersonnelCabine() {
        List<PersonnelCabine> personnels = new ArrayList<>();
        for (Employe employe : employes) {
            if (employe instanceof PersonnelCabine) {
                personnels.add((PersonnelCabine) employe);
            }
        }
        return personnels;
    }

    // Passenger management
    public List<Passager> getPassagers() {
        return passagers;
    }

    public void ajouterPassager(Passager passager) {
        passagers.add(passager);
    }

    public boolean supprimerPassager(String id) {
        for (Passager passager : passagers) {
            if (passager.getId().equals(id)) {
                passagers.remove(passager);
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

    public Passager rechercherPassagerParPasseport(String numeroPasseport) {
        for (Passager passager : passagers) {
            if (passager.getNumeroPasseport().equals(numeroPasseport)) {
                return passager;
            }
        }
        return null;
    }

    // Flight management
    public List<Vol> getVols() {
        return vols;
    }

    public void planifierVol(Vol vol) {
        vols.add(vol);
        volsParNumero.put(vol.getNumeroVol(), vol);
    }

    public boolean annulerVol(String numeroVol) {
        Vol vol = obtenirVol(numeroVol);
        if (vol != null) {
            if (vol.annulerVol()) {
                // Note: we keep the flight in the list, but with status "Cancelled"
                return true;
            }
        }
        return false;
    }

    public Vol obtenirVol(String numeroVol) {
        return volsParNumero.get(numeroVol);
    }

    public List<Vol> rechercherVols(String origine, String destination, String date) {
        List<Vol> volsTrouves = new ArrayList<>();
        for (Vol vol : vols) {
            if (vol.getOrigine().equals(origine) &&
                    vol.getDestination().equals(destination) &&
                    vol.getDateDepart().equals(date) &&
                    !vol.getStatut().equals("Annulé")) {
                volsTrouves.add(vol);
            }
        }
        return volsTrouves;
    }

    // Staff assignment
    public boolean affecterEquipageVol(String numeroVol, String idPilote, List<String> idsPersonnelCabine) {
        Vol vol = obtenirVol(numeroVol);
        if (vol == null) {
            return false;
        }

        // Assign the pilot
        Employe employe = rechercherEmploye(idPilote);
        if (employe instanceof Pilote) {
            vol.setPilote((Pilote) employe);
        } else {
            return false;
        }

        // Assign cabin crew
        for (String idPersonnel : idsPersonnelCabine) {
            employe = rechercherEmploye(idPersonnel);
            if (employe instanceof PersonnelCabine) {
                vol.ajouterPersonnelCabine((PersonnelCabine) employe);
            }
        }

        return true;
    }

    // Aircraft assignment
    public boolean affecterAvionVol(String numeroVol, String immatriculation) {
        Vol vol = obtenirVol(numeroVol);
        Avion avion = rechercherAvion(immatriculation);

        if (vol == null || avion == null) {
            return false;
        }

        return avion.affecterVol(vol);
    }

    // Statistics
    public int getNombreVols() {
        return vols.size();
    }

    public int getNombreVolsParStatut(String statut) {
        int count = 0;
        for (Vol vol : vols) {
            if (vol.getStatut().equals(statut)) {
                count++;
            }
        }
        return count;
    }

    public int getNombrePassagers() {
        return passagers.size();
    }

    public int getNombreReservations() {
        int count = 0;
        for (Vol vol : vols) {
            count += vol.getReservations().size();
        }
        return count;
    }

    public Map<String, Integer> getDestinationsPopulaires() {
        Map<String, Integer> destinations = new HashMap<>();

        for (Vol vol : vols) {
            String destination = vol.getDestination();
            destinations.put(destination, destinations.getOrDefault(destination, 0) + vol.getReservations().size());
        }

        return destinations;
    }

    public double getRevenusGeneres() {
        double revenus = 0;
        for (Vol vol : vols) {
            for (Reservation reservation : vol.getReservations()) {
                if (!reservation.getStatut().equals("Annulée")) {
                    revenus += reservation.getPrix();
                }
            }
        }
        return revenus;
    }
}