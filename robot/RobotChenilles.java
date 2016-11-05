package robot;

import carte.Case;

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
        super(caseRobot);
        this.vitesse = new Vitesse(60, 30, 0, 0, 60);
        this.reservoir = new Reservoir(2000, 300, 100, 8);
    }

    // Constructeur par défaut
    public RobotChenilles() {
        super();
        this.vitesse = new Vitesse(60, 30, 0, 0, 60);
        this.reservoir = new Reservoir(2000, 300, 100, 8);
    }

}
