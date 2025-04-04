package fr.isep.algo;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        CompagnieAerienne airFrance = new CompagnieAerienne("Air France", "AF");

        Avion avion1 = new Avion("F-GKXL", "Airbus A320", 180);
        Avion avion2 = new Avion("F-HPJE", "Boeing 787", 300);

        airFrance.ajouterAvion(avion1);
        airFrance.ajouterAvion(avion2);

        Pilote pilote1 = new Pilote("P001", "Dubois", "Jean", "1 Rue Paris", "0123456789",
                "jean.dubois@airfrance.fr", "Commandant de bord", 80000.0,
                new Date(), "PLCM001", 5000);
        pilote1.ajouterQualification("A320");

        PersonnelCabine hotesse1 = new PersonnelCabine("PC001", "Martin", "Sophie", "2 Rue Lyon",
                "0987654321", "sophie.martin@airfrance.fr",
                "Chef de cabine", 45000.0, new Date(), "CERT001");
        hotesse1.ajouterLangue("Français");
        hotesse1.ajouterLangue("Anglais");

        airFrance.ajouterEmploye(pilote1);
        airFrance.ajouterEmploye(hotesse1);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 10, 8, 0); // 10 décembre 2024, 8h00
        Date dateDepart = calendar.getTime();

        calendar.set(2024, Calendar.DECEMBER, 10, 10, 30); // 10 décembre 2024, 10h30
        Date dateArrivee = calendar.getTime();

        Vol vol1 = new Vol("AF1234", "Paris", "Nice", dateDepart, dateArrivee);
        vol1.setAvion(avion1);

        List<Employe> equipage = new ArrayList<>();
        equipage.add(pilote1);
        equipage.add(hotesse1);
        vol1.affecterEquipage(equipage);

        airFrance.planifierVol(vol1);

        Passager passager1 = new Passager("C001", "Petit", "Marie", "3 Rue Marseille",
                "0654321987", "marie.petit@gmail.com", "PAB123456", "Française");

        airFrance.ajouterPassager(passager1);

        Reservation reservation1 = passager1.reserverVol(vol1, "Économique", 150.0);

        System.out.println("=== SYSTÈME DE GESTION DE COMPAGNIE AÉRIENNE ===");
        System.out.println("\nDétails du vol:");
        System.out.println(vol1.obtenirVol());

        System.out.println("\nDétails de la réservation:");
        System.out.println(reservation1.obtenirReservations());

        System.out.println("\nInformations du passager:");
        System.out.println(passager1.obtenirInfos());

        System.out.println("\nInformations du pilote:");
        System.out.println(pilote1.obtenirInfos());

        System.out.println("\nInformations du personnel de cabine:");
        System.out.println(hotesse1.obtenirInfos());

        System.out.println("\n=== ANNULATION DE RÉSERVATION ===");
        boolean annulationReussie = passager1.annulerReservation(reservation1.getNumeroReservation());
        System.out.println("Annulation de la réservation " + reservation1.getNumeroReservation() +
                ": " + (annulationReussie ? "Réussie" : "Échouée"));

        calendar.set(2024, Calendar.DECEMBER, 15, 14, 0); // 15 décembre 2024, 14h00
        Date dateDepart2 = calendar.getTime();

        calendar.set(2024, Calendar.DECEMBER, 15, 16, 0); // 15 décembre 2024, 16h00
        Date dateArrivee2 = calendar.getTime();

        Vol vol2 = new Vol("AF5678", "Paris", "Lyon", dateDepart2, dateArrivee2);
        vol2.setAvion(avion2);
        vol2.affecterEquipage(equipage);

        airFrance.planifierVol(vol2);

        Reservation reservation2 = passager1.reserverVol(vol2, "Affaires", 250.0);

        System.out.println("\n=== ANNULATION DE VOL ===");
        boolean volAnnule = airFrance.annulerVol(vol2.getNumeroVol());
        System.out.println("Annulation du vol " + vol2.getNumeroVol() +
                ": " + (volAnnule ? "Réussie" : "Échouée"));

        System.out.println("Statut du vol après annulation: " + vol2.getStatut());
        System.out.println("Statut de la réservation après annulation: " + reservation2.getStatut());

        System.out.println("\n=== STATISTIQUES ===");
        System.out.println("Revenu total: " + airFrance.calculerRevenusTotal() + " €");

        Map<String, Integer> destinationsPopulaires = airFrance.statistiquesDestinationsPopulaires();
        System.out.println("\nDestinations populaires:");
        for (Map.Entry<String, Integer> entry : destinationsPopulaires.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " passagers");
        }
    }
}