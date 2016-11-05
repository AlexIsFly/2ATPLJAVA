package robot;

/**
 * Created by Nicolas on 05/11/2016.
 */

public class Vitesse {

    protected double vitesse;
    protected double vitesseForet;
    protected double vitesseEau;
    protected double vitesseRoche;
    protected double vitesseHabitat;

    public Vitesse(double vitesse, double vitesseForet, double vitesseEau, double vitesseRoche, double vitesseHabitat) {
        this.vitesse = vitesse;
        this.vitesseForet = vitesseForet;
        this.vitesseEau = vitesseEau;
        this.vitesseRoche = vitesseRoche;
        this.vitesseHabitat = vitesseHabitat;
    }

    public Vitesse(double vitesse) {
        this.vitesse = vitesse;
        this.vitesseForet = vitesse;
        this.vitesseEau = vitesse;
        this.vitesseRoche = vitesse;
        this.vitesseHabitat = vitesse;
    }

    public Vitesse() {
        this.vitesse = 0;
        this.vitesseForet = 0;
        this.vitesseEau = 0;
        this.vitesseRoche = 0;
        this.vitesseHabitat = 0;
    }

    public double getVitesse () {
        return this.vitesse;
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
