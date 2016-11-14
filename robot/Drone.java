package robot;

import carte.Carte;
import carte.Case;
import java.util.LinkedList;

/**
 * Created by Nicolas on 05/11/2016.
 */
public class Drone extends Robots {

    private static LinkedList<LinkedList<LinkedList<int[]>>> graphe;
    // Quand la vitesse est spécifiée dans le fichier

    public Drone (Case caseRobot, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(100000, 15, 10000, 1), new Vitesse(vitesseTerrainLibre), "DRONE");
    }

    // Quand la vitesse n'est pas spécifiée
    public Drone(Case caseRobot) {
        super(caseRobot, new Reservoir(100000, 15, 10000, 1), new Vitesse(100), "DRONE");
    }

    // Constructeur par défaut
    public Drone() {
        super();
        this.vitesse = new Vitesse(100);
        this.reservoir = new Reservoir(100000, 15, 10000, 1);
    }

    public void remplirReservoir(Carte map) {
        if (this.caseRobot.equalsTerrain("EAU")) {
            this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
            System.out.println("Remplissage réussi !");
        }
        else {
            System.out.println("Le remplissage a échoué !");
        }
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphe() {
        return this.graphe;
    }

    public void creeGraphe(Carte carte) {
        graphe = new LinkedList<LinkedList<LinkedList<int[]>>>();

        //Initialisation du tableau 2D
        for(int i = 0; i < carte.getNbLignes(); i++) {
            graphe.add(new LinkedList<LinkedList<int[]>>());
            for(int j = 0; j < carte.getNbLignes(); j++)
                graphe.get(i).add(new LinkedList<int[]>());
        }
        // On remplit le tableau 2D des coordonnées des voisins
        for(int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                // On ajoute les coordonnéees des voisins avec leur poids
                int[] coord = new int[2];
                coord[0] = i;
                coord[1] = j;
                int[] voisinNord = new int[3];
                int[] voisinSud = new int[3];
                int[] voisinOuest = new int[3];
                int[] voisinEst = new int[3];

                // Si on n'est pas sur le bord haut
                if (coord[0] != 0) {
                    voisinNord[0] = coord[0] - 1;
                    voisinNord[1] = coord[1];
                    voisinNord[2] = 10000/(int)this.vitesse.getVitesseTerrainLibre();
                    graphe.get(i).get(j).add(voisinNord);
                }
                // Si on n'est pas sur le bord sud
                if (coord[0] != carte.getNbLignes()-1) {
                    voisinSud[0] = coord[0] + 1;
                    voisinSud[1] = coord[1];
                    voisinSud[2] = 10000/(int)this.vitesse.getVitesseTerrainLibre();
                    graphe.get(i).get(j).add(voisinSud);
                }
                // Si on n'est pas sur le bord droit
                if (coord[1] != carte.getNbColonnes()-1) {
                    voisinEst[0] = coord[0];
                    voisinEst[1] = coord[1] + 1;
                    voisinEst[2] = 10000/(int)this.vitesse.getVitesseTerrainLibre();
                    graphe.get(i).get(j).add(voisinEst);
                }
                // Si on n'est pas sur le bord gauche
                if (coord[1] != 0) {
                    voisinOuest[0] = coord[0];
                    voisinOuest[1] = coord[1] - 1;
                    voisinOuest[2] = 10000/(int)this.vitesse.getVitesseTerrainLibre();
                    graphe.get(i).get(j).add(voisinOuest);
                }
            }
        }
    }

}
