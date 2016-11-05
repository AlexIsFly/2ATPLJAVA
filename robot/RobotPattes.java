package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class RobotPattes extends Robots {
    // La vitesse n'est jamais spécifiée pour robot à pattes
    // Je considère le volume d'eau infini en le mettant très grand
    public RobotPattes(Case caseRobot) {
        super(caseRobot);
        this.vitesse = new Vitesse(30, 30, 0, 10, 30);
        this.reservoir = new Reservoir(1000000000, 0, 10, 1);
    }

    // Constructeur par défaut
    public RobotPattes() {
        super();
        this.vitesse = new Vitesse(30, 30, 0, 10, 30);
        this.reservoir = new Reservoir(1000000000, 0, 10, 1);
        this.reservoir.setVolumeCourant(1000000000);
    }
}
