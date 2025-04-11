package fr.isep.algo;

import fr.isep.algo.model.Passager;
import fr.isep.algo.model.Reservation;
import fr.isep.algo.model.Vol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassagerTest {

    private Passager passager;
    private Vol vol;

    @BeforeEach
    void setUp() {
        // Créer un passager pour chaque test
        passager = new Passager("P1", "Dupont", "Jean", "01/01/1990",
                "1 rue de Paris", "0123456789", "jean@example.com",
                "AB123456", "Française");

        // Créer un vol pour les tests
        vol = new Vol("AF123", "Paris", "Londres", "12/05/2024",
                "10:00", "11:30", "01:30", 350.0);
    }

    @Test
    void testReserverVol() {
        // Test de réservation d'un vol
        Reservation reservation = passager.reserverVol(vol, "Business", 600.0);

        // Vérifier que la réservation a été créée
        assertNotNull(reservation);

        // Vérifier que la réservation a été ajoutée à la liste des réservations du passager
        assertEquals(1, passager.getReservations().size());

        // Vérifier les détails de la réservation
        assertEquals(vol, reservation.getVol());
        assertEquals("Business", reservation.getClasse());
        assertEquals(600.0, reservation.getPrix());
        assertEquals("Confirmée", reservation.getStatut());
    }

    @Test
    void testAnnulerReservation() {
        // Créer une réservation
        Reservation reservation = passager.reserverVol(vol, "Economy", 300.0);
        String numeroReservation = reservation.getNumeroReservation();

        // Vérifier que la réservation existe
        assertEquals(1, passager.getReservations().size());

        // Annuler la réservation
        boolean resultat = passager.annulerReservation(numeroReservation);

        // Vérifier que l'annulation a réussi
        assertTrue(resultat);

        // Vérifier que la réservation a été supprimée
        assertEquals(0, passager.getReservations().size());
    }

    @Test
    void testObtenirReservation() {
        // Créer une réservation
        Reservation reservation = passager.reserverVol(vol, "First", 1000.0);
        String numeroReservation = reservation.getNumeroReservation();

        // Obtenir la réservation
        Reservation reservationObtenue = passager.obtenirReservation(numeroReservation);

        // Vérifier que la réservation obtenue correspond à celle créée
        assertNotNull(reservationObtenue);
        assertEquals(numeroReservation, reservationObtenue.getNumeroReservation());
        assertEquals(vol, reservationObtenue.getVol());
        assertEquals("First", reservationObtenue.getClasse());
        assertEquals(1000.0, reservationObtenue.getPrix());
    }

    @Test
    void testObtenirReservationInexistante() {
        // Tenter d'obtenir une réservation inexistante
        Reservation reservation = passager.obtenirReservation("INEXISTANT");

        // Vérifier que null est retourné
        assertNull(reservation);
    }

    @Test
    void testObtenirRole() {
        // Vérifier que le rôle est correct
        assertEquals("Passager", passager.obtenirRole());
    }
}