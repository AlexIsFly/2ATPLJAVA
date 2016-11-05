package robot;

/**
 * Created by Nicolas on 05/11/2016.
 */

public class Vitesse {

    protected double vitesseTerrainLibre;
    protected double vitesseForet;
    protected double vitesseEau;
    protected double vitesseRoche;
    protected double vitesseHabitat;

    public Vitesse(double vitesseTerrainLibre, double vitesseForet, double vitesseEau, double vitesseRoche, double vitesseHabitat) {
        this.vitesseTerrainLibre = vitesseTerrainLibre;
        this.vitesseForet = vitesseForet;
        this.vitesseEau = vitesseEau;
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

    public void setVitesseTerrainLibre(double vitesse) {
        this.vitesseTerrainLibre = vitesseTerrainLibre;
    }

    public void setVitesseForet(double vitesseForet) {
        this.vitesseForet = vitesseForet;
    }

    public void setVitesseEau(double vitesseEau) {
        this.vitesseEau = vitesseEau;
    }

    public void setVitesseRoche(double vitesseRoche) {
        this.vitesseRoche = vitesseRoche;
    }

    public void setVitesseHabitat(double vitesseHabitat) {
        this.vitesseHabitat = vitesseHabitat;
    }
}
