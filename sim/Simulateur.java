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
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */
class Simulateur implements Simulable {
        private DonneesSimulation datasim = null;
        private GUISimulator gui;
        private Timeline timeline;
        private int currentdate;
        private String[] argsBak;


    /**
     * This object is the core of the simulation
     * It manages all the events
     * This constructor is used to initialize the simulation
     * @param args the name of the map to use
     */
    public Simulateur(String [] args) {
        //saves the program arguments to start the simulation again
        this.argsBak = args;

        //lecture du fichier et initialisation de datasim
        fillSimulationData(args);

        //creation graph for each robot
        fillGrapheRobots();

        //création de la gui
        createGUI();

        // association a la gui!
        gui.setSimulable(this);

        //dessin du terrain+incendie
        drawTerrain();

        //dessin des robots
        drawRobots();

        drawText();

        //timeline creation
        timeline = new Timeline(datasim);
        this.currentdate = 0;



    }

    /**
     * Advances the simulation by one step
     */
    @Override
    public void next() {
        currentdate++;
        if (!isSimOver()) {
            lanceChefRobot();
        }
        else {
            return;
        }
        System.out.println();
        System.out.println("ACTION : DATE "+ this.currentdate);
        System.out.println("---------------------");
        executeEvents(currentdate);
        drawTerrain();
        drawRobots();
        drawText();
    }

    /**
     * Restart the simulation from the beginning
     * <p>Not Working so well, a fire get stuck in affected state so the sim doesn't stop correctly</p>
     *
     */
    @Override
    public void restart() {
        this.currentdate = 0;
        fillSimulationData(argsBak);
        this.timeline = new Timeline(datasim);
        drawTerrain();
        drawRobots();
        drawText();
    }

    public int getDate(){
        return this.currentdate;
    }

    /**
     * Creates the DonneesSimulation instance
     * @param args
     */
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


    /**
     * Creates graphes for each robot
     */
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


    /**
     * Draw the terrain with corresponding texture in the GUI
     */
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
        if (gcase.isIncendieAffected()) {
            gui.addGraphicalElement(new ImageElement(x+17,y+17,"src/sprites/frog.gif",30,30,null));

        }
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


