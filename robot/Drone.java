package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class Drone extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public Drone (Case caseRobot, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(100000, 1800, 10000, 30), new Vitesse(vitesseTerrainLibre));
    }

    // Quand la vitesse n'est pas spécifiée
    public Drone(Case caseRobot) {
        super(caseRobot, new Reservoir(100000, 1800, 10000, 30), new Vitesse(100));
    }

    // Constructeur par défaut
    public Drone() {
        super();
        this.vitesse = new Vitesse(100);
        this.reservoir = new Reservoir(100000, 1800, 10000, 30);
    }

}
