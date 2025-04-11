package fr.isep.algo;

import java.util.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

public class Main {

    private static CompagnieAerienne compagnie;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialiser la compagnie aérienne
        compagnie = new CompagnieAerienne("Air France", "AF", "Paris, France",
                "+33123456789", "contact@airfrance.fr", "www.airfrance.fr");

        // Charger les données depuis les fichiers (si disponibles)
        chargerDonnees();

        // Afficher le menu principal
        boolean quitter = false;
        while (!quitter) {
            afficherMenuPrincipal();
            int choix = saisirEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    gestionVols();
                    break;
                case 2:
                    gestionPassagers();
                    break;
                case 3:
                    gestionAvions();
                    break;
                case 4:
                    gestionReservations();
                    break;
                case 5:
                    gestionEmployes();
                    break;
                case 6:
                    afficherStatistiques();
                    break;
                case 7:
                    sauvegarderDonnees();
                    break;
                case 8:
                    quitter = true;
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }

    private static void afficherMenuPrincipal() {
        System.out.println("\n--- SYSTÈME DE GESTION DE COMPAGNIE AÉRIENNE ---");
        System.out.println("1. Gestion des vols");
        System.out.println("2. Gestion des passagers");
        System.out.println("3. Gestion des avions");
        System.out.println("4. Gestion des réservations");
        System.out.println("5. Gestion des employés");
        System.out.println("6. Afficher les statistiques");
        System.out.println("7. Sauvegarder les données");
        System.out.println("8. Quitter");
    }

