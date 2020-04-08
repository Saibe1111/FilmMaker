package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

/**
 * @version 1.0 - 08/04/2020
 * @author Sebastien CUVELLIER
 */
public class FilmEncadrement implements Film {
    private Film film;
    private final char MOTIF = '*';
    private final int TAILLE_BORDURE = 4;
    private boolean bordure = false;

    /**
     *
     * @param film film que l'on veut encadrer.
     */
    public FilmEncadrement(Film film) {
        this.film = film;
    }

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return film.hauteur() + 2 * TAILLE_BORDURE;
    }

    /**
     * @see Film#largeur() 
     */
    @Override
    public int largeur() {
        return film.largeur() + 2 * TAILLE_BORDURE;
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] écran) {
        char[][] sousEcran = new char[film.hauteur()][film.largeur()];
        if (!bordure) {
            for (int i = 0; i < this.largeur(); ++i)
                for (int j = 0; j < TAILLE_BORDURE; ++j) {
                    écran[j][i] = MOTIF;
                    écran[this.hauteur() - j - 1][i] = MOTIF;
                }
            for (int i = 0; i < this.hauteur(); ++i)
                for (int j = 0; j < TAILLE_BORDURE; ++j) {
                    écran[i][j] = MOTIF;
                    écran[i][this.largeur() - j - 1] = MOTIF;
                }
            this.bordure = true;
        }
        boolean suivant = film.suivante(sousEcran);
        for (int i = 0; i < sousEcran.length; ++i)
            System.arraycopy(sousEcran[i], 0, écran[i + TAILLE_BORDURE], 4, sousEcran[i].length);
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
