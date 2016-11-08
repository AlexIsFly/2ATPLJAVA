package evenement;

import carte.Carte;
import enumdata.Direction;
import exceptions.CaseOutOfMapException;
import robot.Robots;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class EvenementMoveDir extends Evenement {
    private Direction dir;
    private Robots rbt;

    public EvenementMoveDir(int date, Robots rbt, Direction dir) {
        super(date);
        this.dir = dir;
        this.rbt = rbt;
    }

    public void execute(Carte map){
        try {
            switch (dir) {
                case NORD:
                    this.rbt.setPosition(map.getVoisin(this.rbt.getPosition(), Direction.NORD));
                    break;
                case SUD:
                    this.rbt.setPosition(map.getVoisin(this.rbt.getPosition(), Direction.SUD));
                    break;
                case EST:
                    this.rbt.setPosition(map.getVoisin(this.rbt.getPosition(), Direction.EST));
                    break;
                case OUEST:
                    this.rbt.setPosition(map.getVoisin(this.rbt.getPosition(), Direction.OUEST));
                    break;
            }
            System.out.println(toString() + "FAIT !");
        } catch(CaseOutOfMapException e) {
            e.printStackTrace();
            System.out.println("Déplacement Interdit !!");
        }
    }

    @Override
    public String toString() {
        return "EvenementMoveDir{" +
                super.toString() +
                ", dir=" + dir +
                "} ";
    }
}
