package sim;

import carte.Case;
import enumdata.Direction;
import evenement.*;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import io.DonneesSimulation;
import io.LecteurDonnees;
import robot.*;

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
        private Timeline timeline;
        private int currentdate;

        public Simulateur(String [] args) {
            //lecture du fichier et initialisation de datasim
            fillSimulationData(args);

            //création de la gui
            createGUI();

            //timeline creation
            timeline = new Timeline(datasim);
            this.currentdate = 1;

            // association a la gui!
            gui.setSimulable(this);

            //dessin du terrain+incendie
            drawTerrain();

            //dessin des robots
            drawRobots();

        }

        @Override
        public void next() {
            System.out.println("DATE "+ this.currentdate);
            System.out.println("---------------------");
            showEvents(currentdate);
            executeEvents(currentdate);
            drawTerrain();
            drawRobots();
            currentdate++;
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
            gui.reset();    // clear the window
            for (Case[] caselig : this.datasim.getCarte().getMap()) {
                for (Case onecase : caselig) {
                    drawCaseTerrain(onecase);
                    if (onecase.isIncendie()) {
                        drawCaseIncendie(onecase);
                    }
                }
            }
        }

        private void drawCaseIncendie(Case gcase) {
            int x = gcase.getColonne()*64;
            int y = gcase.getLigne() * 64;
            gui.addGraphicalElement(new ImageElement(x+8,y+8,"src/sprites/fire.png",48,48,null));
        }

        private void drawCaseTerrain(Case gcase) {
            int x = gcase.getColonne() * 64;
            int y = gcase.getLigne() * 64;
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

        private void drawRobots() {
            for(Robots roboti : this.datasim.getRobotL()) {
                int x = roboti.getPosition().getColonne() * 64;
                int y = roboti.getPosition().getLigne() * 64;
                if (roboti instanceof Drone){
                    gui.addGraphicalElement(new ImageElement(x+12, y+12,"src/sprites/drone.png",40,40,null));
                }
                else if(roboti instanceof RobotChenilles) {
                    gui.addGraphicalElement(new ImageElement(x+12, y+12,"src/sprites/tracks.png",40,40,null));
                }
                else if(roboti instanceof RobotRoues) {
                    gui.addGraphicalElement(new ImageElement(x+12, y+12,"src/sprites/wheels.png",40,40,null));
                }
                else if(roboti instanceof RobotPattes) {
                    gui.addGraphicalElement(new ImageElement(x+12, y+12,"src/sprites/legs.png",40,40,null));
                }
                else {
                    System.out.println("erreur dessin robot");
                }
            }
        }

        public void addEventDirection(int date, Robots rbt, Direction dir) {
            EvenementMoveDir event = new EvenementMoveDir(date,rbt,dir);
            timeline.addEvent(event);
        }

        public void addEventCoord(int date, int lig, int col) {
            EvenementMoveCoord event = new EvenementMoveCoord(date,lig,col);
            timeline.addEvent(event);
        }

        public void addEventArrive(int date, Robots r, int lig, int col) {
            EvenementArrive event = new EvenementArrive(date,r,lig,col);
            timeline.addEvent(event);
        }

        public void addEventRemplir(int date, Robots r) {
            EvenementRemplir event = new EvenementRemplir(date, r);
            timeline.addEvent(event);
        }

        public void addEventIntervention(int date, Robots r) {
            EvenementIntervention event = new EvenementIntervention(date, r);
            timeline.addEvent(event);
        }

        public void showEvents(int date) {
            timeline.showEvents(date);
        }

        private void executeEvents(int date) {
            timeline.executeEvents(date);
        }

        public Robots getaRobot(int index) {
            return datasim.getRobotL()[index];
        }
        
        public int getDate(){
            return this.currentdate;
        }

}
