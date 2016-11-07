package evenement;

import carte.Carte;
import robot.Robots;

public class EvenementArrive extends Evenement {
	private Robots r ;
    private int lig;
    private int col;

	public EvenementArrive(int date, Robots rob, int lig, int col) {
		super(date);
		this.r = rob;
        this.lig = lig;
        this.col = col;
	}
		
	public void execute(Carte carte) {
        System.out.println(toString());
        if (r.getPosition().getLigne() == lig && r.getPosition().getColonne() == col) {
			System.out.println(" Le robot est arrivé à destination ");
		}
	}

    @Override
    public String toString() {
        return "EvenementArrive{" +
                super.toString() +
                ", lig=" + this.lig +
                ", col=" + this.col +
                "} ";
    }
}


