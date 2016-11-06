package robot;

import carte.Carte;
import carte.Case;
import enumdata.Direction;

/**
 * Created by Nicolas on 05/11/2016.
 */
class RobotChenilles extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public RobotChenilles (Case caseRobot, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(2000, 300, 100, 8), new Vitesse(vitesseTerrainLibre, vitesseTerrainLibre/2, 0, 0, vitesseTerrainLibre));
    }

    // Quand la vitesse n'est pas spécifiée
    public RobotChenilles(Case caseRobot) {
        super(caseRobot, new Reservoir(2000, 300, 100, 8), new Vitesse(60, 30, 0, 0, 60));
    }

    // Constructeur par défaut
    public RobotChenilles() {
        super();
        this.vitesse = new Vitesse(60, 30, 0, 0, 60);
        this.reservoir = new Reservoir(2000, 300, 100, 8);
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