    /**
     * Draw the robots with corresponding texture in the GUI
     */
    private void drawRobots() {
        for(Robots roboti : this.datasim.getRobotL()) {
            int x = roboti.getCaseRobot().getColonne() * 64;
            int y = roboti.getCaseRobot().getLigne() * 64;
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


    /**
     * Writes relevant values in the GUI
     */
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
                    gui.addGraphicalElement(new Text(casei.getColonne()*64+30,casei.getLigne()*64+5,Color.white, number));
                }
            }
        }
    }


    /**
     * Check if the simulation is over or not
     * @return true if there are no more fire left
     */
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


    /**
     * Creates an elementary move event and add it to the timeline
     * @param date the date to execute the event
     * @param rbt the robot to perform the event
     * @param dir the direction to move
     */
    private void addEventDirection(int date, Robots rbt, Direction dir) {
        EvenementMoveDir event = new EvenementMoveDir(date,rbt,dir);
        timeline.addEvent(event);
    }


    /**
     * Creates an elementary event to fill the robot with water
     * @param r the robot to perform the event
     * @param date the date to execute the event
     */
    private void addEventRemplir(Robots r, int date) {
        r.setIdle(false);
        EvenementRemplir event = new EvenementRemplir(date + r.getReservoir().getTempsRemplissage(),r);
        timeline.addEvent(event);
    }

    /**
     * Creates an elementary event to put out the fire
     * @param r the robot to perform the event
     * @param date the date to execute the event
     */
    private void addEventIntervention(Robots r, int date) {
        r.setIdle(false);
        EvenementIntervention event = new EvenementIntervention(date +r.getReservoir().getTempsIntervention(),r);
        timeline.addEvent(event);
    }


    /**
     * Create a complex event to move and fill with water
     * <p>The date is not a parameter, because we don't know how long the path is going to be
     * so elementary event are created starting from the current date and incrementing</p>
     * @param rbt Robot
     * @param lig Y coordinate to the destination
     * @param col X coordinate to the destination
     */
    private void addEventMoveRemplir(Robots rbt, int lig, int col) {

        rbt.setIdle(false);

        LinkedList<int[]> tabChemin;
        LinkedList<LinkedList<LinkedList<int[]>>> grapheT = rbt.getGraphe();
        int[] prevcoord = rbt.getCaseRobot().getCoord();
        int[] suivcoord;
        int[] posFinale = {lig,col};
        int pseudodate = currentdate;
        Chemin c = new Chemin( prevcoord, posFinale, rbt.getGraphe());

        // Si la case est inacessible
        if (grapheT.get(lig).get(col).size() == 0) {
            System.out.println("Case inaccessible");
            rbt.setIdle(true);
            return;
        }

        tabChemin=c.plusCourtChemin();
        ListIterator li = tabChemin.listIterator();

        while (li.hasNext()){
            suivcoord = (int [])li.next();
            //on regarde si on a des déplacements verticaux à effectuer
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
                        //on est soit au départ où une instruction dans tabChemin se répéte
                        //donc il y a une erreur
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
        //on sort donc on a parcouru tout tabChemin, on peut créer l'evenement de remplissage
        addEventRemplir(rbt, pseudodate);
    }

    /**
     * Create a complex event to move and put out a fire
     * <p>The date is not a parameter, because we don't know how long the path is going to be
     * so elementary event are created starting from the current date and incrementing</p>
     * @param rbt Robot
     * @param lig Y coordinate to the destination
     * @param col X coordinate to the destination
     */
    private void addEventMoveIntervenir(Robots rbt, int lig, int col) {
        rbt.setIdle(false);

        LinkedList<int[]> tabChemin;
        LinkedList<LinkedList<LinkedList<int[]>>> grapheT = rbt.getGraphe();
        int[] prevcoord = rbt.getCaseRobot().getCoord();
        int[] suivcoord;
        int[] posFinale = {lig,col};
        int pseudodate = currentdate;
        Chemin c = new Chemin( prevcoord, posFinale, rbt.getGraphe());

        // Si la case est inacessible
        if (grapheT.get(lig).get(col).size() == 0) {
            System.out.println("Case inaccessible");
            rbt.setIdle(true);
            return;
        }

        tabChemin=c.plusCourtChemin();
        ListIterator li = tabChemin.listIterator();

        while (li.hasNext()){
            suivcoord = (int [])li.next();
            //on regarde si on a des déplacements verticaux à effectuer
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
                        //on est soit au départ où une instruction dans tabChemin se répéte
                        //donc il y a une erreur
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
        //on sort donc on a parcouru tout tabChemin, on peut créer l'evenement d'intervention
        addEventIntervention(rbt, pseudodate-1);
    }

    /**
     * Execute all the events for the date in parameter
     * @param date
     */
    private void executeEvents(int date) {
        timeline.executeEvents(date);
    }


    /**
     * A manual firefighter chief to give individual orders to the robots
     * For testing purposes only
     * It's a bit obsolete now
     */
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
                    System.out.println("Que souhaitez vous faire pour le robot + " + rbt.toString() + " situé sur " + rbt.getCaseRobot().toString());
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
                        //addEventCoord(rbt, lig, col);
                        break;
                    case 2:
                        //addEventIntervention(rbt);
                        break;
                    case 3:
                        //addEventRemplir(rbt, );
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


    private void lanceChefRobot() {
        Case casernd;
        for (Case caseL[] : datasim.getCarte().getMap()) {
            for (Case casei : caseL) {
                if (casei.getQteEau() > 0 && !casei.isIncendieAffected()) {
                    //casei EST un incendie NON affecté à ce niveau là
                    for(Robots roboti : datasim.getRobotL()) {
                        //on parcoure la liste des robots
                        if (roboti.isIdle() && roboti.getGraphe().get(casei.getLigne()).get(casei.getColonne()).size() > 0) {
                            //on trouve un robot libre ET qui peut atteindre l'incendie
                            if (roboti.getReservoir().getVolumeCourant() == 0 &&  roboti.getGraphe().get(5).get(3).size() > 0) {
                                //un fois trouvé si celui ci est vide ET qui a accés à de l'eau on l'envoie se remplir
                                if (roboti instanceof Drone) {
                                    casernd = datasim.getCarte().getRandomWaterCase();
                                    addEventMoveRemplir(roboti, casernd.getLigne(), casernd.getColonne());
                                }
                                else {
                                    casernd = datasim.getCarte().getRandomCoastCase();
                                    addEventMoveRemplir(roboti, casernd.getLigne(), casernd.getColonne());
                                }
                            }
                            else {
                                //si le robot n'est pas vide on l'affecte à l'incendie et on l'envoie l'éteindre
                                casei.setIncendieAffected(true);
                                addEventMoveIntervenir(roboti, casei.getLigne(), casei.getColonne());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
