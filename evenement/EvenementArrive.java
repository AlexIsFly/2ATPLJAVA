package evenement;

import carte.Carte;
import robot.Robots;

public class EvenementArrive extends Evenement {
	private Robots r ;

	public EvenementArrive(int date, Robots rob) {
		super(date);
		this.r = rob;
	}
		
	public void execute(Carte carte) {
        System.out.println(toString() + " FAIT !");
        r.setIdle(true);
	}

    @Override
    public String toString() {
        return "EvenementArrive{" +
                super.toString() +
                ", " +
                r.toString() +
                "} ";
    }
}


