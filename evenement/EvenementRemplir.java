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
		if (pos.getTerrain().equalsTerrain(EAU)) {  // En réalité ca marche que pour les drones car les robots à chenilles et à roues doivent être à coté
			this.r.remplirReservoir();
			System.out.println(" Le robot a remplis son réservoir d'eau");
		}
	}

}
