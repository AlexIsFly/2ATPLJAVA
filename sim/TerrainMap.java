package sim;

import carte.Case;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import io.DonneesSimulation;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */

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


    TerrainMap(GUISimulator gui, DonneesSimulation datasim) {
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
        gui.addGraphicalElement(new ImageElement(x+8,y+8,"src/sprites/fire.png",48,48,null));
    }

    private void drawCaseTerrain(Case gcase) {
        x = gcase.getColonne()*64;
        y = gcase.getLigne()*64;
        switch (gcase.getTerrain()) {
            case FORET:
                gui.addGraphicalElement(new ImageElement(x,y,"src/sprites/forest.png",64,64,null));
                break;
            case EAU:
                gui.addGraphicalElement(new ImageElement(x,y,"src/sprites/water.gif",64,64,null));
                break;
            case ROCHE:
                gui.addGraphicalElement(new ImageElement(x,y,"src/sprites/rock.png",64,64,null));
                break;
            case TERRAIN_LIBRE:
                gui.addGraphicalElement(new ImageElement(x,y,"src/sprites/grass.png",64,64,null));
                break;
            case HABITAT:
                gui.addGraphicalElement(new ImageElement(x,y,"src/sprites/grass.png",64,64,null));
                gui.addGraphicalElement(new ImageElement(x+5,y+5,"src/sprites/house.png",54,54,null));
                break;
        }
    }
}

