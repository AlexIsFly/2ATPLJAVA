package sim;

import enumdata.Direction;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class TestSimulateur {
    public static void main(String[] args) {
        Simulateur simulateur = new Simulateur(args);
        simulateur.addEvent(2, Direction.EST);
        simulateur.addEvent(3,5,7);
    }
}
