package evenement;

import carte.Carte;
import enumdata.Direction;
import exceptions.CaseOutOfMapException;
import robot.Robots;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */
public class EvenementMoveDir extends Evenement {
    private Direction dir;
    private Robots rbt;

    /**
     * Constructor for the EvenementMoveDir
     * @param date Date the event has to execute
     * @param rbt The robot to perform the event
     * @param dir The direction the robot has to move
     */
    public EvenementMoveDir(int date, Robots rbt, Direction dir) {
        super(date);
        this.dir = dir;
        this.rbt = rbt;
    }

    @Override
    public void execute(Carte map){
        try {
            switch (dir) {
                case NORD:
                    this.rbt.setCaseRobot(map.getVoisin(this.rbt.getCaseRobot(), Direction.NORD));
                    break;
                case SUD:
                    this.rbt.setCaseRobot(map.getVoisin(this.rbt.getCaseRobot(), Direction.SUD));
                    break;
                case EST:
                    this.rbt.setCaseRobot(map.getVoisin(this.rbt.getCaseRobot(), Direction.EST));
                    break;
                case OUEST:
                    this.rbt.setCaseRobot(map.getVoisin(this.rbt.getCaseRobot(), Direction.OUEST));
                    break;
            }
            System.out.println(toString() + "FAIT !");
        } catch(CaseOutOfMapException e) {
            e.printStackTrace();
            System.out.println("Déplacement Interdit !!");
        }
    }

    /**
     * @return Formatted string description of this event
     */
    @Override
    public String toString() {
        return "EvenementMoveDir{" +
                super.toString() +
                ", dir=" + dir +
                "} ";
    }
}
