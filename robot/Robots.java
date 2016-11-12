package robot;

import carte.Case;
import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 26/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Robots {

    protected Case caseRobot;
    protected Reservoir reservoir;
    protected Vitesse vitesse;

    //constructeur
    public Robots(Case caseRobot, Reservoir reservoir, Vitesse vitesse) {
        this.caseRobot = caseRobot;
        this.reservoir = reservoir;
        this.vitesse = vitesse;
    }

    public Robots(Case caseRobot) {
        this.caseRobot = caseRobot;
    }

    //constructeur par défaut
    public Robots() {
        //constructeur par défaut de Case (coord (0,0), type EAU, pas d'incendie)
        this.caseRobot = new Case();
    }

    public Reservoir getReservoir() {
        return reservoir;
    }

    public Case getPosition() {
        return this.caseRobot;
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
    public void deverserEau(int volume)  {
        // Décrémenter le volume du robot mais aussi décrémenter l'incendie : attendre DORR
        // Dire également le temps que ça a mis à verser l'eau
        double temps;

        if (this.reservoir.getVolumeCourant() < 1000000000) {
            if (this.reservoir.getVolumeCourant() - volume >= 0) {
                this.reservoir.setVolumeCourant(this.reservoir.getVolumeCourant() - volume);
            }
            else {
                int volume2 = volume - this.reservoir.volumeCourant;
                this.reservoir.setVolumeCourant(0);
                System.out.println("Seulement " + volume2 + " d'eau a pu être versé.");
            }
        }
        temps = ((volume % this.reservoir.getVolumeIntervention())/this.reservoir.getVolumeIntervention())*this.reservoir.getTempsIntervention();
        temps += (volume / this.reservoir.getVolumeIntervention()) * this.reservoir.getTempsIntervention();

        System.out.println("Temps pour verser eau : " + temps);
    }

 /*   void remplirReservoir() {
        // On fait en fonction des robots : pour savoir le type de robot qu'on traite on regarde le reservoir.getTempsIntervention() qui est différent en fonction des robots

        // Robot Drone


        if (this.reservoir.getTempsIntervention() == 30) {
            if (this.caseRobot.equalsTerrain("EAU")) {
                this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
            }
        }
        // Robot à roues et robot à chenilles
        else if (this.reservoir.getTempsIntervention() == 5 || this.reservoir.getTempsIntervention() == 8) {
            // On regarde si les cases à côté sont de l'eau
            // ATTENTION : Gros problème si on est sur un bord de la map...
            Case tempCase1 = new Case(this.caseRobot.getLigne() + 1, this.caseRobot.getColonne(), this.caseRobot.getTerrain());
            Case tempCase2 = new Case(this.caseRobot.getLigne() - 1, this.caseRobot.getColonne(), this.caseRobot.getTerrain());
            Case tempCase3 = new Case(this.caseRobot.getLigne(), this.caseRobot.getColonne() + 1, this.caseRobot.getTerrain());
            Case tempCase4 = new Case(this.caseRobot.getLigne(), this.caseRobot.getColonne() - 1, this.caseRobot.getTerrain());

            if (tempCase1.equalsTerrain("EAU") || tempCase2.equalsTerrain("EAU") || tempCase3.equalsTerrain("EAU") || tempCase4.equalsTerrain("EAU")) {
                // On est à côté de l'eau on peut remplir
                this.reservoir.setVolumeCourant(this.reservoir.getCapaciteReservoir());
            }

        }
        else {
            System.out.println("Le robot ne peut pas remplir son réservoir !");
        }



    } */
    public void remplirReservoir(){
        System.out.println("Superclass remplirReservoir");
    }
    
    public void creerEvenementDeplacer(Simulateur sim,/* Carte map, */int[] coordArrivee ) {
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
        sim.addEventArrive(d,this, coordArrivee[0], coordArrivee[1]);
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
}
