package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class RobotRoues extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public RobotRoues (Case caseRobot, int capaciteMaxReservoir, double vitesseTerrainLibre) {
        super(caseRobot, capaciteMaxReservoir, new Vitesse(vitesseTerrainLibre, 0, 0, 0, vitesseTerrainLibre));
    }

    // Quand la vitesse n'est pas spécifiée
    public RobotRoues(Case caseRobot, int capaciteMaxReservoir) {
        super(caseRobot, capaciteMaxReservoir);
        this.vitesse = new Vitesse(80, 0, 0, 0, 80);
    }

    // Constructeur par défaut
    public RobotRoues() {
        super();
        this.vitesse = new Vitesse(80, 0, 0, 0, 80);
    }
}
