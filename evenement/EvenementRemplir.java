package evenement;

import carte.Carte;
import robot.Robots;

public class EvenementRemplir extends Evenement {
	private Robots r ;

	public EvenementRemplir( int date, Robots rob) {
		super(date);
		this.r = rob;
	}

	public void execute(Carte carte) {
        r.remplirReservoir(carte);
	}

    @Override
    public String toString() {
        return "EvenementRemplir{" +
				", " +
                super.toString() +
				r.toString() +
                '}';
    }
}
