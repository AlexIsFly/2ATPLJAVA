package evenement;

import enumdata.Action;
import enumdata.Direction;

public class EvenementRemplir extends Evenement {
	private Robots r ;

	public EvenementRemplir( int date, Robots rob, Case c) {
		super(date);
		this.r = rob;
	}

	public void execute() {
		Case pos;
		pos = this.r.getPosition();
		super.execute();
		if (pos.getTerrain().equalsTerrain(EAU)) {
			this.r.remplirReservoir();
			System.out.println(" Le robot a remplis son réservoir d'eau");
		}
	}

}
