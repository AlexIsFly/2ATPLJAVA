package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class RobotPattes extends Robots {

    // La vitesse n'est jamais spécifiée pour robot à pattes
    public RobotPattes(Case caseRobot, int capaciteMaxReservoir) {
        super(caseRobot, capaciteMaxReservoir);
        this.vitesse = new Vitesse(30, 30, 0, 10, 30);
    }

    // Constructeur par défaut
    public RobotPattes() {
        super();
        this.vitesse = new Vitesse(30, 30, 0, 10, 30);
    }

}
