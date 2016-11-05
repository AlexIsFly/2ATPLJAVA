package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class RobotRoues extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public RobotRoues (Case caseRobot, Reservoir reservoir, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(5000, 600, 100, 5), new Vitesse(vitesseTerrainLibre, 0, 0, 0, vitesseTerrainLibre));
    }

    // Quand la vitesse n'est pas spécifiée
    public RobotRoues(Case caseRobot, int capaciteMaxReservoir) {
        super(caseRobot);
        this.vitesse = new Vitesse(80, 0, 0, 0, 80);
        this.reservoir = new Reservoir(5000, 600, 100, 5);
    }

    // Constructeur par défaut
    public RobotRoues() {
        super();
        this.vitesse = new Vitesse(80, 0, 0, 0, 80);
        this.reservoir = new Reservoir(5000, 600, 100, 5);
    }
}
