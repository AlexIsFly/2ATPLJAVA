package robot;

import carte.Carte;
import carte.Case;
import enumdata.NatureTerrain;

import java.util.LinkedList;

/**
 * Created by alexisgacel on 26/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Robots {

    protected Case caseRobot;
    protected Reservoir reservoir;
    protected Vitesse vitesse;
    protected boolean idle;
    protected static LinkedList<LinkedList<LinkedList<int[]>>> graphe;
    protected String name = new String();

    //constructeur
    public Robots(Case caseRobot, Reservoir reservoir, Vitesse vitesse, String pname) {
        this.caseRobot = caseRobot;
        this.reservoir = reservoir;
        this.vitesse = vitesse;
        this.idle = true;
        this.name = pname;
    }

    public Robots(Case caseRobot, String pname) {
        this.caseRobot = caseRobot;
        this.name = pname;
    }

    //constructeur par défaut
    public Robots() {
        this.caseRobot = new Case();
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public Reservoir getReservoir() {
        return reservoir;
    }

    public Case getPosition() {
        return this.caseRobot;
    }

    public LinkedList<LinkedList<LinkedList<int[]>>> getGraphe() {
        return this.graphe;
    }

    public void setPosition(Case caseUser) {
        this.caseRobot = caseUser;
    }

    public double getVitesse(NatureTerrain ntUser) {
        if (NatureTerrain.valueOf("TERRAIN_LIBRE") == ntUser) {
            return this.vitesse.getVitesseTerrainLibre();
        }
        if (NatureTerrain.valueOf("FORET") == ntUser) {
            return this.vitesse.getVitesseForet();
        }
        if (NatureTerrain.valueOf("EAU") == ntUser) {
            return this.vitesse.getVitesseEau();
        }
        if (NatureTerrain.valueOf("ROCHE") == ntUser) {
            return this.vitesse.getVitesseRoche();
        }
        if (NatureTerrain.valueOf("HABITAT") == ntUser) {
            return this.vitesse.getVitesseHabitat();
        }
        return 0;
    }


    public void deverserEau(int volume) {

        if (this.reservoir.getVolumeCourant() < 1000000000) {
                this.getReservoir().setVolumeCourant(0);

        }
    }

    public void remplirReservoir(Carte carte){
        System.out.println("Ce robot n'a pas besoin de se remplir !");
    }

    public void creeGraphe(Carte carte) {
        System.out.println("Super-méthode creeGraphe");
    }

    @Override
    public String toString() {
        return "Robots{" +
                "name=" + name +
                ", position=" + "("+caseRobot.getCoord()[0]+", "+caseRobot.getCoord()[1]+")" +
                ", reservoir=" + reservoir.getVolumeCourant() +
                ", idle=" + idle +
                '}';
    }

    /*
    public void creerEvenementDeplacer(Simulateur sim, int[] coordArrivee ) {
        int[] posCourante = new int[this.getCase().getLigne()][this.getCase().getColonne()];
        LinkedList<int[]> tabChemin = new LinkedList<int[]>();
        // On doit creer le graphe g ici en utilisant map
        Chemin c = new Chemin( posCourante, coordArrivee, g);
        tabChemin=c.plusCourtChemin();
        while ( coordArrivee[0] !=  posCourante[0] && coordArrivee[1] != posCourante[1]){
            int i = 0;
            sim.addEventCoord(sim.getDate()+i;,tabCheminchemin(i)[0],tabChemin(i)[1]); // cette méthode ne devrait elle pas prendre un robot en paramètre comme tout les autres addEvent ??
            // on se déplace le long du chemin jusqu'a arriver a la fin
            posCourante[0]=tabChemin(i)[0];
            posCourante[1]=tabChemin(i)[1];
            i++;
        }
        sim.addEventTermine(d,this, coordArrivee[0], coordArrivee[1]);
        System.out.println("Le robot est arrivé à la case (" + posCourante[0] + ";" + posCourante[1]+") ");
    }


    public void creerEvenementIntervenir(Simulateur sim, Carte map, int[] coordIntervention) {
        int[] posCourante = new int[this.getCase().getLigne()][this.getCase().getColonne()];
        if (coordIntervention[0] !=  posCourante[0] && coordIntervention[1] != posCourante[1]){ // Si on n'est pas sur le lieu de l'intervention on commence par s'y rendre
            this.creerEvenementDeplacer(sim,map,coordIntervention);
        }
        addEventIntervention(sim.getDate(),this); // problème pour trouver la bonne date 
        System.out.println("Le robot est intervenu sur la case " + coordIntervention[0] + ";" + coordIntervention[1]+") ");
    }
    
    public void creerEvenementRemplir(Simulateur sim, Carte map, int[] coordRemplissage) {
        int[] posCourante = new int[this.getCase().getLigne()][this.getCase().getColonne()];
        if (coordRemplissage[0] !=  posCourante[0] && coordRemplissage[1] != posCourante[1]){ // Si on n'est pas sur le lieu ddu remplissage on commence par s'y rendre
            this.creerEvenementDeplacer(sim,map,coordIntervention);
        }
        addEventRemplir(sim.getDate(),this);
        System.out.println("Le robot a remplis son réservoir sur la case " + coordIntervention[0] + ";" + coordIntervention[1]+") ");
    }
    */
}
