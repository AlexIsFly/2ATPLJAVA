package robot;

import carte.Carte;
import carte.Case;
import enumdata.Direction;
import exceptions.CaseOutOfMapException;

import java.util.LinkedList;

/**
 * Created by Nicolas on 05/11/2016.
 */
public class RobotRoues extends Robots {

    private static LinkedList<LinkedList<LinkedList<int[]>>> graphe;


    // Quand la vitesse est spécifiée dans le fichier
    public RobotRoues (Case caseRobot, Reservoir reservoir, double vitesseTerrainLibre) {
        super(caseRobot, new Reservoir(5000, 5, 2500, 1), new Vitesse(vitesseTerrainLibre, 0, 0, 0, vitesseTerrainLibre), "ROUES");
    }

    // Quand la vitesse n'est pas spécifiée
    public RobotRoues(Case caseRobot) {
        super(caseRobot, new Reservoir(5000, 5, 2500, 1), new Vitesse(80, 0, 0, 0, 80), "ROUES");
    }

    // Constructeur par défaut
    public RobotRoues() {
        super();
        this.vitesse = new Vitesse(80, 0, 0, 0, 80);
        this.reservoir = new Reservoir(5000, 5, 2500, 1);
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphe() {
        return this.graphe;
    }

    public void remplirReservoir(Carte carte) {
        try {
            if (carte.getVoisin(this.caseRobot, Direction.NORD).equalsTerrain("EAU")
                    || carte.getVoisin(this.caseRobot, Direction.SUD).equalsTerrain("EAU")
                    || carte.getVoisin(this.caseRobot, Direction.EST).equalsTerrain("EAU")
                    || carte.getVoisin(this.caseRobot, Direction.OUEST).equalsTerrain("EAU")) {
                this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
            }
            else {
                System.out.println("Le remplissage a échoué !");
            }
        } catch (CaseOutOfMapException e) {
            System.out.println("Verification effectuée en dehors");
            e.printStackTrace();
        }
    }

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

  /*  public ArrayList<int[]> getCoordVoisins(int []coord, Carte carte) {

        // Roues peut se déplacer UNIQUEMENT sur libre et habitat

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
                    || carte.getCase(voisinNord[0], voisinNord[1]).equalsTerrain("HABITAT")) {
                al.add(voisinNord);
            }
        }
        // Si on n'est pas sur le bord sud
        if (coord[0] != carte.getNbLignes()-1) {
            voisinSud[0] = coord[0] + 1;
            voisinSud[1] = coord[1];
            if (carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinSud[0], voisinSud[1]).equalsTerrain("HABITAT")) {
                al.add(voisinSud);
            }
        }
        // Si on n'est pas sur le bord droit
        if (coord[1] != carte.getNbColonnes()-1) {
            voisinEst[0] = coord[0];
            voisinEst[1] = coord[1] + 1;
            if (carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinEst[0], voisinEst[1]).equalsTerrain("HABITAT")) {
                al.add(voisinEst);
            }
        }
        // Si on n'est pas sur le bord gauche
        if (coord[1] != 0) {
            voisinOuest[0] = coord[0];
            voisinOuest[1] = coord[1] - 1;
            if (carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("TERRAIN_LIBRE")
                    || carte.getCase(voisinOuest[0], voisinOuest[1]).equalsTerrain("HABITAT")) {
                al.add(voisinOuest);
            }
        }
        return al;
    }*/
}
