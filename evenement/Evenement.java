package evenement;

import enumdata.Action;

/**
 * Created by alexisgacel on 30/10/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Evenement {
    protected int date;

    public Evenement(int date) {
        this.date = date;
    }

    public Evenement() {
        this.date = 0;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void execute() {
        System.out.println("Date : "+this.date);
    }
}