import enumdata.Direction;
import evenement.Evenement;
import evenement.EvenementMoveCoord;
import evenement.EvenementMoveDir;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class TestEvenement {
    public static void main(String[] args) {
        Evenement event = new Evenement(1);
        event.execute();

        Evenement event2 = new EvenementMoveDir(1, Direction.EST);
        event2.execute();

        Evenement event3 = new EvenementMoveCoord(2,5,4);
        event3.execute();
    }
}
