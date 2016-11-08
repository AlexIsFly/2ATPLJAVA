package carte;
import carte.Case;
import enumdata.NatureTerrain;
import robot.Robots;
public class Chemin2 {
    private Robots r;
    private int[][] tab_poids;
    public Chemin(Robots rob) {
        int i,j;
        this.r=rob;
        i=rob.getGraphe().getLigne();
        j=rob.getGraphe().getColonne();
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
        this.getTab()[this.getRobots().getPosition().getLigne()][this.getRobots().getPosition().getColonne()]=0;
    }
    public void djikstra(int i,int j) {  // i et j sont les coordonnées de la case d'arrivé
        int posi, posy;
        posi=this.getRobots().getPosition().getLigne();
        posy=this.getRobots().getPosition().getColonne();
        if( i < nbligne && j< nbcolonne) {
            throw new IllegalArgumentException("La destination est en dehors de la carte");
        }
        else
            while (this.getTab()[i][j] != 10000) {
            }
    }