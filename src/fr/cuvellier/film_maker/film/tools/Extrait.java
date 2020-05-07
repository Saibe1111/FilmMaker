package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;


/**
 * @version 1.0 - 08/04/2020
 * @author Sebastien CUVELLIER
 */
public class Extrait implements Film {
    private Film film;
    private int prmièreImage;
    private int dernièreImage;
    private int imageEnCour;

    /**
     * Constructeur qui permet d'initialiser le film,la première image inclus et la dernière image inclus du film.
     * @param film Le film dont on veut un extrait
     * @param prmièreImage La première image que l'on veut pour notre nouveau film
     * @param dernièreImage La dernière image que l'on veut pour notre nouveau film
     */
    public Extrait(Film film, int prmièreImage, int dernièreImage) {
        this.film = film;
        this.prmièreImage = prmièreImage;
        this.dernièreImage = dernièreImage;
        this.imageEnCour = 0;
    }

    /**
     * Constructeur qui permet d'initialiser le film et  la dernière image inclus du film.
     * @param film Le film dont on veut un extrait
     * @param dernièreImage La dernière image que l'on veut pour notre nouveau film
     */
    public Extrait(Film film, int dernièreImage) {
        this(film,0,dernièreImage);
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
    public boolean suivante(char[][] écran) {
        if (imageEnCour < prmièreImage) {
            for (int i = imageEnCour; i < prmièreImage; ++i) {
                film.suivante(écran);
                Films.effacer(écran);
                ++this.imageEnCour;
            }
        }
        if (dernièreImage < imageEnCour) {
            return false;
        }
        ++this.imageEnCour;
        return film.suivante(écran);
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
