package fr.cuvellier.film_maker.film.optional;

import fr.cuvellier.film_maker.film.Film;

/**
 * @version 1.0 - 13/02/2020
 * @author Denis Poitrenaud
 */
public class LaDiagonaleDuFou implements Film {
    private int num = 0;
    private static final int NB_IMAGES = 20;

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return 10;
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
}
