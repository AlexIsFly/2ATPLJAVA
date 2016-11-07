package evenement;

import enumdata.Action;
import enumdata.Direction;

public class EvenementIntervention extends Evenement {
	private Robots r ;
	private Case pos ;

	public EvenementIntervention(int date, Robots rob, Case c) {
		super(date);
		this.r = rob;
		this.pos = c;
	}
	
	public void execute() {
		int Qte;
		super.execute();
		if ( this.pos.incendie == true ) {
			Qte = min(this.r.getReservoir(),this.pos.getQteEau());  // la quantité que l'on va verser est le min entre ce que l'on peut verser et ce que l'on va verser
			this.r.deverserEau(this.pos.getQteEau());
			this.pos.setQteEau(this.pos.getQteEau()-Qte);
			System.out.println(" Le robot a déversé "+ Qte +" volume d'eau ");
		}
	}


}
