package io;


import carte.Carte;
import enumdata.NatureTerrain;
import robot.*;
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;



public class LecteurDonnees {


    /**
     * Class method
     * Reads and stores relevant data in an instance of DonneesSimulation
     * @param fichierDonnees nom du fichier à lire
     * @return an instance of DonneesSimulation
     */
    public static DonneesSimulation lire(String fichierDonnees)
        throws FileNotFoundException, DataFormatException {
        System.out.println("\n == Lecture du fichier" + fichierDonnees);
        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);

        //creation + remplissage de l'instance Carte
        Carte map = lecteur.lireCarte();
        lecteur.lireIncendies(map);

        //creation + remplissage de l'instance liste de Robots
        Robots[] robotL = lecteur.lireRobots(map);

        //construction de l'instance de DonneesSimulation
        DonneesSimulation datasim = new DonneesSimulation(map, robotL);
        scanner.close();
        return (datasim);
    }




    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Read and create the map
     * @throws ExceptionFormatDonnees
     * @return a map
     */
    private Carte lireCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	// en m
            //carte object initialization
            Carte map = new Carte(nbLignes, nbColonnes, tailleCases);

            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    lireCase(lig, col, map);

                }
            }
            return map;

        }
        catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }




    /**
     * Read and create an instance of Case
     */
    private void lireCase(int lig, int col, Carte map) throws DataFormatException {
        ignorerCommentaires();
        String chaineNature;
        NatureTerrain nature;

        try {
            chaineNature = scanner.next();
            // si NatureTerrain est un Enum, vous pouvez recuperer la valeur
            // de l'enum a partir d'une String avec:
            //			NatureTerrain nature = NatureTerrain.valueOf(chaineNature);
            nature = NatureTerrain.valueOf(chaineNature);
            verifieLigneTerminee();

            map.fillCase(lig,col,nature);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }
    }


    /**
     * Read and updates map with Incendie
     */
    private void lireIncendies(Carte map) throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            for (int i = 0; i < nbIncendies; i++) {
                lireIncendie(i, map);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /**
     * Put Incendie to one Case of the map
     */
    private void lireIncendie(int i, Carte map) throws DataFormatException {
        ignorerCommentaires();

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();
            map.setIncendieCase(lig,col,intensite);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Reads and creates the list of robots
     */
    private Robots[] lireRobots(Carte map) throws DataFormatException {
        ignorerCommentaires();
        Robots[] robotL;
        try {
            int nbRobots = scanner.nextInt();
            robotL = new Robots[nbRobots];
            for (int i = 0; i < nbRobots; i++) {
                lireRobot(i, robotL, map);
            }

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
        return robotL;
    }


    /**
     * Reads the information of a robot and appends it to the list
     */
    private void lireRobot(int i, Robots[] robotL, Carte map) throws DataFormatException {
        ignorerCommentaires();

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            String type = scanner.next();


            // lecture eventuelle d'une vitesse du robot (entier)
            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

            if (s == null) {
                switch (type) {
                    case("DRONE"):
                        robotL[i] = new Drone(map.getCase(lig,col));
                        break;
                    case("ROUES"):
                        robotL[i] = new RobotRoues(map.getCase(lig,col));
                        break;
                    case("PATTES"):
                        robotL[i] = new RobotPattes(map.getCase(lig,col));
                        break;
                    case("CHENILLES"):
                        robotL[i] = new RobotChenilles(map.getCase(lig,col));
                        break;
                }
            } else {
                int vitesse = Integer.parseInt(s);
                switch (type) {
                    case ("DRONE"):
                        robotL[i] = new Drone(map.getCase(lig, col), vitesse);
                        break;
                    case ("ROUES"):
                        robotL[i] = new RobotRoues(map.getCase(lig, col));
                        break;
                    case ("PATTES"):
                        break;
                    case ("CHENILLES"):
                        robotL[i] = new RobotChenilles(map.getCase(lig, col), vitesse);
                        break;
                }

            }
            verifieLigneTerminee();


        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
    }




    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
