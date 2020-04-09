package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

/**
 * @version 1.0 - 09/04/2020
 * @author Sebastien CUVELLIER
 * D’incruster un ﬁlm dans un ﬁlm.
 * Le point d’incrustation sera d´esign´e par les num´eros de ligne et de colonne
 * que doit prendre le coin en haut `a gauche du ﬁlm devant ˆetre incrust´e
 * dans les images du ﬁlm ou` il est incrust´e.
 */
public class FilmIncruster implements Film {
    private Film film1;
    private Film film2;
    private int colone;
    private int ligne;

    /**
     * @see Film#hauteur() 
     */
    @Override
    public int hauteur() {
        return 0;
    }

    /**
     * @see Film#largeur() 
     */
    @Override
    public int largeur() {
        return 0;
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] écran) {
        return false;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {

    }
}
