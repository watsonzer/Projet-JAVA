package fr.isep.algo;

import com.airline.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompagnieAerienneTest {

    private CompagnieAerienne compagnie;
    private Vol vol1;
    private Vol vol2;
    private Avion avion;
    private Pilote pilote;
    private PersonnelCabine personnel1;
    private PersonnelCabine personnel2;

    @BeforeEach
    void setUp() {
        // Créer une compagnie pour chaque test
        compagnie = new CompagnieAerienne("Air Test", "AT", "Adresse Test",
                "0123456789", "contact@airtest.com", "www.airtest.com");

        // Créer des vols pour les tests
        vol1 = new Vol("AT100", "Paris", "Lyon", "12/05/2024",
                "10:00", "11:00", "01:00", 400.0);
        vol2 = new Vol("AT200", "Lyon", "Nice", "12/05/2024",
                "14:00", "15:30", "01:30", 500.0);

        // Créer un avion pour les tests
        avion = new Avion("F-TEST", "Airbus A320", "Airbus",
                16, 40, 120, 5000, 2020);

        // Créer des employés pour les tests
        pilote = new Pilote("E1", "Martin", "Paul", "01/01/1980",
                "Adresse Pilote", "0123456789", "paul@example.com",
                "P001", "01/01/2010", 5000.0, "L12345", 5000);

        personnel1 = new PersonnelCabine("E2", "Durand", "Marie", "02/02/1985",
                "Adresse PC1", "0123456789", "marie@example.com",
                "PC001", "01/01/2015", 3000.0, "C12345");

        personnel2 = new PersonnelCabine("E3", "Petit", "Sophie", "03/03/1990",
                "Adresse PC2", "0123456789", "sophie@example.com",
                "PC002", "01/01/2018", 3000.0, "C67890");
    }

    @Test
    void testPlanifierVol() {
        // Test de planification d'un vol
        compagnie.planifierVol(vol1);

        // Vérifier que le vol a été ajouté
        assertEquals(1, compagnie.getVols().size());
        assertEquals(vol1, compagnie.obtenirVol("AT100"));
    }

    @Test
    void testAffecterEquipageVol() {
        // Ajouter les vols et les employés
        compagnie.planifierVol(vol1);
        compagnie.ajouterEmploye(pilote);
        compagnie.ajouterEmploye(personnel1);
        compagnie.ajouterEmploye(personnel2);

        // Affecter l'équipage
        boolean resultat = compagnie.affecterEquipageVol("AT100", "E1", Arrays.asList("E2", "E3"));

        // Vérifier que l'affectation a réussi
        assertTrue(resultat);

        // Vérifier que l'équipage est bien affecté
        assertEquals(pilote, vol1.getPilote());
        assertEquals(2, vol1.getEquipageCabine().size());
        assertTrue(vol1.getEquipageCabine().contains(personnel1));
        assertTrue(vol1.getEquipageCabine().contains(personnel2));
    }

    @Test
    void testAffecterAvionVol() {
        // Ajouter le vol et l'avion
        compagnie.planifierVol(vol1);
        compagnie.ajouterAvion(avion);

        // Affecter l'avion
        boolean resultat = compagnie.affecterAvionVol("AT100", "F-TEST");

        // Vérifier que l'affectation a réussi
        assertTrue(resultat);

        // Vérifier que l'avion est bien affecté
        assertEquals(avion, vol1.getAvion());
    }

    @Test
    void testRechercherVols() {
        // Ajouter les vols
        compagnie.planifierVol(vol1);
        compagnie.planifierVol(vol2);

        // Rechercher des vols
        List<Vol> vols = compagnie.rechercherVols("Paris", "Lyon", "12/05/2024");

        // Vérifier les résultats
        assertEquals(1, vols.size());
        assertEquals(vol1, vols.get(0));
    }

    @Test
    void testAnnulerVol() {
        // Ajouter le vol
        compagnie.planifierVol(vol1);

        // Annuler le vol
        boolean resultat = compagnie.annulerVol("AT100");

        // Vérifier que l'annulation a réussi
        assertTrue(resultat);

        // Vérifier que le statut du vol est "Annulé"
        assertEquals("Annulé", vol1.getStatut());
    }

    @Test
    void testGetDestinationsPopulaires() {
        // Configuration pour le test
        compagnie.planifierVol(vol1); // Paris -> Lyon
        compagnie.planifierVol(vol2); // Lyon -> Nice

        Passager passager1 = new Passager("P1", "Nom1", "Prenom1", "01/01/1990",
                "Adresse", "0123456789", "email@example.com",
                "PASS1", "Française");
        Passager passager2 = new Passager("P2", "Nom2", "Prenom2", "02/02/1990",
                "Adresse", "0123456789", "email@example.com",
                "PASS2", "Française");

        compagnie.ajouterPassager(passager1);
        compagnie.ajouterPassager(passager2);

        // Créer des réservations
        passager1.reserverVol(vol1, "Economy", 100.0);
        passager1.reserverVol(vol2, "Economy", 100.0);
        passager2.reserverVol(vol1, "Economy", 100.0);

        // Obtenir les destinations populaires
        var destinations = compagnie.getDestinationsPopulaires();

        // Vérifier les résultats
        assertEquals(2, destinations.size());
        assertEquals(2, destinations.get("Lyon")); // 2 passagers pour Lyon
        assertEquals(1, destinations.get("Nice")); // 1 passager pour Nice
    }
}
