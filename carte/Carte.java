package carte;

import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 21/10/2016.
 */
public class Carte {
    private Case[][] map;
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;

    public Carte(int nbLignes, int nbColonnes, int tailleCases) {
        this.tailleCases = tailleCases;
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.map = new Case[nbLignes][nbColonnes];
    }

    public void fillCase(int ligne, int colonne, NatureTerrain terrain) {
        this.map[ligne][colonne] = new Case(ligne, colonne, terrain);
    }

    public void printMap() {
        for (int lig = 0; lig < this.nbLignes; lig++) {
            for (int col = 0; col < this.nbColonnes; col++) {
                System.out.print(map[lig][col].getTerrain() + "  ");
            }
            System.out.println(lig);
        }
    }
}