package evenement;

import carte.Carte;
import robot.Robots;

public class EvenementTermine extends Evenement {
	private Robots r ;

	public EvenementTermine(int date, Robots rob) {
		super(date);
		this.r = rob;
	}
		
	public void execute(Carte carte) {
        System.out.println(toString() + " FAIT !");
        r.setIdle(true);
	}

    @Override
    public String toString() {
        return "EvenementTermine{" +
                super.toString() +
                ", " +
                r.toString() +
                "} ";
    }
}


