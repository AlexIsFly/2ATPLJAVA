package carte;

/**
 * Created by alexisgacel on 23/10/2016.
 */
enum NatureTerrain {
    FORET,
    EAU,
    ROCHE,
    TERRAIN_LIBRE,
    HABITAT;
}

public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain terrain;

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public NatureTerrain getTerrain() {
        return terrain;
    }

    public void setTerrain(NatureTerrain terrain) {
        this.terrain = terrain;
    }
}

