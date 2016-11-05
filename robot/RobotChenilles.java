package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class RobotChenilles extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public RobotChenilles (Case caseRobot, int capaciteMaxReservoir, double vitesseTerrainLibre) {
        super(caseRobot, capaciteMaxReservoir, new Vitesse(vitesseTerrainLibre, vitesseTerrainLibre/2, 0, 0, vitesseTerrainLibre));

        // Je suis obligé de faire cette chose laborieuse car l'appel à super doit être la première instruction..
        if (vitesseTerrainLibre > 80) {
            vitesseTerrainLibre = 80;
            this.vitesse.setVitesseTerrainLibre(80);
            this.vitesse.setVitesseEau(0);
            this.vitesse.setVitesseForet(40);
            this.vitesse.setVitesseRoche(0);
            this.vitesse.setVitesseHabitat(80);
        }
    }

    // Quand la vitesse n'est pas spécifiée
    public RobotChenilles(Case caseRobot, int capaciteMaxReservoir) {
        super(caseRobot, capaciteMaxReservoir);
        this.vitesse = new Vitesse(60, 30, 0, 0, 60);
    }

    // Constructeur par défaut
    public RobotChenilles() {
        super();
        this.vitesse = new Vitesse(60, 30, 0, 0, 60);
    }

}
