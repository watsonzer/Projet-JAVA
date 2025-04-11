package fr.isep.algo;

import com.airline.model.*;
import com.airline.util.FileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private List<Vol> vols;
    private List<Passager> passagers;
    private List<Avion> avions;
    private CompagnieAerienne compagnie;

    private final String VOLS_FILE = "test_vols.csv";
    private final String PASSAGERS_FILE = "test_passagers.csv";
    private final String AVIONS_FILE = "test_avions.csv";

    @BeforeEach
    void setUp() {
        // Initialiser les listes
        vols = new ArrayList<>();
        passagers = new ArrayList<>();
        avions = new ArrayList<>();

        // Créer des objets de test
        Vol vol = new Vol("TEST100", "Paris", "Lyon", "12/05/2024",
                "10:00", "11:00", "01:00", 400.0);
        vols.add(vol);

        Passager passager = new Passager("P1", "Dupont", "Jean", "01/01/1990",
                "1 rue de Paris", "0123456789", "jean@example.com",
                "AB123456", "Française");
        passagers.add(passager);

        Avion avion = new Avion("F-TEST", "Airbus A320", "Airbus",
                16, 40, 120, 5000, 2020);
        avions.add(avion);

        // Initialiser la compagnie
        compagnie = new CompagnieAerienne("Air Test", "AT", "Adresse Test",
                "0123456789", "contact@airtest.com", "www.airtest.com");
    }

    @AfterEach
    void tearDown() {
        // Supprimer les fichiers de test
        new File(VOLS_FILE).delete();
        new File(PASSAGERS_FILE).delete();
        new File(AVIONS_FILE).delete();
    }

    @Test
    void testExportImportVols() throws IOException {
        // Exporter les vols
        FileManager.exportVolsToCSV(vols, VOLS_FILE);

        // Vérifier que le fichier existe
        File file = new File(VOLS_FILE);
        assertTrue(file.exists());

        // Importer les vols
        List<Vol> volsImportes = FileManager.importVolsFromCSV(VOLS_FILE, compagnie);

        // Vérifier les données importées
        assertEquals(1, volsImportes.size());
        Vol volImporte = volsImportes.get(0);
        assertEquals("TEST100", volImporte.getNumeroVol());
        assertEquals("Paris", volImporte.getOrigine());
        assertEquals("Lyon", volImporte.getDestination());
    }

    @Test
    void testExportImportPassagers() throws IOException {
        // Exporter les passagers
        FileManager.exportPassagersToCSV(passagers, PASSAGERS_FILE);

        // Vérifier que le fichier existe
        File file = new File(PASSAGERS_FILE);
        assertTrue(file.exists());

        // Importer les passagers
        List<Passager> passagersImportes = FileManager.importPassagersFromCSV(PASSAGERS_FILE);

        // Vérifier les données importées
        assertEquals(1, passagersImportes.size());
        Passager passagerImporte = passagersImportes.get(0);
        assertEquals("P1", passagerImporte.getId());
        assertEquals("Dupont", passagerImporte.getNom());
        assertEquals("Jean", passagerImporte.getPrenom());
        assertEquals("AB123456", passagerImporte.getNumeroPasseport());
    }

    @Test
    void testExportImportAvions() throws IOException {
        // Exporter les avions
        FileManager.exportAvionsToCSV(avions, AVIONS_FILE);

        // Vérifier que le fichier existe
        File file = new File(AVIONS_FILE);
        assertTrue(file.exists());

        // Importer les avions
        List<Avion> avionsImportes = FileManager.importAvionsFromCSV(AVIONS_FILE);

        // Vérifier les données importées
        assertEquals(1, avionsImportes.size());
        Avion avionImporte = avionsImportes.get(0);
        assertEquals("F-TEST", avionImporte.getImmatriculation());
        assertEquals("Airbus A320", avionImporte.getModele());
        assertEquals("Airbus", avionImporte.getFabricant());
        assertEquals(16, avionImporte.getCapaciteFirst());
        assertEquals(40, avionImporte.getCapaciteBusiness());
        assertEquals(120, avionImporte.getCapaciteEconomy());
    }
}
