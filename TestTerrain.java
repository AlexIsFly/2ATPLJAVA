
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


    public TerrainMap(GUISimulator gui, DonneesSimulation datasim) throws IOException {
        this.gui = gui;
        gui.setSimulable(this);// association a la gui!
        for (Case[] caselig : datasim.getCarte().getMap()) {
            for (Case onecase : caselig) {
                drawCase(onecase);
            }
        }
    }

    /**
     * Programme les déplacements de l'invader.
     */

    @Override
    public void next() {
        if (this.xIterator.hasNext())
            this.x = this.xIterator.next();
        if (this.yIterator.hasNext())
            this.y = this.yIterator.next();
        draw();
    }

    @Override
    public void restart() {
        draw();
    }


    /**
     * Dessine l'invader.
     */
    private void draw() {
        gui.reset();	// clear the window

        gui.addGraphicalElement(new ImageElement(0,0,"sprites/water.gif",64,64,null));

        gui.addGraphicalElement(new ImageElement(164,200,"sprites/rock.png",64,64,null));

        gui.addGraphicalElement(new ImageElement(228,200,"sprites/grass.png",64,64,null));
        gui.addGraphicalElement(new ImageElement(228+5,200+5,"sprites/house.png",54,54,null));

        gui.addGraphicalElement(new ImageElement(164+64+64,200,"sprites/grass.png",64,64,null));

        gui.addGraphicalElement(new ImageElement(164+64+64+64,200,"sprites/forest.png",64,64,null));
    }

    private void drawCase(Case gcase) {
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
