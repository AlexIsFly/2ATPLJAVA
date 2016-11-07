package evenement;

import carte.Carte;
import carte.Case;
import enumdata.Direction;
import robot.Robots;

import static java.lang.Math.min;

public class EvenementIntervention extends Evenement {
	private Robots r ;

	public EvenementIntervention(int date, Robots rob) {
		super(date);
		this.r = rob;
	}
	
	public void execute(Carte carte) {
		int Qte;
		Case pos = this.r.getPosition();
		if ( (carte.getVoisin(this.r.getPosition(), Direction.NORD).isIncendie()
				|| carte.getVoisin(this.r.getPosition(), Direction.SUD).isIncendie()
				|| carte.getVoisin(this.r.getPosition(), Direction.EST).isIncendie()
				|| carte.getVoisin(this.r.getPosition(), Direction.OUEST).isIncendie())) {
			Qte = min(this.r.getReservoir().getVolumeCourant() , pos.getQteEau());  // la quantité que l'on va verser est le min entre ce que l'on peut verser et ce que l'on doit verser
			this.r.deverserEau(Qte);
			pos.setQteEau(pos.getQteEau()-Qte);
			System.out.println(" Le robot a déversé "+ Qte +" volume d'eau ");
		}
		else
		{
            System.out.println("Pas de feu adjacent à " + this.r.getPosition().toString());
        }
	}

	@Override
	public String toString() {
		return "EvenementIntervention{" +
				super.toString() +
				", Intervention Incendie" +
				'}';
	}
}
