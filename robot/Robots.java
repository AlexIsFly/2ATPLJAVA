package robot;

import carte.Case;
import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 26/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Robots {

    protected Case caseRobot;
    protected int reservoir;
    protected int capaciteMaxReservoir;
    protected double vitesse;

    //constructeur
    public Robots(Case caseRobot, int capaciteMaxReservoir, double vitesse) {
        this.caseRobot = caseRobot;
        this.capaciteMaxReservoir = capaciteMaxReservoir;
        this.reservoir = capaciteMaxReservoir;
        this.vitesse = vitesse;
    }

    //constructeur lorsque la vitesse n'est pas scécifié dans le fichier
    public Robots(Case caseRobot, int capaciteMaxReservoir) {
        this.caseRobot = caseRobot;
        this.capaciteMaxReservoir = capaciteMaxReservoir;
        this.reservoir = capaciteMaxReservoir;
    }

    //constructeur par défaut
    public Robots() {
        //constructeur par défaut de Case (coord (0,0), type EAU, pas d'incendie)
        this.caseRobot = new Case();
        this.reservoir = 0;
        this.capaciteMaxReservoir = 0;
        this.vitesse = 0;
    }

    public Case getPosition() {
        return this.caseRobot;
    }

    public void setPosition(Case caseUser) {
        this.caseRobot = caseUser;
    }

    public double getVitesse(NatureTerrain ntUser) {
        // Pas du tout complète il faut regarder la nature du terrain
        return this.vitesse;
    }
    void deverserEau(int volume)  {
        // Décrémenter le volume du robot mais aussi décrémenter l'incendie : attendre DORR
        if (this.reservoir - volume >= 0) {
            this.reservoir -= volume;
        }
        else {
            volume = volume - this.reservoir;
            this.reservoir = 0;
            System.out.println("Seulement " + volume + " d'eau a pu être versé.");
        }
    }
    void remplirReservoir() {

        // Ça marche pas le double égal mais je sais pas comment faire pour l'instant
        if (this.caseRobot.equalsTerrain("EAU")) {
            this.reservoir = capaciteMaxReservoir;
        }
        else {
            System.out.println("Le robot ne se situe pas sur un point d'eau");
        }
    }
}
