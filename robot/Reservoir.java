package robot;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */
public class Reservoir {
    private int capaciteReservoir;
    private int tempsRemplissage;
    private int volumeIntervention;
    private int tempsIntervention;
    private int volumeCourant;

    /**
     * This object contains relevant information regarding water usage
     * @param capaciteReservoir
     * @param tempsRemplissage in number of turns (for this version)
     * @param volumeIntervention
     * @param tempsIntervention in number of turns (for this version)
     */
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
        return capaciteReservoir;
    }

    public int getTempsRemplissage() {
        return tempsRemplissage;
    }

    public int getVolumeIntervention() {
        return volumeIntervention;
    }

    public int getTempsIntervention() {
        return tempsIntervention;
    }

    public int getVolumeCourant() {
        return volumeCourant;
    }

    public void setVolumeCourant(int volumeCourant) {
        this.volumeCourant = volumeCourant;
    }
}
