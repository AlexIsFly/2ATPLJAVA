package evenement;

import carte.Carte;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */
abstract public class Evenement {
    private int date;

    Evenement(int date) {
        this.date = date;
    }

    Evenement() {
        this.date = 0;
    }

    public int getDate() {
        return date;
    }

    /**
     * This method execute the relevant instructions for this event
     * @param carte To check adjacent Case
     */
    public void execute(Carte carte){}

    @Override
    public String toString() {
        return "date=" + date;
    }
}
