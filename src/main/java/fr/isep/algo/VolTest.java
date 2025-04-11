package fr.isep.algo;

import fr.isep.algo.model.Avion;
import fr.isep.algo.model.Vol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolTest {

    private Vol vol;
    private Avion avion;

    @BeforeEach
    void setUp() {
        // Créer un vol pour chaque test
        vol = new Vol("AF123", "Paris", "Londres", "12/05/2024",
                "10:00", "11:30", "01:30", 350.0);

        // Créer un avion pour les tests
        avion = new Avion("F-ABCD", "Airbus A320", "Airbus",
                16, 40, 120, 5000, 2020);
    }

    @Test
    void testAffecterAvion() {
        // Test d'affectation d'un avion à un vol
        vol.setAvion(avion);

        // Vérifier que l'avion est bien affecté
        assertSame(avion, vol.getAvion());

        // Vérifier que les places disponibles sont correctement initialisées
        assertEquals(16, vol.getPlacesDisponiblesFirst());
        assertEquals(40, vol.getPlacesDisponiblesBusiness());
        assertEquals(120, vol.getPlacesDisponiblesEconomy());
    }

    @Test
    void testAnnulerVol() {
        // Test d'annulation d'un vol
        vol.setAvion(avion);

        // Annuler le vol
        boolean resultat = vol.annulerVol();

        // Vérifier que l'annulation a réussi
        assertTrue(resultat);

        // Vérifier que le statut est bien "Annulé"
        assertEquals("Annulé", vol.getStatut());

        // Vérifier que l'avion n'est plus affecté
        assertNull(vol.getAvion());
    }

    @Test
    void testImpossibleAnnulerVolTermine() {
        // Test d'annulation d'un vol déjà terminé
        vol.setStatut("Terminé");

        // Essayer d'annuler le vol
        boolean resultat = vol.annulerVol();

        // Vérifier que l'annulation a échoué
        assertFalse(resultat);

        // Vérifier que le statut est toujours "Terminé"
        assertEquals("Terminé", vol.getStatut());
    }

    @Test
    void testObtenirVol() {
        // Test de la méthode pour obtenir les informations d'un vol
        String infos = vol.obtenirVol();

        // Vérifier que les informations contiennent les détails du vol
        assertTrue(infos.contains("AF123"));
        assertTrue(infos.contains("Paris"));
        assertTrue(infos.contains("Londres"));
        assertTrue(infos.contains("12/05/2024"));
        assertTrue(infos.contains("10:00"));
    }
}