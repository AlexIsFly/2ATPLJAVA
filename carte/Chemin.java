package carte;

import carte.Case;
import enumdata.NatureTerrain;
import robot.Robots;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class Chemin {
    private int[] coordCaseDepart;
    private int[] coordCaseArrivee;
    private LinkedList<LinkedList<LinkedList<int[]>>> graphe;

    private int[][][] tab_poids;
    private int[][][] tab_antecedents;
    /*
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
    } */

    public Chemin(int[] coordCaseDepart, int[] coordCaseArrivee, LinkedList<LinkedList<LinkedList<int[]>>> graphe) {
        this.coordCaseDepart = coordCaseDepart;
        this.coordCaseArrivee = coordCaseArrivee;
        this.graphe = graphe;
        int nbLignes = graphe.size();
        int nbColonnes = graphe.get(0).size();
        this.tab_poids = new int[nbLignes][nbColonnes][2];
        this.tab_antecedents = new int[nbLignes][nbColonnes][2];
    }

    public Chemin() {
        int[] temp = new int[2];
        this.coordCaseDepart = temp;
        this.coordCaseArrivee = temp;
        this.graphe = new LinkedList<LinkedList<LinkedList<int[]>>>();
        this.tab_poids = new int[0][0][2];
        this.tab_antecedents = new int[0][0][2];
    }

    private void algorithme() {
        // On met la valeur du poids de la case de départ à 0
       // tab_poids[this.coordCaseDepart[0]][this.coordCaseDepart[1]] = 0;
    }

    public LinkedList<int[]> plusCourtChemin() {

        // Retourne le plus court chemin calculé par Djikstra

    }



}
