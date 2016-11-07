package evenement;

import enumdata.Action;
import enumdata.Direction;

public class EvenementArrive extends Evenement {
	private Robots r ;
	private Case destination ;

	public EvenementArrive(int date, Robots rob, Case c) {
		super(date);
		this.r = rob;
		this.destination = c;
	}
		
	public void execute() {
		super.execute();
		if ( (this.r.getPosition()).getLigne() == this.destination.getLigne() && (this.r.getPosition()).getColonne() == this.destination.getColonne()) {
			System.out.println(" Le robot est arrivé à destination ");
			}
		}

	}


