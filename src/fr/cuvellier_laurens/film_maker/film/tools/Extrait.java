package fr.cuvellier_laurens.film_maker.film.tools;

import fr.cuvellier_laurens.film_maker.film.Film;
import fr.cuvellier_laurens.film_maker.film.Films;


/**
 * @version 1.0 - 08/04/2020
 * @author Sebastien CUVELLIER - Fleur LAURENS
 * Permet d'obtenir l'extrait d'un Film
 */
public class Extrait implements Film {
    private Film film;
    private int premiereImage;
    private int derniereImage;
    private int imageEnCour;

    /**
     * Constructeur qui permet d'initialiser le film,la première image inclus et la dernière image inclus du film.
     * @param film Le film dont on veut un extrait
     * @param premiereImage La première image que l'on veut pour notre nouveau film
     * @param derniereImage La dernière image que l'on veut pour notre nouveau film
     */
    public Extrait(Film film, int premiereImage, int derniereImage) {
        this.film = film;
        this.premiereImage = premiereImage;
        this.derniereImage = derniereImage;
        this.imageEnCour = 0;
    }

    /**
     * Constructeur qui permet d'initialiser le film et  la dernière image inclus du film.
     * @param film Le film dont on veut un extrait
     * @param derniereImage La dernière image que l'on veut pour notre nouveau film
     */
    public Extrait(Film film, int derniereImage) {
        this(film,0, derniereImage);
    }

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return film.hauteur();
    }

    /**
     * @see Film#largeur()
     */
    @Override
    public int largeur() {
        return film.largeur();
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] ecran) {
        if (imageEnCour < premiereImage) {
            for (int i = imageEnCour; i < premiereImage; ++i) {
                film.suivante(ecran);
                Films.effacer(ecran);
                ++this.imageEnCour;
            }
        }
        if (derniereImage < imageEnCour) {
            return false;
        }
        ++this.imageEnCour;
        return film.suivante(ecran);
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        film.rembobiner();
        this.imageEnCour = 0;
    }
}
