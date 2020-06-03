package fr.cuvellier_laurens.film_maker.film.tools;

import fr.cuvellier_laurens.film_maker.film.Film;
import fr.cuvellier_laurens.film_maker.film.Films;

/**
 * @author Sebastien CUVELLIER  - Fleur LAURENS
 * @version 3.0 - 03/05/2020
 * Permet d'encadrer un Film
 */
public class Encadrement implements Film{
    private Film film;
    private char motif;
    private int tailleBordure;

    /**
     * Constructeur qui permet d'encardrer un film
     *
     * @param film film que l'on veut encadrer.
     */
    public Encadrement(Film film) {
        this(film, 1, '*');
    }

    /**
     * Constructeur qui permet d'encardrer un film
     *
     * @param film          film que l'on veut encadrer.
     * @param tailleBordure taille de la bodure.
     * @param motif         motif avec lequel on veut entourer.
     */
    public Encadrement(Film film, int tailleBordure, char motif) {
        this.tailleBordure = tailleBordure;
        this.motif = motif;
        this.film = film;
    }

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return film.hauteur() + 2 * tailleBordure;
    }

    /**
     * @see Film#largeur()
     */
    @Override
    public int largeur() {
        return film.largeur() + 2 * tailleBordure;
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] ecran) {
        char[][] sousEcran = new char[film.hauteur()][film.largeur()];
        Films.effacer(sousEcran);
        for (int i = 0; i < this.largeur(); ++i)
            for (int j = 0; j < tailleBordure; ++j) {
                ecran[j][i] = motif;
                ecran[this.hauteur() - j - 1][i] = motif;
            }
        for (int i = 0; i < this.hauteur(); ++i)
            for (int j = 0; j < tailleBordure; ++j) {
                ecran[i][j] = motif;
                ecran[i][this.largeur() - j - 1] = motif;
            }
        boolean suivant = film.suivante(sousEcran);
        for (int i = 0; i < sousEcran.length; ++i)
            System.arraycopy(sousEcran[i], 0, ecran[i + tailleBordure], tailleBordure, sousEcran[i].length);
        return suivant;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        film.rembobiner();
    }
}
