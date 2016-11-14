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
		if (pos.isIncendie()) {
			Qte = min(this.r.getReservoir().getVolumeIntervention() , pos.getQteEau());
			this.r.deverserEau(Qte);
			pos.setQteEau(pos.getQteEau()-Qte);
			System.out.println("Sur la " + r.getPosition().toString());
			System.out.println("Le " + r.toString() + " a déversé "+ Qte +" volume d'eau ");
            System.out.println("L'incendie est d'intensité : "+ pos.getQteEau());
        }
		else
		{
            System.out.println("Pas de feu sur" + r.getPosition().toString() + " !");
        }
        r.getPosition().setIncendieAffected(false);
		r.setIdle(true);
	}

	@Override
	public String toString() {
		return "EvenementIntervention{" +
				super.toString() +
				", Intervention Incendie" +
				'}';
	}
}
