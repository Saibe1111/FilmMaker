package fr.cuvellier.film_maker.film;

/**
 * @version 1.0 - 13/02/2020
 * @author Denis Poitrenaud
 */
public interface Film {

    /**
     * Indique la hauteur des images de ce film (en nombre de caractères).
     * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
     *         ce film.
     */
    int hauteur();

    /**
     * Indique la largeur des images de ce film (en nombre de caractères).
     * @return largeur minimale de l'écran pour pouvoir afficher les images de
     *         ce film.
     */
    int largeur();

    /**
     * Obtenir l'image suivante (s'il y en a une).
     * @param écran L'écran où afficher l'image
     * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
     *         film est terminé
     */
    boolean suivante(char[][] écran);

    /**
     * Rembobine le film en permettant de rejouer le film dans sa totalité (via
     * des appels successifs à la méthode suivante()).
     */
    void rembobiner();
}
