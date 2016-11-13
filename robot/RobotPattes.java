package robot;

import carte.Carte;
import carte.Case;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Nicolas on 05/11/2016.
 */
public class RobotPattes extends Robots {

    private static LinkedList<LinkedList<LinkedList<int[]>>> graphe;;
    // La vitesse n'est jamais spécifiée pour robot à pattes
    // Je considère le volume d'eau infini en le mettant très grand
    public RobotPattes(Case caseRobot) {
        super(caseRobot, new Reservoir(1000000000, 0, 10, 1), new Vitesse(30, 30, 0, 10, 30));
    }

    // Constructeur par défaut
    public RobotPattes() {
        super();
        this.vitesse = new Vitesse(30, 30, 0, 10, 30);
        this.reservoir = new Reservoir(1000000000, 0, 10, 1);
        this.reservoir.setVolumeCourant(1000000000);
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphe() {
        return RobotPattes.graphe;
    }

    public static void creeGraphe(Carte carte) {
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
                    if (carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("TERRAIN_LIBRE")
                            || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("FORET")
                            || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("HABITAT")) {
                        voisinNord[2] = 10000 / 30;
                        graphe.get(i).get(j).add(voisinNord);
                    }
                    else if (carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("ROCHE")) {
                        voisinNord[2] = 10000 / 10;
                        graphe.get(i).get(j).add(voisinNord);
                    }

                }
                // Si on n'est pas sur le bord sud
                if (coord[0] != carte.getNbLignes()-1) {
                    voisinSud[0] = coord[0] + 1;
                    voisinSud[1] = coord[1];
                    if (carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("TERRAIN_LIBRE")
                            || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("FORET")
                            || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("HABITAT")) {
                        voisinSud[2] = 10000 / 30;
                        graphe.get(i).get(j).add(voisinSud);
                    }
                    else if (carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("ROCHE")) {
                        voisinSud[2] = 10000 / 10;
                        graphe.get(i).get(j).add(voisinSud);
                    }
                }
                // Si on n'est pas sur le bord droit
                if (coord[1] != carte.getNbColonnes()-1) {
                    voisinEst[0] = coord[0];
                    voisinEst[1] = coord[1] + 1;
                    if (carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("TERRAIN_LIBRE")
                            || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("FORET")
                            || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("HABITAT")) {
                        voisinEst[2] = 10000 / 30;
                        graphe.get(i).get(j).add(voisinEst);
                    }
                    else if (carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("ROCHE")) {
                        voisinEst[2] = 10000 / 10;
                        graphe.get(i).get(j).add(voisinEst);
                    }
                }
                // Si on n'est pas sur le bord gauche
                if (coord[1] != 0) {
                    voisinOuest[0] = coord[0];
                    voisinOuest[1] = coord[1] - 1;
                    if (carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("TERRAIN_LIBRE")
                            || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("FORET")
                            || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("HABITAT")) {
                        voisinOuest[2] = 10000 / 30;
                        graphe.get(i).get(j).add(voisinOuest);
                    }
                    else if (carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("ROCHE")) {
                        voisinOuest[2] = 10000 / 10;
                        graphe.get(i).get(j).add(voisinOuest);
                    }
                }
            }
        }
    }

    /*
    public ArrayList<int[]> getCoordvoisins(int []coord, Carte carte) {

        // Pattes peut se déplacer sur tous sauf EAU

        ArrayList<int[]> al = new ArrayList<int[]>();

        int[] voisinNord = new int[3];
        int[] voisinSud = new int[3];
        int[] voisinOuest = new int[3];
        int[] voisinEst = new int[3];

        // Si on n'est pas sur le bord haut
        if (coord[0] != 0) {
            voisinNord[0] = coord[0] - 1;
            voisinNord[1] = coord[1];
            if (carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("FORET")
                    || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("HABITAT")
                    || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("ROCHE")) {
                al.add(voisinNord);
            }
        }
        // Si on n'est pas sur le bord sud
        if (coord[0] != carte.getNbLignes()-1) {
            voisinSud[0] = coord[0] + 1;
            voisinSud[1] = coord[1];
            if (carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("FORET")
                    || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("HABITAT")
                    || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("ROCHE")) {
                al.add(voisinSud);
            }
        }
        // Si on n'est pas sur le bord droit
        if (coord[1] != carte.getNbColonnes()-1) {
            voisinEst[0] = coord[0];
            voisinEst[1] = coord[1] + 1;
            if (carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("FORET")
                    || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("HABITAT")
                    || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("ROCHE")) {
                al.add(voisinEst);
            }
        }
        // Si on n'est pas sur le bord gauche
        if (coord[1] != 0) {
            voisinOuest[0] = coord[0];
            voisinOuest[1] = coord[1] - 1;
            if (carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("FORET")
                    || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("HABITAT")
                    || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("ROCHE")) {
                al.add(voisinOuest);
            }
        }
        return al;
    }
    */
}
