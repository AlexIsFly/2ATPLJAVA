package evenement;

import enumdata.Action;
import enumdata.Direction;

public class EvenementIntervention extends Evenement {
	private Robots r ;

	public EvenementIntervention(int date, Robots rob) {
		super(date);
		this.r = rob;
	}
	
	public void execute() {
		int Qte;
		Case pos;
		pos=this.r.getPosition();
		super.execute();
		if ( pos.isIncendie()) {   
			Qte = min(this.r.getReservoir(),this.pos.getQteEau());  // la quantité que l'on va verser est le min entre ce que l'on peut verser et ce que l'on doit verser
			this.r.deverserEau(Qte);
			this.pos.setQteEau(this.pos.getQteEau()-Qte);
			System.out.println(" Le robot a déversé "+ Qte +" volume d'eau ");
		}
	}


}
