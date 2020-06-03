package fr.cuvellier_laurens.film_maker.film;

/**
 * @version 1.0 - 13/02/2020
 * @author Denis Poitrenaud
 * Interface permetant de définir les méthodes des Film
 */
public interface Film {

    /**
     * Indique la hauteur des images de ce film (en nombre de caractères).
     * @return Hauteur minimale de l'ecran pour pouvoir afficher les images de
     *         ce film.
     */
    int hauteur();

    /**
     * Indique la largeur des images de ce film (en nombre de caractères).
     * @return largeur minimale de l'ecran pour pouvoir afficher les images de
     *         ce film.
     */
    int largeur();

    /**
     * Obtenir l'image suivante (s'il y en a une).
     * @param ecran L'ecran où afficher l'image
     * @return vrai Si l'image suivante a été affichée sur l'ecran et faux si le
     *         film est terminé
     */
    boolean suivante(char[][] ecran);

    /**
     * Rembobine le film en permettant de rejouer le film dans sa totalité (via
     * des appels successifs à la méthode suivante()).
     */
    void rembobiner();
}
