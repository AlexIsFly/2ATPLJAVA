package robot;

import carte.Case;
import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 26/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Robots {

    protected Case caseRobot;
    protected Reservoir reservoir;
    protected Vitesse vitesse;

    //constructeur
    public Robots(Case caseRobot, Reservoir reservoir, Vitesse vitesse) {
        this.caseRobot = caseRobot;
        this.reservoir = reservoir;
        this.vitesse = vitesse;
    }

    public Robots(Case caseRobot) {
        this.caseRobot = caseRobot;
    }

    //constructeur par défaut
    public Robots() {
        //constructeur par défaut de Case (coord (0,0), type EAU, pas d'incendie)
        this.caseRobot = new Case();
    }

    public Case getPosition() {
        return this.caseRobot;
    }

    public void setPosition(Case caseUser) {
        this.caseRobot = caseUser;
    }

    public double getVitesse(NatureTerrain ntUser) {
        if (NatureTerrain.valueOf("TERRAIN_LIBRE") == ntUser) {
            return this.vitesse.getVitesseTerrainLibre();
        }
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
        // Dire également le temps que ça a mis à verser l'eau
        double temps;

        if (this.reservoir.getVolumeCourant() < 1000000000) {
            if (this.reservoir.getVolumeCourant() - volume >= 0) {
                this.reservoir.setVolumeCourant(this.reservoir.getVolumeCourant() - volume);
            }
            else {
                volume = volume - this.reservoir.volumeCourant;
                this.reservoir.setVolumeCourant(0);
                System.out.println("Seulement " + volume + " d'eau a pu être versé.");
            }
        }
        temps = ((volume % this.reservoir.getVolumeIntervention())/this.reservoir.getVolumeIntervention())*this.reservoir.getTempsIntervention();
        temps += (volume / this.reservoir.getVolumeIntervention()) * this.reservoir.getTempsIntervention();

        System.out.println("Temps pour verser eau : " + temps);
    }

 /*   void remplirReservoir() {
        // On fait en fonction des robots : pour savoir le type de robot qu'on traite on regarde le reservoir.getTempsIntervention() qui est différent en fonction des robots

        // Robot Drone


        if (this.reservoir.getTempsIntervention() == 30) {
            if (this.caseRobot.equalsTerrain("EAU")) {
                this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
            }
        }
        // Robot à roues et robot à chenilles
        else if (this.reservoir.getTempsIntervention() == 5 || this.reservoir.getTempsIntervention() == 8) {
            // On regarde si les cases à côté sont de l'eau
            // ATTENTION : Gros problème si on est sur un bord de la map...
            Case tempCase1 = new Case(this.caseRobot.getLigne() + 1, this.caseRobot.getColonne(), this.caseRobot.getTerrain());
            Case tempCase2 = new Case(this.caseRobot.getLigne() - 1, this.caseRobot.getColonne(), this.caseRobot.getTerrain());
            Case tempCase3 = new Case(this.caseRobot.getLigne(), this.caseRobot.getColonne() + 1, this.caseRobot.getTerrain());
            Case tempCase4 = new Case(this.caseRobot.getLigne(), this.caseRobot.getColonne() - 1, this.caseRobot.getTerrain());

            if (tempCase1.equalsTerrain("EAU") || tempCase2.equalsTerrain("EAU") || tempCase3.equalsTerrain("EAU") || tempCase4.equalsTerrain("EAU")) {
                // On est à côté de l'eau on peut remplir
                this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
            }

        }
        else {
            System.out.println("Le robot ne peut pas remplir son réservoir !");
        }



    } */
}