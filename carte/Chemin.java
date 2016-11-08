
import enumdata.Directory;
import enumdata.NatureTerrain;

public class chemin {
	private Robots r;
	private int[][] tab_poids;


	public chemin(Robots rob) {
		int i,j;
		this.r=rob;
	/*      i=rob.getGraphe().getLigne();
		j=rob.getGraphe().getColonne(); */
		this.tab_poids = new int[i][j];
	}

	public int[][] getTab() {
		return this.tab_poids;
	}
		
	public Robots getRobots() {
		return this.r;
	}

	public void init_djikstra() {
		Case pos;
		int i,j;
		pos=this.getRobots().getPosition()
		i=this.getTab().length;
		j=this.getTab()[0].length;
		for( int k=0; k<i; k++) {
			for( int l=0; l<j; l++){
				this.getTab()[k][l]=10000; // on fixe tout les poids des sommets à l'infini
			}
		}
		
}
