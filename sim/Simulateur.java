package sim;

import carte.Case;
import carte.Chemin;
import enumdata.Direction;
import evenement.*;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import gui.Text;
import io.DonneesSimulation;
import io.LecteurDonnees;
import robot.*;

import java.awt.*;
import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
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
        private String[] argsBak;

        public Simulateur(String [] args) {
            this.argsBak = args;
            //lecture du fichier et initialisation de datasim
            fillSimulationData(args);

            //création de la gui
            createGUI();

            //timeline creation
            timeline = new Timeline(datasim);
            this.currentdate = 0;

            //creation graph for each robot
            fillGrapheRobots();

            // association a la gui!
            gui.setSimulable(this);

            //dessin du terrain+incendie
            drawTerrain();

            //dessin des robots
            drawRobots();

            drawText();

        }

        @Override
        public void next() {
            currentdate++;
            if (!isSimOver()) {
                lanceChefRobot();
            }
            System.out.println();
            System.out.println("ACTION : DATE "+ this.currentdate);
            System.out.println("---------------------");
            executeEvents(currentdate);
            drawTerrain();
            drawRobots();
            drawText();
        }

        @Override
        public void restart() {
            this.currentdate = 0;
            fillSimulationData(argsBak);
            this.timeline = new Timeline(this.datasim);
            drawTerrain();
            drawRobots();
            drawText();
            lanceChefRobot();
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

        private void fillGrapheRobots(){
            for(Robots roboti : this.datasim.getRobotL()) {
                roboti.creeGraphe(this.datasim.getCarte());
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
                    if (onecase.getQteEau() > 0) {
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

        private void drawText() {
            for (int i=0; i<8; i++) {
                String number = "";
                number = number + i;
                gui.addGraphicalElement(new Text(10, 10+i * 64, Color.black, number));
            }
            for (int i=0; i<8; i++) {
                String number = "";
                number = number + i;
                gui.addGraphicalElement(new Text(10+i * 64, 10, Color.black, number));
            }
            for (Case caseL[] : datasim.getCarte().getMap()) {
                for (Case casei : caseL) {
                    if (casei.isIncendie()){
                        String number = "";
                        number = number + casei.getQteEau();
                        gui.addGraphicalElement(new Text(casei.getColonne()*64+30,casei.getLigne()*64+40,Color.white, number));
                    }
                }
            }
        }

        private boolean isSimOver(){
            boolean resteDuFeu = false;
            for (Case caseL[] : datasim.getCarte().getMap()) {
                for (Case casei : caseL) {
                    if (casei.getQteEau() != 0) {
                        resteDuFeu = true;
                    }
                }
            }
            return (!resteDuFeu);
        }

        private void addEventDirection(int date, Robots rbt, Direction dir) {
            EvenementMoveDir event = new EvenementMoveDir(date,rbt,dir);
            timeline.addEvent(event);
        }

        private void addEventMoveRemplir(Robots rbt, int lig, int col) {

            int[] posCourante = rbt.getPosition().getCoord();
            int[] posFinale = {lig,col};
            LinkedList<int[]> tabChemin;
            rbt.setIdle(false);

            LinkedList<LinkedList<LinkedList<int[]>>> grapheT = rbt.getGraphe();
            // Si la case est inacessible
            if (grapheT.get(lig).get(col).size() == 0) {
                System.out.println("Case inaccessible");
                rbt.setIdle(true);
                return;
            }

            int pseudodate = currentdate;
            Chemin c = new Chemin( posCourante, posFinale, rbt.getGraphe());
            //System.out.println("Chemin Done");
            tabChemin=c.plusCourtChemin();
            //System.out.println("PCC done");
            ListIterator li = tabChemin.listIterator();

            int[] prevcoord = posCourante;
            int[] suivcoord;

            while (li.hasNext()){
                suivcoord = (int [])li.next();
                switch (suivcoord[0] - prevcoord[0]){
                    case -1:
                        addEventDirection(pseudodate,rbt,Direction.NORD);
                        break;
                    case 1:
                        addEventDirection(pseudodate,rbt,Direction.SUD);
                        break;
                    //sinon on regarde quel déplacement horizontal est à faire
                    case 0:
                        switch (suivcoord[1] - prevcoord[1]){
                            case 1:
                                addEventDirection(pseudodate,rbt,Direction.EST);
                                break;
                            case -1:
                                addEventDirection(pseudodate,rbt,Direction.OUEST);
                                break;
                            //si one est dans ce cas il y a une erreur, car on aurait du sortir du while.
                            case 0:
                                System.out.println("ERREUR planification déplacement");
                                rbt.setIdle(true);
                                return;
                            default:
                                System.out.println("ERREUR planification déplacement");
                                rbt.setIdle(true);
                                return;
                        }
                        break;
                    default:
                        System.out.println("ERREUR planification déplacement");
                        rbt.setIdle(true);
                        return;
                }
                prevcoord = suivcoord;
                pseudodate++;
            }
            //on sort donc on a parcouru tout tabChemein, on peut créer l'evenement d'arrivée
            addEventRemplir(rbt, pseudodate);
        }

        private void addEventMoveIntervenir(Robots rbt, int lig, int col) {

        int[] posCourante = rbt.getPosition().getCoord();
        int[] posFinale = {lig,col};
        LinkedList<int[]> tabChemin;
        rbt.setIdle(false);

        LinkedList<LinkedList<LinkedList<int[]>>> grapheT = rbt.getGraphe();
        // Si la case est inacessible
        if (grapheT.get(lig).get(col).size() == 0) {
            System.out.println("Case inaccessible");
            rbt.setIdle(true);
            return;
        }

        int pseudodate = currentdate;
        Chemin c = new Chemin( posCourante, posFinale, rbt.getGraphe());
        //System.out.println("Chemin Done");
        tabChemin=c.plusCourtChemin();
        //System.out.println("PCC done");
        ListIterator li = tabChemin.listIterator();

        int[] prevcoord = posCourante;
        int[] suivcoord;

        while (li.hasNext()){
            suivcoord = (int [])li.next();
            switch (suivcoord[0] - prevcoord[0]){
                case -1:
                    addEventDirection(pseudodate,rbt,Direction.NORD);
                    break;
                case 1:
                    addEventDirection(pseudodate,rbt,Direction.SUD);
                    break;
                //sinon on regarde quel déplacement horizontal est à faire
                case 0:
                    switch (suivcoord[1] - prevcoord[1]){
                        case 1:
                            addEventDirection(pseudodate,rbt,Direction.EST);
                            break;
                        case -1:
                            addEventDirection(pseudodate,rbt,Direction.OUEST);
                            break;
                        //si one est dans ce cas il y a une erreur, car on aurait du sortir du while.
                        case 0:
                            System.out.println("ERREUR planification déplacement");
                            rbt.setIdle(true);
                            return;
                        default:
                            System.out.println("ERREUR planification déplacement");
                            rbt.setIdle(true);
                            return;
                    }
                    break;
                default:
                    System.out.println("ERREUR planification déplacement");
                    rbt.setIdle(true);
                    return;
            }
            prevcoord = suivcoord;
            pseudodate++;
        }
        //on sort donc on a parcouru tout tabChemein, on peut créer l'evenement d'arrivée
        addEventIntervention(rbt, pseudodate-1);
    }

        public void addEventRemplir(Robots r, int date) {
            r.setIdle(false);
            EvenementRemplir event = new EvenementRemplir(date + r.getReservoir().getTempsRemplissage(),r);
            timeline.addEvent(event);
        }

        public void addEventIntervention(Robots r, int date) {
            r.setIdle(false);
            EvenementIntervention event = new EvenementIntervention(date +r.getReservoir().getTempsIntervention(),r);
            timeline.addEvent(event);
        }

        private void executeEvents(int date) {
            timeline.executeEvents(date);
        }

        public int getDate(){
            return this.currentdate;
        }

        /**
        public void demandeUtilisateur() {
            Scanner sc = new Scanner(System.in);
            int choice, lig, col;

            Robots[] robotL = datasim.getRobotL();
            System.out.println();
            System.out.println("PLANIFICATION : DATE "+ this.currentdate);
            System.out.println("---------------------");
            System.out.println();
            for (Robots rbt : robotL) {
                if (rbt.isIdle()) {
                    do {
                        System.out.println();
                        System.out.println("Que souhaitez vous faire pour le robot + " + rbt.toString() + " situé sur " + rbt.getPosition().toString());
                        System.out.println("1) Déplacer le robot");
                        System.out.println("2) Mettre le robot en état occupé (en attendant Intervention Incendie)");
                        System.out.println("3) Mettre le robot en état libre (en attendant Remplissage)");
                        choice = sc.nextInt();
                    }
                    while (!(choice == 1 || choice == 2 || choice==3));
                    System.out.println("Votre choix est : "+ choice);
                    switch(choice){
                        case 1:
                            //pour des raisons de test, le chemin est prédéfini dans addEventCoord

                            System.out.println("Coordonnées : quelle ligne ?");
                            lig = sc.nextInt();
                            System.out.println("Coordonnées : quelle colonne ?");
                            col = sc.nextInt();
                            addEventCoord(rbt, lig, col);
                            break;
                        case 2:
                            addEventIntervention(rbt);
                            break;
                        case 3:
                            addEventRemplir(rbt, );
                            break;
                    }
                }
                else {
                    System.out.println("Le robot " + rbt.toString() + " est occupé !");
                }
            }
            System.out.println("Veuillez appuyer sur Suivant");
            System.out.println();
        }
        */
        public void testRnd() {
            for (int i=0; i<15; i++) {
                System.out.println("CASE EAU RND : " + datasim.getCarte().getRandomWaterCase().toString());
                System.out.println("CASE COAST RND : " + datasim.getCarte().getRandomCoastCase().toString());
            }
        }

        public void lanceChefRobot() {
            Case casernd;
            for (Case caseL[] : datasim.getCarte().getMap()) {
                for (Case casei : caseL) {
                    if (casei.getQteEau() > 0 && !casei.isIncendieAffected()) {
                        //casei EST un incendie à ce niveau là
                        //System.out.println("Case séléctionée : " + casei.toString());
                        for(Robots roboti : datasim.getRobotL()) {
                            //System.out.println("Robot dans liste : " + roboti.toString());
                            if (roboti.isIdle() && roboti.getGraphe().get(casei.getLigne()).get(casei.getColonne()).size() > 0) {
                                //System.out.println("Robot libre : " + roboti.toString());
                                if (roboti.getReservoir().getVolumeCourant() == 0 &&  roboti.getGraphe().get(5).get(3).size() > 0) {
                                    if (roboti instanceof Drone) {
                                        casernd = datasim.getCarte().getRandomWaterCase();
                                        addEventMoveRemplir(roboti, casernd.getLigne(), casernd.getColonne());
                                    }
                                    else {
                                        casernd = datasim.getCarte().getRandomCoastCase();
                                        addEventMoveRemplir(roboti, casernd.getLigne(), casernd.getColonne());
                                    }
                                    //System.out.println("Robot bouge remplir : " + roboti.toString());
                                }
                                else {
                                    casei.setIncendieAffected(true);
                                    addEventMoveIntervenir(roboti, casei.getLigne(), casei.getColonne());
                                    //System.out.println("Robot bouge intervenir : " + roboti.toString());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
}
