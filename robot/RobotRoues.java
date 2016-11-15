package robot;

import carte.Carte;
import carte.Case;
import enumdata.Direction;
import exceptions.CaseOutOfMapException;

import java.util.LinkedList;

import static java.lang.Math.min;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */
public class RobotRoues extends Robots {

    private static LinkedList<LinkedList<LinkedList<int[]>>> graphe;

    /**
     * Constructor of RobotRoues when a different speed is specified
     * @param caseRobot the starting Case
     * @param vitesseTerrainLibre the special speed
     */
    public RobotRoues (Case caseRobot, Reservoir reservoir, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(5000, 5, 5000, 3), new Vitesse(vitesseTerrainLibre, 0, 0, vitesseTerrainLibre), "ROUES");
    }

    /**
     * Constructor of RobotsRoues whith default speed
     * @param caseRobot the starting Case
     */
    public RobotRoues(Case caseRobot) {
        super(caseRobot, new Reservoir(5000, 5, 5000, 3), new Vitesse(80, 0, 0, 80), "ROUES");
    }

    // Constructor by default
    public RobotRoues() {
        super();
    }

    @Override
    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphe() {
        return graphe;
    }

    /**
     * Command the robot to unload the water
     * The robot must be on a Case with Incendie
     */
    @Override
    public void deverserEau() {
        int Qte;
        if (caseRobot.isIncendie()) {
            Qte = min(this.reservoir.getVolumeCourant() , caseRobot.getQteEau());
            caseRobot.setQteEau(caseRobot.getQteEau()-Qte);
            this.getReservoir().setVolumeCourant(0);
            System.out.println("Intervention réussie sur la " + caseRobot.toString());

        }
        else
        {
            System.out.println("Pas de feu sur la " + caseRobot.toString() + " !");
        }
    }


    /**
     * Command the robot to load the water
     * This robot must be next to the water
     * @param carte
     */
    @Override
    public void remplirReservoir(Carte carte) {
        for (Direction dir : Direction.values()) {
            try {
                if (carte.getVoisin(this.caseRobot, dir).equalsTerrain("EAU")) {
                    this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
                }
            } catch (CaseOutOfMapException e) {
                System.out.println("Warning : Verification effectuée en dehors en dehors de la carte !");
            }
        }
    }

    /**
     * Create the graph with correct weight for this robot
     * It is necessary to call this method before finding a shortest path
     * @param carte
     */
    @Override
    public void creeGraphe(Carte carte) {
        graphe = new LinkedList<LinkedList<LinkedList<int[]>>>();

        //Initialisation du tableau 2D
        for (int i = 0; i < carte.getNbLignes(); i++) {
            graphe.add(new LinkedList<LinkedList<int[]>>());
            for (int j = 0; j < carte.getNbLignes(); j++)
                graphe.get(i).add(new LinkedList<int[]>());
        }
        // On remplit le tableau 2D des coordonnées des voisins
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                // On ajoute les coordonnéees des voisins avec leur poids
                int[] coord = new int[2];
                coord[0] = i;
                coord[1] = j;
                int[] voisinNord = new int[3];
                int[] voisinSud = new int[3];
                int[] voisinOuest = new int[3];
                int[] voisinEst = new int[3];

                if (carte.getCase(coord[0], coord[1]).equalsTerrain("TERRAIN_LIBRE")
                        || carte.getCase(coord[0], coord[1]).equalsTerrain("HABITAT")) {
                    // Si on n'est pas sur le bord haut
                    if (coord[0] != 0) {
                        voisinNord[0] = coord[0] - 1;
                        voisinNord[1] = coord[1];
                        voisinNord[2] = 10000 / (int)this.vitesse.getVitesseTerrainLibre();
                        if (carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("TERRAIN_LIBRE")
                                || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("HABITAT")) {
                            graphe.get(i).get(j).add(voisinNord);
                        }
                    }
                    // Si on n'est pas sur le bord sud
                    if (coord[0] != carte.getNbLignes() - 1) {
                        voisinSud[0] = coord[0] + 1;
                        voisinSud[1] = coord[1];
                        voisinSud[2] = 10000 / (int)this.vitesse.getVitesseTerrainLibre();
                        if (carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("TERRAIN_LIBRE")
                                || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("HABITAT")) {
                            graphe.get(i).get(j).add(voisinSud);
                        }
                    }
                    // Si on n'est pas sur le bord droit
                    if (coord[1] != carte.getNbColonnes() - 1) {
                        voisinEst[0] = coord[0];
                        voisinEst[1] = coord[1] + 1;
                        voisinEst[2] = 10000 / (int)this.vitesse.getVitesseTerrainLibre();
                        if (carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("TERRAIN_LIBRE")
                                || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("HABITAT")) {
                            graphe.get(i).get(j).add(voisinEst);
                        }
                    }
                    // Si on n'est pas sur le bord gauche
                    if (coord[1] != 0) {
                        voisinOuest[0] = coord[0];
                        voisinOuest[1] = coord[1] - 1;
                        voisinOuest[2] = 10000 / (int)this.vitesse.getVitesseTerrainLibre();
                        if (carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("TERRAIN_LIBRE")
                                || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("HABITAT")) {
                            graphe.get(i).get(j).add(voisinOuest);
                        }
                    }
                }
            }
        }
    }

}
