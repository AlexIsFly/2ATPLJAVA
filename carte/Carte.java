package carte;

import enumdata.Direction;
import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 21/10/2016.
 * For Project Java ISSC - IMAG 2016
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

    public int getTailleCases() {
        return tailleCases;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Case getCase(int lig, int col) {
        return map[lig][col];
    }

    private boolean voisinExiste(Case depart, Direction dir) {
        boolean existe = true;
        switch (dir){
            case NORD:
                if (depart.getLigne() == 0) {
                    existe = false;
                }
                break;
            case SUD:
                if (depart.getLigne() == this.nbLignes-1) {
                    existe = false;
                }
                break;
            case EST:
                if (depart.getColonne() == this.nbColonnes-1) {
                    existe = false;
                }
                break;
            case OUEST:
                if (depart.getColonne() == 0) {
                    existe = false;
                }
                break;
        }
        return existe;
    }

    public Case getVoisin(Case depart, Direction dir) {
        switch (dir){
            case NORD:
                if (voisinExiste(depart,Direction.NORD)) {
                    return map[depart.getLigne() + 1][depart.getColonne()];
                }
                break;
            case SUD:
                if (voisinExiste(depart,Direction.SUD)) {
                    return map[depart.getLigne() - 1][depart.getColonne()];
                }
                break;
            case EST:
                if (voisinExiste(depart,Direction.EST)) {
                    return map[depart.getLigne()][depart.getColonne() + 1];
                }
                break;
            case OUEST:
                if (voisinExiste(depart,Direction.OUEST)) {
                    return map[depart.getLigne()][depart.getColonne() - 1];
                }
                break;
        }
        return depart;
    }

    public void fillCase(int ligne, int colonne, NatureTerrain terrain) {
        this.map[ligne][colonne] = new Case(ligne, colonne, terrain);
    }

    private void printCaseTerrain(int lig, int col) {
        System.out.print(this.map[lig][col].getTerrain());
    }

    public void printMap() {
        for (int lig = 0; lig < this.nbLignes; lig++) {
            for (int col = 0; col < this.nbColonnes; col++) {
                printCaseTerrain(lig,col);
                System.out.print("  ");
            }
            System.out.println(lig);
        }
    }
}