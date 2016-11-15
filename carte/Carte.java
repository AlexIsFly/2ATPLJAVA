package carte;

import enumdata.Direction;
import enumdata.NatureTerrain;
import exceptions.CaseOutOfMapException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */

public class Carte {
    private Case[][] map;
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;

    /**
     * Constructor
     * @param nbLignes
     * @param nbColonnes
     * @param tailleCases (not used here)
     */
    public Carte(int nbLignes, int nbColonnes, int tailleCases) {
        this.tailleCases = tailleCases;
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.map = new Case[nbLignes][nbColonnes];
    }

    //default constructor
    public Carte() {
        this.map = null;
        this.tailleCases = 0;
        this.nbLignes = 0;
        this.nbColonnes = 0;
    }

    //getters
    public int getTailleCases() {
        return tailleCases;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Case[][] getMap() {
        return map;
    }

    /**
     * @param lig
     * @param col
     * @return the case corresponding to the given coordinates
     */
    public Case getCase(int lig, int col) {
        return this.map[lig][col];
    }

    public Case getRandomWaterCase() {
        int ligrnd, colrnd;
        do {
            ligrnd = ThreadLocalRandom.current().nextInt(0, nbLignes);
            colrnd = ThreadLocalRandom.current().nextInt(0, nbColonnes);
        } while (!getCase(ligrnd,colrnd).equalsTerrain("EAU"));
        return getCase(ligrnd, colrnd);
    }

    public Case getRandomCoastCase() {
        int ligrnd, colrnd;
        Case casernd;
        boolean found = false;
        do {
            ligrnd = ThreadLocalRandom.current().nextInt(0, nbLignes);
            colrnd = ThreadLocalRandom.current().nextInt(0, nbColonnes);
            casernd = getCase(ligrnd,colrnd);
            if (!casernd.equalsTerrain("EAU")) {
                for (Direction dir : Direction.values()) {
                    try {
                        if (getVoisin(casernd,dir).equalsTerrain("EAU")) {
                            found = true;
                        }
                    } catch (CaseOutOfMapException e) {
                        System.out.println("Case RND en dehors de la carte !");
                    }
                }
            }
        } while (!found);
        return casernd;
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

    /**
     * @param depart
     * @param dir
     * @return The case next to depart form the direction dir
     * @throws CaseOutOfMapException
     */
    public Case getVoisin(Case depart, Direction dir) throws CaseOutOfMapException {
        switch (dir){
            case NORD:
                if (voisinExiste(depart,Direction.NORD)) {
                    return map[depart.getLigne() - 1][depart.getColonne()];
                }
                else throw new CaseOutOfMapException("");
            case SUD:
                if (voisinExiste(depart,Direction.SUD)) {
                    return map[depart.getLigne() + 1][depart.getColonne()];
                }
                else throw new CaseOutOfMapException("Case Interdite");
            case EST:
                if (voisinExiste(depart,Direction.EST)) {
                    return map[depart.getLigne()][depart.getColonne() + 1];
                }
                else throw new CaseOutOfMapException("Case Interdite");
            case OUEST:
                if (voisinExiste(depart,Direction.OUEST)) {
                    return map[depart.getLigne()][depart.getColonne() - 1];
                }
                else throw new CaseOutOfMapException("Case Interdite");
        }
        return depart;
    }

    //these 2 methods are used in LecteurDonnées to create the map
    public void setIncendieCase(int lig, int col, int intensite) {
        this.map[lig][col].setIncendie(true);
        this.map[lig][col].setQteEau(intensite);
    }

    public void fillCase(int ligne, int colonne, NatureTerrain terrain) {
        this.map[ligne][colonne] = new Case(ligne, colonne, terrain);
    }

    //these 2 methods are only used for testing purposes
    private void printCaseTerrain(int lig, int col) {
        System.out.print(this.map[lig][col].getTerrain());
    }

    /**
     * Print an ASCII map to the standard output.
     */
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