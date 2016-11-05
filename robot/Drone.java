package robot;

import carte.Case;

/**
 * Created by Nicolas on 05/11/2016.
 */
class Drone extends Robots {

    // Quand la vitesse est spécifiée dans le fichier
    public Drone (Case caseRobot, int capaciteMaxReservoir, double vitesseTerrainLibre) {
        super(caseRobot, capaciteMaxReservoir, new Vitesse(vitesseTerrainLibre));

        // Je suis obligé de faire cette chose laborieuse car l'appel à super doit être la première instruction..
        if (vitesseTerrainLibre > 150) {
            vitesseTerrainLibre = 150;
            this.vitesse.setVitesseTerrainLibre(150);
            this.vitesse.setVitesseEau(150);
            this.vitesse.setVitesseForet(150);
            this.vitesse.setVitesseRoche(150);
            this.vitesse.setVitesseHabitat(150);
        }
    }

    // Quand la vitesse n'est pas spécifiée
    public Drone(Case caseRobot, int capaciteMaxReservoir) {
        super(caseRobot, capaciteMaxReservoir);
        this.vitesse = new Vitesse(100);
    }

}
