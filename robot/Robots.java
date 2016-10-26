package robot;

import carte.Case;
import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 26/10/2016.
 */
public class Robots {

    private Case caseRobot;
    private int reservoir;
    private int capaciteMaxReservoir;
    private double vitesse;

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
        if (this.caseRobot.getTerrain() == "EAU") {
            this.reservoir = capaciteMaxReservoir;
        }
        else {
            System.out.println("Le robot ne se situe pas sur un point d'eau");
        }
    }
}
