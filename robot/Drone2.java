package robot;

import carte.Case;

/**
 * Created by alexisgacel on 05/11/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Drone2 extends Robots {
    //vitesse spécifiée dans le fichier
    public Drone2 (Case caseRobot, double vitesseTerrainLibre) {
        super(caseRobot, 10000, new Vitesse(vitesseTerrainLibre));
    }

    //vitesse non spécifiée
    public Drone2 (Case caseRobot){
            super(caseRobot, 10000, new Vitesse(100));
        }
    }
