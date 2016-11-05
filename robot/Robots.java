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
    protected Vitesse vitesse;

    //constructeur
    public Robots(Case caseRobot, int capaciteMaxReservoir, Vitesse vitesse) {
        this.caseRobot = caseRobot;
        this.capaciteMaxReservoir = capaciteMaxReservoir;
        this.reservoir = capaciteMaxReservoir;
        this.vitesse = vitesse;
    }

    //constructeur par défaut
    public Robots() {
        //constructeur par défaut de Case (coord (0,0), type EAU, pas d'incendie)
        this.caseRobot = new Case();
        this.reservoir = 0;
        this.capaciteMaxReservoir = 0;
    }

    public Case getPosition() {
        return this.caseRobot;
    }

    public void setPosition(Case caseUser) {
        this.caseRobot = caseUser;
    }

    public double getVitesse(NatureTerrain ntUser) {
        if (NatureTerrain.valueOf("FORET") == ntUser) {
            return this.vitesse.getVitesseForet();
        }
        if (NatureTerrain.valueOf("EAU") == ntUser) {
            return this.vitesse.getVitesseEau();
        }
        if (NatureTerrain.valueOf("ROCHE") == ntUser) {
            return this.vitesse.getVitesseRoche();
        }
        if (NatureTerrain.valueOf("HABITAT") == ntUser) {
            return this.vitesse.getVitesseHabitat();
        }
        return 0;
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
        if (this.caseRobot.equalsTerrain("EAU")) {
            this.reservoir = capaciteMaxReservoir;
        }
        else {
            System.out.println("Le robot ne se situe pas sur un point d'eau");
        }
    }
}
