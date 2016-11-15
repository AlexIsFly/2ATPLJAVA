package evenement;

import carte.Carte;
import robot.Robots;

public class EvenementRemplir extends Evenement {
	private Robots r ;


	/**
	 * Constructor for the event EvenementRemplir
	 * @param date Date the event has to execute
	 * @param rob Robot to perform the event
	 */
	public EvenementRemplir( int date, Robots rob) {
		super(date);
		this.r = rob;
	}

	@Override
	public void execute(Carte carte) {
		r.remplirReservoir(carte);
        r.setIdle(true);
    }

	/**
	 * @return Formatted string description of this event
	 */
	@Override
    public String toString() {
        return "EvenementRemplir{" +
				", " +
                super.toString() +
				r.toString() +
                '}';
    }
}