    // Méthodes de gestion des vols
    private static void gestionVols() {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- GESTION DES VOLS ---");
            System.out.println("1. Ajouter un vol");
            System.out.println("2. Afficher tous les vols");
            System.out.println("3. Rechercher un vol");
            System.out.println("4. Affecter un équipage à un vol");
            System.out.println("5. Affecter un avion à un vol");
            System.out.println("6. Annuler un vol");
            System.out.println("7. Retour au menu principal");

            int choix = saisirEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    ajouterVol();
                    break;
                case 2:
                    afficherVols();
                    break;
                case 3:
                    rechercherVol();
                    break;
                case 4:
                    affecterEquipage();
                    break;
                case 5:
                    affecterAvion();
                    break;
                case 6:
                    annulerVol();
                    break;
                case 7:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterVol() {
        System.out.println("\n--- AJOUTER UN VOL ---");
        String numeroVol = saisirChaine("Numéro de vol: ");
        String origine = saisirChaine("Origine: ");
        String destination = saisirChaine("Destination: ");
        String dateDepart = saisirChaine("Date de départ (JJ/MM/AAAA): ");
        String heureDepart = saisirChaine("Heure de départ (HH:MM): ");
        String heureArrivee = saisirChaine("Heure d'arrivée (HH:MM): ");
        String duree = saisirChaine("Durée du vol (HH:MM): ");
        double distance = saisirDouble("Distance (km): ");

        Vol vol = new Vol(numeroVol, origine, destination, dateDepart, heureDepart, heureArrivee, duree, distance);

        compagnie.planifierVol(vol);
        System.out.println("Vol ajouté avec succès.");
    }

    private static void afficherVols() {
        System.out.println("\n--- LISTE DES VOLS ---");
        List<Vol> vols = compagnie.getVols();

        if (vols.isEmpty()) {
            System.out.println("Aucun vol enregistré.");
        } else {
            for (Vol vol : vols) {
                System.out.println(vol.getNumeroVol() + ": " + vol.getOrigine() + " -> "
                        + vol.getDestination() + " (" + vol.getDateDepart() + " "
                        + vol.getHeureDepart() + ") - " + vol.getStatut());
            }
        }
    }

    private static void rechercherVol() {
        System.out.println("\n--- RECHERCHER UN VOL ---");
        String numeroVol = saisirChaine("Numéro de vol: ");

        Vol vol = compagnie.obtenirVol(numeroVol);
        if (vol != null) {
            System.out.println(vol.obtenirVol());
        } else {
            System.out.println("Vol non trouvé.");
        }
    }

    private static void affecterEquipage() {
        System.out.println("\n--- AFFECTER UN ÉQUIPAGE À UN VOL ---");
        String numeroVol = saisirChaine("Numéro de vol: ");

        Vol vol = compagnie.obtenirVol(numeroVol);
        if (vol == null) {
            System.out.println("Vol non trouvé.");
            return;
        }

        // Afficher les pilotes disponibles
        List<Pilote> pilotes = compagnie.getPilotes();
        if (pilotes.isEmpty()) {
            System.out.println("Aucun pilote disponible.");
            return;
        }

        System.out.println("Pilotes disponibles:");
        for (Pilote pilote : pilotes) {
            System.out.println(pilote.getId() + ": " + pilote.getNom() + " " + pilote.getPrenom());
        }

        String idPilote = saisirChaine("ID du pilote: ");

        // Afficher le personnel de cabine disponible
        List<PersonnelCabine> personnels = compagnie.getPersonnelCabine();
        if (personnels.isEmpty()) {
            System.out.println("Aucun personnel de cabine disponible.");
            return;
        }

        System.out.println("Personnel de cabine disponible:");
        for (PersonnelCabine personnel : personnels) {
            System.out.println(personnel.getId() + ": " + personnel.getNom() + " " + personnel.getPrenom());
        }

        List<String> idsPersonnel = new ArrayList<>();
        int nbPersonnel = saisirEntier("Nombre de membres d'équipage de cabine à affecter: ");
        for (int i = 0; i < nbPersonnel; i++) {
            String idPersonnel = saisirChaine("ID du membre d'équipage " + (i+1) + ": ");
            idsPersonnel.add(idPersonnel);
        }

        if (compagnie.affecterEquipageVol(numeroVol, idPilote, idsPersonnel)) {
            System.out.println("Équipage affecté avec succès.");
        } else {
            System.out.println("Erreur lors de l'affectation de l'équipage.");
        }
    }

    private static void affecterAvion() {
        System.out.println("\n--- AFFECTER UN AVION À UN VOL ---");
        String numeroVol = saisirChaine("Numéro de vol: ");

        Vol vol = compagnie.obtenirVol(numeroVol);
        if (vol == null) {
            System.out.println("Vol non trouvé.");
            return;
        }

        // Afficher les avions disponibles
        List<Avion> avions = compagnie.getFlotte();
        if (avions.isEmpty()) {
            System.out.println("Aucun avion disponible.");
            return;
        }

        System.out.println("Avions disponibles:");
        for (Avion avion : avions) {
            boolean disponible = avion.verifierDisponibilite(vol.getDateDepart(), vol.getHeureDepart(), vol.getHeureArrivee());
            System.out.println(avion.getImmatriculation() + ": " + avion.getModele() +
                    " (" + (disponible ? "Disponible" : "Non disponible") + ")");
        }

        String immatriculation = saisirChaine("Immatriculation de l'avion: ");

        if (compagnie.affecterAvionVol(numeroVol, immatriculation)) {
            System.out.println("Avion affecté avec succès.");
        } else {
            System.out.println("Erreur lors de l'affectation de l'avion (indisponible ou non trouvé).");
        }
    }

    private static void annulerVol() {
        System.out.println("\n--- ANNULER UN VOL ---");
        String numeroVol = saisirChaine("Numéro de vol: ");

        if (compagnie.annulerVol(numeroVol)) {
            System.out.println("Vol annulé avec succès.");
        } else {
            System.out.println("Impossible d'annuler ce vol (déjà terminé, en cours, ou non trouvé).");
        }
    }

    // Méthodes de gestion des passagers
    private static void gestionPassagers() {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- GESTION DES PASSAGERS ---");
            System.out.println("1. Ajouter un passager");
            System.out.println("2. Afficher tous les passagers");
            System.out.println("3. Rechercher un passager");
            System.out.println("4. Supprimer un passager");
            System.out.println("5. Retour au menu principal");

            int choix = saisirEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    ajouterPassager();
                    break;
                case 2:
                    afficherPassagers();
                    break;
                case 3:
                    rechercherPassager();
                    break;
                case 4:
                    supprimerPassager();
                    break;
                case 5:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterPassager() {
        System.out.println("\n--- AJOUTER UN PASSAGER ---");
        String id = "P" + System.currentTimeMillis() % 10000; // ID généré automatiquement
        String nom = saisirChaine("Nom: ");
        String prenom = saisirChaine("Prénom: ");
        String dateNaissance = saisirChaine("Date de naissance (JJ/MM/AAAA): ");
        String adresse = saisirChaine("Adresse: ");
        String telephone = saisirChaine("Téléphone: ");
        String email = saisirChaine("Email: ");
        String numeroPasseport = saisirChaine("Numéro de passeport: ");
        String nationalite = saisirChaine("Nationalité: ");

        Passager passager = new Passager(id, nom, prenom, dateNaissance, adresse, telephone, email, numeroPasseport, nationalite);

        compagnie.ajouterPassager(passager);
        System.out.println("Passager ajouté avec succès. ID: " + id);
    }

    private static void afficherPassagers() {
        System.out.println("\n--- LISTE DES PASSAGERS ---");
        List<Passager> passagers = compagnie.getPassagers();

        if (passagers.isEmpty()) {
            System.out.println("Aucun passager enregistré.");
        } else {
            for (Passager passager : passagers) {
                System.out.println(passager.getId() + ": " + passager.getNom() + " " +
                        passager.getPrenom() + " (" + passager.getNumeroPasseport() + ")");
            }
        }
    }

    private static void rechercherPassager() {
        System.out.println("\n--- RECHERCHER UN PASSAGER ---");
        System.out.println("1. Rechercher par ID");
        System.out.println("2. Rechercher par numéro de passeport");

        int choix = saisirEntier("Votre choix: ");

        Passager passager = null;

        switch (choix) {
            case 1:
                String id = saisirChaine("ID du passager: ");
                passager = compagnie.rechercherPassager(id);
                break;
            case 2:
                String passeport = saisirChaine("Numéro de passeport: ");
                passager = compagnie.rechercherPassagerParPasseport(passeport);
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        if (passager != null) {
            System.out.println(passager.obtenirInfos());
        } else {
            System.out.println("Passager non trouvé.");
        }
    }

    private static void supprimerPassager() {
        System.out.println("\n--- SUPPRIMER UN PASSAGER ---");
        String id = saisirChaine("ID du passager: ");

        if (compagnie.supprimerPassager(id)) {
            System.out.println("Passager supprimé avec succès.");
        } else {
            System.out.println("Passager non trouvé.");
        }
    }

    // Méthodes de gestion des avions
    private static void gestionAvions() {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- GESTION DES AVIONS ---");
            System.out.println("1. Ajouter un avion");
            System.out.println("2. Afficher tous les avions");
            System.out.println("3. Rechercher un avion");
            System.out.println("4. Supprimer un avion");
            System.out.println("5. Retour au menu principal");

            int choix = saisirEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    ajouterAvion();
                    break;
                case 2:
                    afficherAvions();
                    break;
                case 3:
                    rechercherAvion();
                    break;
                case 4:
                    supprimerAvion();
                    break;
                case 5:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterAvion() {
        System.out.println("\n--- AJOUTER UN AVION ---");
        String immatriculation = saisirChaine("Immatriculation: ");
        String modele = saisirChaine("Modèle: ");
        String fabricant = saisirChaine("Fabricant: ");
        int capaciteFirst = saisirEntier("Capacité en première classe: ");
        int capaciteBusiness = saisirEntier("Capacité en classe affaires: ");
        int capaciteEconomy = saisirEntier("Capacité en classe économique: ");
        double autonomie = saisirDouble("Autonomie (km): ");
        int anneeFabrication = saisirEntier("Année de fabrication: ");

        Avion avion = new Avion(immatriculation, modele, fabricant, capaciteFirst,
                capaciteBusiness, capaciteEconomy, autonomie, anneeFabrication);

        compagnie.ajouterAvion(avion);
        System.out.println("Avion ajouté avec succès.");
    }

    private static void afficherAvions() {
        System.out.println("\n--- LISTE DES AVIONS ---");
        List<Avion> avions = compagnie.getFlotte();

        if (avions.isEmpty()) {
            System.out.println("Aucun avion enregistré.");
        } else {
            for (Avion avion : avions) {
                System.out.println(avion.getImmatriculation() + ": " + avion.getFabricant() +
                        " " + avion.getModele() + " (" + avion.getCapacite() + " places)");
            }
        }
    }

    private static void rechercherAvion() {
        System.out.println("\n--- RECHERCHER UN AVION ---");
        String immatriculation = saisirChaine("Immatriculation: ");

        Avion avion = compagnie.rechercherAvion(immatriculation);
        if (avion != null) {
            System.out.println(avion.obtenirInfos());
        } else {
            System.out.println("Avion non trouvé.");
        }
    }

    private static void supprimerAvion() {
        System.out.println("\n--- SUPPRIMER UN AVION ---");
        String immatriculation = saisirChaine("Immatriculation: ");

        if (compagnie.supprimerAvion(immatriculation)) {
            System.out.println("Avion supprimé avec succès.");
        } else {
            System.out.println("Avion non trouvé.");
        }
    }

    // Méthodes de gestion des réservations
    private static void gestionReservations() {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- GESTION DES RÉSERVATIONS ---");
            System.out.println("1. Créer une réservation");
            System.out.println("2. Rechercher une réservation");
            System.out.println("3. Annuler une réservation");
            System.out.println("4. Retour au menu principal");

            int choix = saisirEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    creerReservation();
                    break;
                case 2:
                    rechercherReservation();
                    break;
                case 3:
                    annulerReservation();
                    break;
                case 4:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void creerReservation() {
        System.out.println("\n--- CRÉER UNE RÉSERVATION ---");

        // Rechercher le passager
        System.out.println("Recherche du passager:");
        String idPassager = saisirChaine("ID du passager: ");

        Passager passager = compagnie.rechercherPassager(idPassager);
        if (passager == null) {
            System.out.println("Passager non trouvé. Veuillez d'abord créer le passager.");
            return;
        }

        // Rechercher le vol
        System.out.println("\nRecherche du vol:");
        String origine = saisirChaine("Origine: ");
        String destination = saisirChaine("Destination: ");
        String date = saisirChaine("Date (JJ/MM/AAAA): ");

        List<Vol> vols = compagnie.rechercherVols(origine, destination, date);

        if (vols.isEmpty()) {
            System.out.println("Aucun vol trouvé pour ces critères.");
            return;
        }

        System.out.println("\nVols disponibles:");
        for (int i = 0; i < vols.size(); i++) {
            Vol vol = vols.get(i);
            System.out.println((i+1) + ". " + vol.getNumeroVol() + ": " +
                    vol.getOrigine() + " -> " + vol.getDestination() + " (" +
                    vol.getDateDepart() + " " + vol.getHeureDepart() + ")");
        }

        int choixVol = saisirEntier("Choisissez un vol (numéro): ");
        if (choixVol < 1 || choixVol > vols.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Vol volChoisi = vols.get(choixVol - 1);

        // Choisir la classe
        System.out.println("\nClasses disponibles:");
        System.out.println("1. Première classe");
        System.out.println("2. Classe affaires");
        System.out.println("3. Classe économique");

        int choixClasse = saisirEntier("Choisissez une classe: ");
        String classe;
        double prix;

        switch (choixClasse) {
            case 1:
                classe = "First";
                prix = 1000.0; // Prix exemple
                break;
            case 2:
                classe = "Business";
                prix = 600.0; // Prix exemple
                break;
            case 3:
                classe = "Economy";
                prix = 300.0; // Prix exemple
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        // Créer la réservation
        Reservation reservation = passager.reserverVol(volChoisi, classe, prix);

        System.out.println("\nRéservation créée avec succès:");
        System.out.println(reservation.obtenirReservations());
    }

    private static void rechercherReservation() {
        System.out.println("\n--- RECHERCHER UNE RÉSERVATION ---");

        // D'abord trouver le passager
        String idPassager = saisirChaine("ID du passager: ");

        Passager passager = compagnie.rechercherPassager(idPassager);
        if (passager == null) {
            System.out.println("Passager non trouvé.");
            return;
        }

        // Afficher les réservations du passager
        List<Reservation> reservations = passager.getReservations();

        if (reservations.isEmpty()) {
            System.out.println("Ce passager n'a aucune réservation.");
            return;
        }

        System.out.println("\nRéservations du passager:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getNumeroReservation() + ": " +
                    reservation.getVol().getNumeroVol() + " (" +
                    reservation.getVol().getOrigine() + " -> " +
                    reservation.getVol().getDestination() + ") - " +
                    reservation.getStatut());
        }

        String numeroReservation = saisirChaine("\nNuméro de réservation à consulter: ");

        Reservation reservation = passager.obtenirReservation(numeroReservation);
        if (reservation != null) {
            System.out.println(reservation.obtenirReservations());
        } else {
            System.out.println("Réservation non trouvée.");
        }
    }

    private static void annulerReservation() {
        System.out.println("\n--- ANNULER UNE RÉSERVATION ---");

        // D'abord trouver le passager
        String idPassager = saisirChaine("ID du passager: ");

        Passager passager = compagnie.rechercherPassager(idPassager);
        if (passager == null) {
            System.out.println("Passager non trouvé.");
            return;
        }

        // Afficher les réservations du passager
        List<Reservation> reservations = passager.getReservations();

        if (reservations.isEmpty()) {
            System.out.println("Ce passager n'a aucune réservation.");
            return;
        }

        System.out.println("\nRéservations du passager:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getNumeroReservation() + ": " +
                    reservation.getVol().getNumeroVol() + " (" +
                    reservation.getVol().getOrigine() + " -> " +
                    reservation.getVol().getDestination() + ") - " +
                    reservation.getStatut());
        }

        String numeroReservation = saisirChaine("\nNuméro de réservation à annuler: ");

        if (passager.annulerReservation(numeroReservation)) {
            System.out.println("Réservation annulée avec succès.");
        } else {
            System.out.println("Réservation non trouvée ou déjà annulée.");
        }
    }

    // Méthodes de gestion des employés
    private static void gestionEmployes() {
        boolean retour = false;

        while (!retour) {
            System.out.println("\n--- GESTION DES EMPLOYÉS ---");
            System.out.println("1. Ajouter un pilote");
            System.out.println("2. Ajouter un personnel de cabine");
            System.out.println("3. Afficher tous les employés");
            System.out.println("4. Rechercher un employé");
            System.out.println("5. Supprimer un employé");
            System.out.println("6. Retour au menu principal");

            int choix = saisirEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    ajouterPilote();
                    break;
                case 2:
                    ajouterPersonnelCabine();
                    break;
                case 3:
                    afficherEmployes();
                    break;
                case 4:
                    rechercherEmploye();
                    break;
                case 5:
                    supprimerEmploye();
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void ajouterPilote() {
        System.out.println("\n--- AJOUTER UN PILOTE ---");
        String id = "E" + System.currentTimeMillis() % 10000; // ID généré automatiquement
        String nom = saisirChaine("Nom: ");
        String prenom = saisirChaine("Prénom: ");
        String dateNaissance = saisirChaine("Date de naissance (JJ/MM/AAAA): ");
        String adresse = saisirChaine("Adresse: ");
        String telephone = saisirChaine("Téléphone: ");
        String email = saisirChaine("Email: ");
        String matricule = saisirChaine("Matricule: ");
        String dateEmbauche = saisirChaine("Date d'embauche (JJ/MM/AAAA): ");
        double salaire = saisirDouble("Salaire: ");
        String licence = saisirChaine("Numéro de licence: ");
        int heuresVol = saisirEntier("Heures de vol: ");

        Pilote pilote = new Pilote(id, nom, prenom, dateNaissance, adresse, telephone,
                email, matricule, dateEmbauche, salaire, licence, heuresVol);

        // Ajouter des qualifications
        boolean ajouterQualification = true;
        while (ajouterQualification) {
            String qualification = saisirChaine("Qualification (ou 'fin' pour terminer): ");
            if (qualification.equalsIgnoreCase("fin")) {
                ajouterQualification = false;
            } else {
                pilote.addQualification(qualification);
            }
        }

        compagnie.ajouterEmploye(pilote);
        System.out.println("Pilote ajouté avec succès. ID: " + id);
    }

    private static void ajouterPersonnelCabine() {
        System.out.println("\n--- AJOUTER UN PERSONNEL DE CABINE ---");
        String id = "E" + System.currentTimeMillis() % 10000; // ID généré automatiquement
        String nom = saisirChaine("Nom: ");
        String prenom = saisirChaine("Prénom: ");
        String dateNaissance = saisirChaine("Date de naissance (JJ/MM/AAAA): ");
        String adresse = saisirChaine("Adresse: ");
        String telephone = saisirChaine("Téléphone: ");
        String email = saisirChaine("Email: ");
        String matricule = saisirChaine("Matricule: ");
        String dateEmbauche = saisirChaine("Date d'embauche (JJ/MM/AAAA): ");
        double salaire = saisirDouble("Salaire: ");
        String certification = saisirChaine("Certification: ");

        PersonnelCabine personnel = new PersonnelCabine(id, nom, prenom, dateNaissance, adresse,
                telephone, email, matricule, dateEmbauche,
                salaire, certification);

        // Ajouter des langues
        boolean ajouterLangue = true;
        while (ajouterLangue) {
            String langue = saisirChaine("Langue parlée (ou 'fin' pour terminer): ");
            if (langue.equalsIgnoreCase("fin")) {
                ajouterLangue = false;
            } else {
                personnel.addLangue(langue);
            }
        }

        compagnie.ajouterEmploye(personnel);
        System.out.println("Personnel de cabine ajouté avec succès. ID: " + id);
    }

    private static void afficherEmployes() {
        System.out.println("\n--- LISTE DES EMPLOYÉS ---");
        List<Employe> employes = compagnie.getEmployes();

        if (employes.isEmpty()) {
            System.out.println("Aucun employé enregistré.");
        } else {
            System.out.println("Pilotes:");
            for (Employe employe : employes) {
                if (employe instanceof Pilote) {
                    System.out.println(employe.getId() + ": " + employe.getNom() + " " +
                            employe.getPrenom() + " (" + employe.obtenirRole() + ")");
                }
            }

            System.out.println("\nPersonnel de cabine:");
            for (Employe employe : employes) {
                if (employe instanceof PersonnelCabine) {
                    System.out.println(employe.getId() + ": " + employe.getNom() + " " +
                            employe.getPrenom() + " (" + employe.obtenirRole() + ")");
                }
            }
        }
    }

    private static void rechercherEmploye() {
        System.out.println("\n--- RECHERCHER UN EMPLOYÉ ---");
        String id = saisirChaine("ID de l'employé: ");

        Employe employe = compagnie.rechercherEmploye(id);
        if (employe != null) {
            System.out.println(employe.obtenirInfos());
            System.out.println("Rôle: " + employe.obtenirRole());
        } else {
            System.out.println("Employé non trouvé.");
        }
    }

    private static void supprimerEmploye() {
        System.out.println("\n--- SUPPRIMER UN EMPLOYÉ ---");
        String id = saisirChaine("ID de l'employé: ");

        if (compagnie.supprimerEmploye(id)) {
            System.out.println("Employé supprimé avec succès.");
        } else {
            System.out.println("Employé non trouvé.");
        }
    }

    // Méthodes pour les statistiques
    private static void afficherStatistiques() {
        System.out.println("\n--- STATISTIQUES ---");

        System.out.println("Nombre total de vols: " + compagnie.getNombreVols());
        System.out.println("  - Vols planifiés: " + compagnie.getNombreVolsParStatut("Planifié"));
        System.out.println("  - Vols en cours: " + compagnie.getNombreVolsParStatut("En vol"));
        System.out.println("  - Vols terminés: " + compagnie.getNombreVolsParStatut("Terminé"));
        System.out.println("  - Vols annulés: " + compagnie.getNombreVolsParStatut("Annulé"));

        System.out.println("\nNombre de passagers: " + compagnie.getNombrePassagers());
        System.out.println("Nombre de réservations: " + compagnie.getNombreReservations());
        System.out.println("Revenus générés: " + compagnie.getRevenusGeneres() + " €");

        System.out.println("\nDestinations les plus populaires:");
        Map<String, Integer> destinations = compagnie.getDestinationsPopulaires();

        // Trier par popularité
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(destinations.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (int i = 0; i < Math.min(5, entries.size()); i++) {
            Map.Entry<String, Integer> entry = entries.get(i);
            System.out.println((i+1) + ". " + entry.getKey() + ": " + entry.getValue() + " passagers");
        }
    }

    // Méthodes pour la sauvegarde et le chargement des données
    private static void sauvegarderDonnees() {
        System.out.println("\n--- SAUVEGARDE DES DONNÉES ---");

        try {
            // Créer le dossier data s'il n'existe pas
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            // Sauvegarder les vols
            FileManager.exportVolsToCSV(compagnie.getVols(), "data/vols.csv");
            System.out.println("Vols sauvegardés avec succès.");

            // Sauvegarder les passagers
            FileManager.exportPassagersToCSV(compagnie.getPassagers(), "data/passagers.csv");
            System.out.println("Passagers sauvegardés avec succès.");

            // Sauvegarder les avions
            FileManager.exportAvionsToCSV(compagnie.getFlotte(), "data/avions.csv");
            System.out.println("Avions sauvegardés avec succès.");

            // Collecter toutes les réservations
            List<Reservation> reservations = new ArrayList<>();
            for (Passager passager : compagnie.getPassagers()) {
                reservations.addAll(passager.getReservations());
            }

            // Sauvegarder les réservations
            FileManager.exportReservationsToCSV(reservations, "data/reservations.csv");
            System.out.println("Réservations sauvegardées avec succès.");

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données: " + e.getMessage());
        }
    }

    private static void chargerDonnees() {
        System.out.println("Chargement des données...");

        try {
            // Vérifier si les fichiers existent
            File volsFile = new File("data/vols.csv");
            File passagersFile = new File("data/passagers.csv");
            File avionsFile = new File("data/avions.csv");
            File reservationsFile = new File("data/reservations.csv");

            // Charger les avions
            if (avionsFile.exists()) {
                List<Avion> avions = FileManager.importAvionsFromCSV("data/avions.csv");
                for (Avion avion : avions) {
                    compagnie.ajouterAvion(avion);
                }
                System.out.println(avions.size() + " avions chargés.");
            }

            // Charger les passagers
            if (passagersFile.exists()) {
                List<Passager> passagers = FileManager.importPassagersFromCSV("data/passagers.csv");
                for (Passager passager : passagers) {
                    compagnie.ajouterPassager(passager);
                }
                System.out.println(passagers.size() + " passagers chargés.");
            }

            // Charger les vols
            if (volsFile.exists()) {
                List<Vol> vols = FileManager.importVolsFromCSV("data/vols.csv", compagnie);
                for (Vol vol : vols) {
                    compagnie.planifierVol(vol);
                }
                System.out.println(vols.size() + " vols chargés.");
            }

            // Charger les réservations (doit être fait après les vols et les passagers)
            if (reservationsFile.exists() && volsFile.exists() && passagersFile.exists()) {
                FileManager.importReservationsFromCSV("data/reservations.csv", compagnie);
                System.out.println("Réservations chargées.");
            }

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des données: " + e.getMessage());
        }
    }

    // Méthodes utilitaires pour la saisie
    private static String saisirChaine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                int valeur = Integer.parseInt(scanner.nextLine());
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
            }
        }
    }

    private static double saisirDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double valeur = Double.parseDouble(scanner.nextLine());
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre décimal valide.");
            }
        }
    }
}