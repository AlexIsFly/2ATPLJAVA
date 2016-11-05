package evenement;

import carte.Case;
import enumdata.Direction;

/**
 * Created by alexisgacel on 31/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class EvenementMoveCoord extends Evenement {
    private int lig;
    private int col;

    public EvenementMoveCoord(int date, int lig, int col) {
        super(date);
        this.lig = lig;
        this.col = col;
    }

    public void execute(){
        super.execute();
        System.out.println(" | MoveCoord : ("+this.col+","+this.lig+")");
    }
}
