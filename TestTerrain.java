
import carte.Case;
import gui.*;
import io.DonneesSimulation;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;

public class TestTerrain {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            DonneesSimulation datasim = LecteurDonnees.lire(args[0]);
            datasim.printMap();
            System.out.printf("Done now printing map");

            int dimx = datasim.getCarte().getNbColonnes()*64;
            int dimy = datasim.getCarte().getNbLignes()*64;
            GUISimulator gui = new GUISimulator(dimx, dimy, Color.WHITE);

            TerrainMap terrainmap = new TerrainMap(gui,datasim);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class TerrainMap implements Simulable {
    /** L'interface graphique associée */
    private GUISimulator gui;

    /** Abcisse courante de l'invader (bord gauche) */
    private int x;

    /** Ordonnée courante de l'invader (bord supérieur) */
    private int y;

    /** Itérateur sur les abcisses de l'invader au cours du temps */
    private Iterator<Integer> xIterator;

    /** Itérateur sur les ordonnées de l'invader au cours du temps */
    private Iterator<Integer> yIterator;


    TerrainMap(GUISimulator gui, DonneesSimulation datasim) throws IOException {
        this.gui = gui;
        gui.setSimulable(this);// association a la gui!
        draw(datasim);
    }

    /**
     * Programme les déplacements de l'invader.
     */

    @Override
    public void next() {
    }

    @Override
    public void restart() {
    }


    /**
     * Dessine l'invader.
     */
    private void draw(DonneesSimulation datasim) {
        gui.reset();	// clear the window
        for (Case[] caselig : datasim.getCarte().getMap()) {
            for (Case onecase : caselig) {
                drawCaseTerrain(onecase);
                if (onecase.isIncendie()) {
                    drawCaseIncendie(onecase);
                }
            }
        }
    }

    private void drawCaseIncendie(Case gcase) {
        x = gcase.getColonne()*64;
        y = gcase.getLigne()*64;
        gui.addGraphicalElement(new ImageElement(x+8,y+8,"sprites/fire.png",48,48,null));
    }

    private void drawCaseTerrain(Case gcase) {
        x = gcase.getColonne()*64;
        y = gcase.getLigne()*64;
        switch (gcase.getTerrain()) {
            case FORET:
                gui.addGraphicalElement(new ImageElement(x,y,"sprites/forest.png",64,64,null));
                break;
            case EAU:
                gui.addGraphicalElement(new ImageElement(x,y,"sprites/water.gif",64,64,null));
                break;
            case ROCHE:
                gui.addGraphicalElement(new ImageElement(x,y,"sprites/rock.png",64,64,null));
                break;
            case TERRAIN_LIBRE:
                gui.addGraphicalElement(new ImageElement(x,y,"sprites/grass.png",64,64,null));
                break;
            case HABITAT:
                gui.addGraphicalElement(new ImageElement(x,y,"sprites/grass.png",64,64,null));
                gui.addGraphicalElement(new ImageElement(x+5,y+5,"sprites/house.png",54,54,null));
                break;
        }
    }
}
