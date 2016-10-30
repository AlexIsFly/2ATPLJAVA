package evenement;

import enumdata.Action;
import enumdata.Direction;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class EvenementMoveDir extends Evenement {
    private Direction dir;

    public EvenementMoveDir(int date, Direction dir) {
        super(date);
        this.dir = dir;
    }

    public void execute(){
        super.execute();
        System.out.printf(" | MoveDirection : "+dir);

        switch (dir) {
            case NORD:

                break;
            case SUD:
                break;
            case EST:
                break;
            case OUEST:
                break;
        }
    }
}
