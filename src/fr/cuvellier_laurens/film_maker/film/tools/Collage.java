package fr.cuvellier_laurens.film_maker.film.tools;

import fr.cuvellier_laurens.film_maker.film.Film;

/**
 * @author Sebastien CUVELLIER - Fleur LAURENS
 * @version 1.0 - 07/04/2020
 * Permet de mettre deux films à la suite
 */

public class Collage implements Film{
    private Film film1;
    private Film film2;

    /**
     * Constructeur qui initialise les deux films à coller
     * @param film1 premier film
     * @param film2 deuxième film
     */
    public Collage(Film film1, Film film2) {
        this.film1 = new Copie(film1);
        this.film2 = new Copie(film2);
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
    public boolean suivante(char[][] ecran) {
        if(!film1.suivante(ecran))
            return film2.suivante(ecran);
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