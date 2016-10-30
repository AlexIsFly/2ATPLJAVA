package sim;

import gui.GUISimulator;
import io.DonneesSimulation;
import io.LecteurDonnees;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Simulateur {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        DonneesSimulation datasim = null;
        try {
            //lecture du fichier et création de datasim qui contient toutes les données nécessaires
            datasim = LecteurDonnees.lire(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

            //création de la gui
            int dimx = datasim.getCarte().getNbColonnes() * 64;
            int dimy = datasim.getCarte().getNbLignes() * 64;
            GUISimulator gui = new GUISimulator(dimx, dimy, Color.WHITE);

            //création du terrain+incendie
            TerrainMap terrainmap = new TerrainMap(gui, datasim);
    }
}
