package sim;

import enumdata.Direction;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class TestSimulateur {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(args);

        simulateur.addEventDirection(2, simulateur.getaRobot(0), Direction.EST);
        simulateur.addEventDirection(1, simulateur.getaRobot(1), Direction.EST);
        simulateur.addEventArrive(2,simulateur.getaRobot(1),3,3);
        simulateur.addEventCoord(2,3,5);
        simulateur.addEventIntervention(2,simulateur.getaRobot(0));
        simulateur.addEventRemplir(2,simulateur.getaRobot(0));
    }
}
