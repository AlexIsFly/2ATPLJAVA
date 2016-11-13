package sim;

import enumdata.Direction;
import robot.Robots;

import java.util.Scanner;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class TestSimulateur {
    public static void main(String[] args) {
        Simulateur sim = new Simulateur(args);

        sim.demandeUtilisateur();

        //la methode getaRobot permet d'obtenir un robot dans la liste des robots
        //sim.addEventDirection(1,sim.getaRobot(0),Direction.NORD);
        //sim.addEventArrive(1,sim.getaRobot(0));
    }
}