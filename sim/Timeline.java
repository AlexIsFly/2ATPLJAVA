package sim;

import evenement.Evenement;
import io.DonneesSimulation;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by alexisgacel on 07/11/2016.
 * For Project Java ISSC - IMAG 2016
 */
public class Timeline {
    private LinkedList<LinkedList<Evenement>>  dateL;
    private DonneesSimulation datasim;

    public Timeline(DonneesSimulation datasim) {
        this.dateL = new LinkedList<LinkedList<Evenement>>();
        this.datasim = datasim;
    }

    public void addEvent(Evenement event) {
        int date = event.getDate();
        try {
            LinkedList tempL = dateL.get(date);
            tempL.addLast(event);
        }
        catch (IndexOutOfBoundsException a) {
            for (int i=dateL.size(); i<=date; i++){
                LinkedList eventL = new LinkedList();
                dateL.add(i, eventL);
            }
            LinkedList tempL = dateL.get(date);
            tempL.addLast(event);
        }
    }

    public void showEvents(int date) {
        try {
            ListIterator li = dateL.get(date).listIterator();
            while (li.hasNext()){
                System.out.println(li.next().toString());
            }
        }
        catch (IndexOutOfBoundsException a) {
            System.out.println("Date inexistante");
        }
    }

    public void executeEvents(int date) {
        try {
            ListIterator li = dateL.get(date).listIterator();
            while (li.hasNext()){
                ((Evenement)li.next()).execute(datasim.getCarte());
            }
        }
        catch (IndexOutOfBoundsException a) {
            System.out.println("Date inexistante");
        }
    }
}