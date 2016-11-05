package sim;

import carte.Case;
import enumdata.Direction;
import evenement.Evenement;
import evenement.EvenementMoveCoord;
import evenement.EvenementMoveDir;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import io.DonneesSimulation;
import io.LecteurDonnees;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Simulateur implements Simulable {
        private DonneesSimulation datasim = null;
        private GUISimulator gui;


        public Simulateur(String [] args) {
            //lecture du fichier et initialisation de datasim
            fillSimulationData(args);

            //création de la gui
            createGUI();

            // association a la gui!
            gui.setSimulable(this);

            //création du terrain+incendie
            drawTerrain();

        }

        @Override
        public void next() {
        }

        @Override
        public void restart() {
        }

        private void fillSimulationData(String[] args) {
            if (args.length < 1) {
                System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
                System.exit(1);
            }
            try {
                this.datasim = LecteurDonnees.lire(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("fichier " + args[0] + " inconnu ou illisible");
            } catch (DataFormatException e) {
                System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
            }
        }

        private void createGUI(){
            int dimx = this.datasim.getCarte().getNbColonnes() * 64;
            int dimy = this.datasim.getCarte().getNbLignes() * 64;
            this.gui = new GUISimulator(dimx, dimy, Color.WHITE);
        }

        private void drawTerrain() {
            gui.reset();	// clear the window
            for (Case[] caselig : this.datasim.getCarte().getMap()) {
                for (Case onecase : caselig) {
                    drawCaseTerrain(onecase);
                    if (onecase.isIncendie()) {
                        drawCaseIncendie(onecase);
                    }
                }
            }
            //test drawing robots temporary
            gui.addGraphicalElement(new ImageElement(12, 12,"sprites/drone.png",40,40,null));
            gui.addGraphicalElement(new ImageElement(12, 76,"sprites/tracks.gif",40,40,null));
            gui.addGraphicalElement(new ImageElement(12, 140,"sprites/legs.png",40,40,null));
            gui.addGraphicalElement(new ImageElement(12, 216,"sprites/wheels.png",40,40,null));


        }

        private void drawCaseIncendie(Case gcase) {
            int x = gcase.getColonne()*64;
            int y = gcase.getLigne() * 64;
            gui.addGraphicalElement(new ImageElement(x+8,y+8,"sprites/fire.png",48,48,null));
        }

        private void drawCaseTerrain(Case gcase) {
            int x = gcase.getColonne() * 64;
            int y = gcase.getLigne() * 64;
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

        public void addEvent(int date, Direction dir) {
            EvenementMoveDir event = new EvenementMoveDir(date,dir);
            event.execute();
        }

        public void addEvent(int date, int lig, int col) {
            EvenementMoveCoord event = new EvenementMoveCoord(date,lig,col);
            event.execute();
        }

}
