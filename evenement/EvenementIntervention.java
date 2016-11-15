package evenement;

import carte.Carte;
import robot.Robots;


public class EvenementIntervention extends Evenement {
	private Robots r ;

    /**
     * Constructor for EvenementIntervention
     * @param date Date the event has to execute
     * @param rob Robot to perform the event
     */
    public EvenementIntervention(int date, Robots rob) {
		super(date);
		this.r = rob;
	}

    @Override
    public void execute(Carte carte) {
        r.deverserEau();
        r.setIdle(true);
        r.getCaseRobot().setIncendieAffected(false);
	}

    /**
     * @return Formatted string description of this event
     */
    @Override
	public String toString() {
		return "EvenementIntervention{" +
				super.toString() +
				", Intervention Incendie" +
				'}';
	}
}
