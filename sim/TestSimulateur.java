package sim;

import enumdata.Direction;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class TestSimulateur {
    public static void main(String[] args) {
        Simulateur sim = new Simulateur(args);

        //la methode getaRobot permet d'obtenir un robot dans la liste des robots
        sim.addEventRemplir(1,sim.getaRobot(0));
        sim.addEventDirection(2,sim.getaRobot(0),Direction.NORD);
        sim.addEventDirection(3,sim.getaRobot(0),Direction.NORD);
        sim.addEventDirection(4,sim.getaRobot(0),Direction.NORD);
        sim.addEventDirection(5,sim.getaRobot(0),Direction.NORD);
        sim.addEventIntervention(6,sim.getaRobot(0));

    }
}
