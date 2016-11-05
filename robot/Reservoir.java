package robot;

/**
 * Created by Nicolas on 05/11/2016.
 */
public class Reservoir {
    protected int capaciteReservoir;
    // En secondes
    protected int tempsRemplissage;
    protected int volumeIntervention;
    protected int tempsIntervention;
    // Donne le volume d'eau qu'a le robot à l'instant t
    protected int volumeCourant;

    public Reservoir(int capaciteReservoir, int tempsRemplissage, int volumeIntervention, int tempsIntervention) {
        this.capaciteReservoir = capaciteReservoir;
        this.tempsRemplissage = tempsRemplissage;
        this.volumeIntervention = volumeIntervention;
        this.tempsIntervention = tempsIntervention;
        this.volumeCourant = 0;
    }

    public Reservoir() {
        this.capaciteReservoir = 0;
        this.tempsRemplissage = 0;
        this.volumeIntervention = 0;
        this.tempsIntervention = 0;
        this.volumeCourant = 0;
    }

    public int getCapaciteReservoir() {
        return this.capaciteReservoir;
    }

    public int getTempsIntervention() {
        return this.tempsIntervention;
    }

    public int getTempsRemplissage() {
        return this.tempsRemplissage;
    }

    public int getVolumeCourant() {
        return this.volumeCourant;
    }

    public int getVolumeIntervention() {
        return this.volumeIntervention;
    }

    public void setVolumeCourant(int newVolumeCourant) {
        this.volumeCourant = newVolumeCourant;
    }
}
