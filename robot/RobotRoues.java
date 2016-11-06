package robot;

import carte.Carte;
import carte.Case;
import enumdata.Direction;

/**
 * Created by Nicolas on 05/11/2016.
 */
public class RobotRoues extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public RobotRoues (Case caseRobot, Reservoir reservoir, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(5000, 600, 100, 5), new Vitesse(vitesseTerrainLibre, 0, 0, 0, vitesseTerrainLibre));
    }

    // Quand la vitesse n'est pas spécifiée
    public RobotRoues(Case caseRobot) {
        super(caseRobot, new Reservoir(5000, 600, 100, 5), new Vitesse(80, 0, 0, 0, 80));
    }

    // Constructeur par défaut
    public RobotRoues() {
        super();
        this.vitesse = new Vitesse(80, 0, 0, 0, 80);
        this.reservoir = new Reservoir(5000, 600, 100, 5);
    }

    public void remplirReservoir(Carte carte) {
        if (carte.getVoisin(this.caseRobot, Direction.NORD).equalsTerrain("EAU")
                || carte.getVoisin(this.caseRobot, Direction.SUD).equalsTerrain("EAU")
                || carte.getVoisin(this.caseRobot, Direction.EST).equalsTerrain("EAU")
                || carte.getVoisin(this.caseRobot, Direction.OUEST).equalsTerrain("EAU")) {
            this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
        }
        else {
            System.out.println("Le robot ne peut pas remplir son réservoir !");
        }
    }
}
