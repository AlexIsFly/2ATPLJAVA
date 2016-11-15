package sim;

import evenement.Evenement;
import io.DonneesSimulation;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by alexisgacel on 07/11/2016.
 * For Project Java ISSC - IMAG 2016
 */
class Timeline {

    private LinkedList<LinkedList<Evenement>>  dateL;
    private DonneesSimulation datasim;

    /**
     * L'Objet Timeline
     * <p>Cet objet permet d'organiser les événements dans le temps grâce à une représenation en double liste chaînée
     * Chaque élément de la première liste (ordonnée par date) est une liste contenant les événements correspond à cette date </p>
     * @param datasim les données de simulation
     */
    public Timeline(DonneesSimulation datasim) {
        this.dateL = new LinkedList<LinkedList<Evenement>>();
        this.datasim = datasim;
    }

    /**
     *  This method allows an event to be placed at the correct position in the timeline
     * @param event event to add to the structure
     */
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

    /**
     * Shows all events of a certain date
     * for testing purposes only
     * @param date
     */
    public void showEvents(int date) {
        try {
            ListIterator li = dateL.get(date).listIterator();
            while (li.hasNext()){
                Evenement eventshow = (Evenement)li.next();
                    System.out.println(eventshow.toString() + " PRÉVU");
            }
        }
        catch (IndexOutOfBoundsException a) {
            System.out.println("Aucunes actions pour cette date !");
        }
    }

    /**
     * Executes all events of a certain date
     * @param date
     */
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
