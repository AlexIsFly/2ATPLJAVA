package evenement;

import enumdata.Action;
import enumdata.Direction;

public class EvenementRemplir extends Evenement {
	private Robots r ;
	private Case pos ;

	public EvenementRemplir( int date, Robots rob, Case c) {
		super(date);
		this.r = rob;
		this.pos = c;
	}

	public void execute() {
		super.execute();
		if (this.pos.getTerrain() == EAU) {
			this.r.remplirReservoir();
			System.out.println(" Le robot a remplis son réservoir d'eau");
		}
	}

}
