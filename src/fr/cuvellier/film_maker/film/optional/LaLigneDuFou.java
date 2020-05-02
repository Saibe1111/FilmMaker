package fr.cuvellier.film_maker.film.optional;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;

import java.io.FileNotFoundException;

/**
 * @version 1.0 - 28/04/2020
 * @author Sébastien CUVELLIER
 */
public class LaLigneDuFou implements Film {
    private int num = 0;
    private static final int NB_IMAGES = 2;

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return 2;
    }

    /**
     * @see Film#largeur()
     */
    @Override
    public int largeur() {
        return hauteur(); // ce sera un carré
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] écran) {
        if (num == NB_IMAGES)
            return false;
        écran[num][1] = 'a'; // un 'a' se balade sur
        // la diagonale
        ++num;
        return true;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        num = 0;
    }

    public static void main(String[] args) {
        Film film = new LaLigneDuFou();
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, "out.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'out.txt' n'a pas pu être créé.");
        }
    }

}
