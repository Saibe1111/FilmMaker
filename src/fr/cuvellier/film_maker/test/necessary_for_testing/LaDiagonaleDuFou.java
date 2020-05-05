package fr.cuvellier.film_maker.test.necessary_for_testing;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;

import java.io.FileNotFoundException;

/**
 * @version 2.0 - 28/04/2020
 * @author Denis Poitrenaud, Sébastien CUVELLIER
 */
public class LaDiagonaleDuFou implements Film {
    private int num = 0;
    private static final int NB_IMAGES = 4;

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return 4;
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
        écran[num % hauteur()][num % hauteur()] = 'a'; // un 'a' se balade sur
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
        Film film = new LaDiagonaleDuFou();
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, "out.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'out.txt' n'a pas pu être créé.");
        }
    }

}
