package fr.isep.algo;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Méthodes pour l'exportation des données en CSV

    public static void exportVolsToCSV(List<Vol> vols, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // En-tête
            writer.write("NumeroVol,Origine,Destination,DateDepart,HeureDepart,HeureArrivee,Duree,Distance,Statut,ImmatriculationAvion");
            writer.newLine();

            // Données
            for (Vol vol : vols) {
                StringBuilder sb = new StringBuilder();
                sb.append(vol.getNumeroVol()).append(",");
                sb.append(vol.getOrigine()).append(",");
                sb.append(vol.getDestination()).append(",");
                sb.append(vol.getDateDepart()).append(",");
                sb.append(vol.getHeureDepart()).append(",");
                sb.append(vol.getHeureArrivee()).append(",");
                sb.append(vol.getDuree()).append(",");
                sb.append(vol.getDistance()).append(",");
                sb.append(vol.getStatut()).append(",");
                sb.append(vol.getAvion() != null ? vol.getAvion().getImmatriculation() : "");

                writer.write(sb.toString());
                writer.newLine();
            }
        }
    }

    public static void exportPassagersToCSV(List<Passager> passagers, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // En-tête
            writer.write("ID,Nom,Prenom,DateNaissance,Adresse,Telephone,Email,NumeroPasseport,Nationalite");
            writer.newLine();

            // Données
            for (Passager passager : passagers) {
                StringBuilder sb = new StringBuilder();
                sb.append(passager.getId()).append(",");
                sb.append(passager.getNom()).append(",");
                sb.append(passager.getPrenom()).append(",");
                sb.append(passager.getDateNaissance()).append(",");
                sb.append(passager.getAdresse().replace(",", ";")).append(","); // Remplacer les virgules pour éviter les conflits CSV
                sb.append(passager.getTelephone()).append(",");
                sb.append(passager.getEmail()).append(",");
                sb.append(passager.getNumeroPasseport()).append(",");
                sb.append(passager.getNationalite());

                writer.write(sb.toString());
                writer.newLine();
            }
        }
    }

    public static void exportReservationsToCSV(List<Reservation> reservations, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // En-tête
            writer.write("NumeroReservation,IDPassager,NumeroVol,DateReservation,Classe,Prix,Statut");
            writer.newLine();

            // Données
            for (Reservation reservation : reservations) {
                StringBuilder sb = new StringBuilder();
                sb.append(reservation.getNumeroReservation()).append(",");
                sb.append(reservation.getPassager().getId()).append(",");
                sb.append(reservation.getVol().getNumeroVol()).append(",");
                sb.append(reservation.getDateReservation()).append(",");
                sb.append(reservation.getClasse()).append(",");
                sb.append(reservation.getPrix()).append(",");
                sb.append(reservation.getStatut());

                writer.write(sb.toString());
                writer.newLine();
            }
        }
    }

    public static void exportAvionsToCSV(List<Avion> avions, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // En-tête
            writer.write("Immatriculation,Modele,Fabricant,CapaciteFirst,CapaciteBusiness,CapaciteEconomy,Autonomie,AnneeFabrication");
            writer.newLine();

            // Données
            for (Avion avion : avions) {
                StringBuilder sb = new StringBuilder();
                sb.append(avion.getImmatriculation()).append(",");
                sb.append(avion.getModele()).append(",");
                sb.append(avion.getFabricant()).append(",");
                sb.append(avion.getCapaciteFirst()).append(",");
                sb.append(avion.getCapaciteBusiness()).append(",");
                sb.append(avion.getCapaciteEconomy()).append(",");
                sb.append(avion.getAutonomie()).append(",");
                sb.append(avion.getAnneeFabrication());

                writer.write(sb.toString());
                writer.newLine();
            }
        }
    }

    // Méthodes pour l'importation des données depuis des fichiers CSV

    public static List<Vol> importVolsFromCSV(String filePath, CompagnieAerienne compagnie) throws IOException {
        List<Vol> vols = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Ignorer la ligne d'en-tête
            String line = reader.readLine();

            // Lire les données
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 9) {
                    Vol vol = new Vol(
                            data[0], // numeroVol
                            data[1], // origine
                            data[2], // destination
                            data[3], // dateDepart
                            data[4], // heureDepart
                            data[5], // heureArrivee
                            data[6], // duree
                            Double.parseDouble(data[7]) // distance
                    );

                    vol.setStatut(data[8]); // statut

                    // Associer l'avion si spécifié
                    if (data.length > 9 && !data[9].isEmpty()) {
                        Avion avion = compagnie.rechercherAvion(data[9]);
                        if (avion != null) {
                            vol.setAvion(avion);
                        }
                    }

                    vols.add(vol);
                }
            }
        }

        return vols;
    }

    public static List<Passager> importPassagersFromCSV(String filePath) throws IOException {
        List<Passager> passagers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Ignorer la ligne d'en-tête
            String line = reader.readLine();

            // Lire les données
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 9) {
                    Passager passager = new Passager(
                            data[0], // id
                            data[1], // nom
                            data[2], // prenom
                            data[3], // dateNaissance
                            data[4], // adresse
                            data[5], // telephone
                            data[6], // email
                            data[7], // numeroPasseport
                            data[8]  // nationalite
                    );

                    passagers.add(passager);
                }
            }
        }

        return passagers;
    }

    public static List<Avion> importAvionsFromCSV(String filePath) throws IOException {
        List<Avion> avions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Ignorer la ligne d'en-tête
            String line = reader.readLine();

            // Lire les données
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 8) {
                    Avion avion = new Avion(
                            data[0], // immatriculation
                            data[1], // modele
                            data[2], // fabricant
                            Integer.parseInt(data[3]), // capaciteFirst
                            Integer.parseInt(data[4]), // capaciteBusiness
                            Integer.parseInt(data[5]), // capaciteEconomy
                            Double.parseDouble(data[6]), // autonomie
                            Integer.parseInt(data[7])  // anneeFabrication
                    );

                    avions.add(avion);
                }
            }
        }

        return avions;
    }

    public static void importReservationsFromCSV(String filePath, CompagnieAerienne compagnie) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Ignorer la ligne d'en-tête
            String line = reader.readLine();

            // Lire les données
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 7) {
                    String numeroReservation = data[0];
                    String idPassager = data[1];
                    String numeroVol = data[2];
                    String dateReservation = data[3];
                    String classe = data[4];
                    double prix = Double.parseDouble(data[5]);
                    String statut = data[6];

                    // Récupérer le passager et le vol
                    Passager passager = compagnie.rechercherPassager(idPassager);
                    Vol vol = compagnie.obtenirVol(numeroVol);

                    if (passager != null && vol != null) {
                        // Créer la réservation
                        Reservation reservation = new Reservation(numeroReservation, passager, vol, classe, prix);
                        reservation.setDateReservation(dateReservation);
                        reservation.setStatut(statut);

                        // Ajouter la réservation au passager
                        if (!passager.getReservations().contains(reservation)) {
                            passager.getReservations().add(reservation);
                        }
                    }
                }
            }
        }
    }
}
