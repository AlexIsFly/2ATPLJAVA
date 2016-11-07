package evenement;


import carte.Carte;

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
