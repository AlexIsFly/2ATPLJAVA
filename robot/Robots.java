package robot;

import carte.Carte;
import carte.Case;

import java.util.LinkedList;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */

public class Robots {

    private String name = "";
    Case caseRobot;
    Reservoir reservoir;
    Vitesse vitesse;
    private boolean idle;
    private static LinkedList<LinkedList<LinkedList<int[]>>> graphe;


    /**
     * super Constructor of Robots
     * @param caseRobot the Case on which the robot is
     * @param reservoir an instance of Reservoir
     * @param vitesse an instance of Vitesse
     * @param pname the type of robot
     */
    Robots(Case caseRobot, Reservoir reservoir, Vitesse vitesse, String pname) {
        this.caseRobot = caseRobot;
        this.reservoir = reservoir;
        this.vitesse = vitesse;
        this.idle = true;
        this.name = pname;
    }

    Robots() {
        this.caseRobot = null;
        this.reservoir = null;
        this.vitesse = null;
        this.idle = true;
        this.name = "BLANK";
    }

    public String getName() {
        return name;
    }

    public Case getCaseRobot() {
        return caseRobot;
    }

    public Reservoir getReservoir() {
        return reservoir;
    }

    public Vitesse getVitesse() {
        return vitesse;
    }

    public boolean isIdle() {
        return idle;
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphe() {
        return graphe;
    }

    public void setCaseRobot(Case caseRobot) {
        this.caseRobot = caseRobot;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }


    public void deverserEau() {
        System.out.println("Ce robot a un reservoir considéré infini !");
    }

    public void remplirReservoir(Carte carte){
        System.out.println("Ce robot n'a pas besoin de se remplir !");
    }

    public void creeGraphe(Carte carte) {
        System.out.println("Super-méthode creeGraphe");
    }


    /**
     * @return a formatted string of a Robot
     */
    @Override
    public String toString() {
        return "Robots{" +
                "name=" + name +
                ", position=" + "("+caseRobot.getCoord()[0]+", "+caseRobot.getCoord()[1]+")" +
                ", reservoir=" + reservoir.getVolumeCourant() +
                ", idle=" + idle +
                '}';
    }
}
