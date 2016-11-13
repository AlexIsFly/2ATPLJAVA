package evenement;


import carte.Carte;
import robot.Robots;

/**
 * Created by alexisgacel on 31/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class EvenementMoveCoord extends Evenement {
    private int lig;
    private int col;
    private Robots rbt;

    public EvenementMoveCoord(int date, Robots rbt ,int lig, int col) {
        super(date);
        this.rbt = rbt;
        this.lig = lig;
        this.col = col;
    }

    public void execute(Carte carte){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "EvenementMoveCoord{" +
                super.toString() +
                ", lig=" + lig +
                ", col=" + col +
                '}';
    }
}
