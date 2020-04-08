package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

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
