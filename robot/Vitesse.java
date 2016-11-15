package robot;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */

public class Vitesse {

    private double vitesseTerrainLibre;
    private double vitesseForet;
    private double vitesseEau;
    private double vitesseRoche;
    private double vitesseHabitat;


    /**
     * This object contains the different speed of each robot
     * @param vitesseTerrainLibre
     * @param vitesseForet
     * @param vitesseRoche
     * @param vitesseHabitat
     */
    public Vitesse(double vitesseTerrainLibre, double vitesseForet, double vitesseRoche, double vitesseHabitat) {
        this.vitesseTerrainLibre = vitesseTerrainLibre;
        this.vitesseForet = vitesseForet;
        this.vitesseEau = (double) 0;
        this.vitesseRoche = vitesseRoche;
        this.vitesseHabitat = vitesseHabitat;
    }

    public Vitesse(double vitesseTerrainLibre) {
        this.vitesseTerrainLibre = vitesseTerrainLibre;
        this.vitesseForet = vitesseTerrainLibre;
        this.vitesseEau = vitesseTerrainLibre;
        this.vitesseRoche = vitesseTerrainLibre;
        this.vitesseHabitat = vitesseTerrainLibre;
    }

    public Vitesse() {
        this.vitesseTerrainLibre = 0;
        this.vitesseForet = 0;
        this.vitesseEau = 0;
        this.vitesseRoche = 0;
        this.vitesseHabitat = 0;
    }

    public double getVitesseTerrainLibre () {
        return this.vitesseTerrainLibre;
    }

    public double getVitesseForet() {
        return this.vitesseForet;
    }

    public double getVitesseEau() {
        return this.vitesseEau;
    }

    public double getVitesseRoche() {
        return this.vitesseRoche;
    }

    public double getVitesseHabitat() {
        return this.vitesseHabitat;
    }

}
