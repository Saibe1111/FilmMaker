package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

/**
 * @author Sebastien CUVELLIER
 * @version 1.0 - 07/04/2020
 */
public class FilmColler implements Film {
    private Film film1;
    private Film film2;

    /**
     * Constructeur qui initialise les deux films à coller
     * @param film1 premier film
     * @param film2 deuxième film
     */
    public FilmColler(Film film1, Film film2) {
        this.film1 = film1;
        this.film2 = film2;
    }

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return Math.max(film1.hauteur(), film2.hauteur());
    }

    /**
     * @see Film#largeur()
     */
    @Override
    public int largeur() {
        return Math.max(film1.largeur(),film2.largeur());
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] écran) {
        if(!film1.suivante(écran))
            return film2.suivante(écran);
        return true;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        film1.rembobiner();
        film2.rembobiner();
    }

}
