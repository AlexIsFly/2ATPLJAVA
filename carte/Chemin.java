package carte;

import carte.Case;
import enumdata.NatureTerrain;
import robot.Robots;
import sun.awt.image.ImageWatched;

import java.util.Iterator;
import java.util.LinkedList;

public class Chemin {
    private int[] coordCaseDepart;
    private int[] coordCaseArrivee;
    private LinkedList<LinkedList<LinkedList<int[]>>> graphe;
    private int nbLignes;
    private int nbColonnes;
    private int[][][] tab_poids;
    private int[][][] tab_antecedents;

    public Chemin(int[] coordCaseDepart, int[] coordCaseArrivee, LinkedList<LinkedList<LinkedList<int[]>>> graphe) {
        this.coordCaseDepart = coordCaseDepart;
        this.coordCaseArrivee = coordCaseArrivee;
        this.graphe = graphe;
        int nbLignes = graphe.size();
        int nbColonnes = graphe.get(0).size();
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tab_poids = new int[nbLignes][nbColonnes][2];
        this.tab_antecedents = new int[nbLignes][nbColonnes][2];
    }

    public Chemin() {
        int[] temp = new int[2];
        this.coordCaseDepart = temp;
        this.coordCaseArrivee = temp;
        this.graphe = new LinkedList<LinkedList<LinkedList<int[]>>>();
        this.nbLignes = 0;
        this.nbColonnes = 0;
        this.tab_poids = new int[0][0][2];
        this.tab_antecedents = new int[0][0][2];
    }

    private void algorithme() {
        // Initialisation
        // On met la valeur du poids de la case de départ à 0 et tous les autres très grandes
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if ((this.coordCaseDepart[0] == i) && (this.coordCaseDepart[1] == j)) {
                    this.tab_poids[i][j][2] = 0;
                }
                else {
                    this.tab_poids[i][j][2] = 100000000;
                }
            }
        }

        // On applique maintenant l'algorithme
        // On recherche le noeud de poids le + faible non marqué
        // Si ce noeud est la case d'arrivée on a trouvé le chemin !
        // Sinon on regarde les fils et on met à jour leur poids

        // caseCour contient les coordonnées de la case qui nous intéresse avec son poids
        int[] caseCour = new int[3];
        caseCour[0] = this.coordCaseDepart[0];
        caseCour[1] = this.coordCaseDepart[1];
        caseCour[2] = 100000000;

        while (caseCour[0] != this.coordCaseArrivee[0] && caseCour[1] != this.coordCaseArrivee[1]) {
            for (int i = 0; i < nbLignes; i++) {
                for (int j = 0; j < nbColonnes; j++) {
                    if (this.tab_poids[i][j][1] == 0 && caseCour[2] > this.tab_poids[i][j][0]) {
                        caseCour[2] = this.tab_poids[i][j][0];
                        caseCour[0] = i;
                        caseCour[1] = j;
                    }
                }
            }
            // On a trouvé la case non marquée avec le poids minimal (caseCour), on regarde ses fils et on met à jour le poids
            Iterator itr = this.graphe.get(caseCour[0]).get(caseCour[1]).iterator();
            while (itr.hasNext()) {
                // On regarde chaque fils
                int[] fils = new int[3];
                fils = (int[])itr.next();
                // On regarde si le fils n'est pas marqué
                if (tab_antecedents[fils[0]][fils[1]][1] == 0) {

                }
                itr.next();
            }
        }

    }

    public LinkedList<int[]> plusCourtChemin() {
        // Retourne le plus court chemin calculé par Djikstra
        this.algorithme(); // Met à jour le tableau antécédents
        LinkedList<int[]> chemin = new LinkedList<int[]>();
        int[] coordCourante = new int[2];
        int tempLigne;
        int tempColonne;
        coordCourante[0] = this.coordCaseDepart[0];
        coordCourante[1] = this.coordCaseDepart[1];
        chemin.add(coordCaseArrivee);
        // Tant que les coordonnées de la case de départ ne sont pas atteintes ou que le père de la case courante n'existe pas
        while ((this.coordCaseDepart[0] != coordCourante[0] && this.coordCaseDepart[1] != coordCourante[1])) {
            if (tab_antecedents[coordCourante[0]][coordCourante[1]] == null) {
                System.out.println("Le chemin n'existe pas !");
                return null;
            }
            tempLigne = tab_antecedents[coordCourante[0]][coordCourante[1]][0];
            tempColonne = tab_antecedents[coordCourante[0]][coordCourante[1]][1];
            coordCourante[0] = tempLigne;
            coordCourante[1] = tempColonne;
            chemin.addFirst(coordCourante);
        }
        return chemin;
    }

    public int tempsDeplacement(LinkedList<int[]> chemin) {
        // Le temps de déplacement est le poids du sommet de départ dans le chemin
        return chemin.getFirst()[2];
    }

}
