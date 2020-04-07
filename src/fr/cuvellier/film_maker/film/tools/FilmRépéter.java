package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

/**
 * @author Sebastien CUVELLIER
 * @version 1.0 - 06/04/2020
 */
public class FilmRépéter implements Film {
    private int nombreDeRépétition;
    private int répétitionRestante;
    private Film film;

    /**
     *Constructeur qui initialise le film et le nombre de répétition de ce dernier.
     * @param film film que l'on veut répéter.
     * @param nombreDeRépétition nombre de fois que l'on veut le répéter.
     */
    public FilmRépéter(Film film, int nombreDeRépétition) {
        this(film);
        this.nombreDeRépétition = nombreDeRépétition;
        this.répétitionRestante = nombreDeRépétition;
    }
    /**
     *Constructeur optionnel qui initialise le film de ce dernier et le nombre de répétition a une .
     * @param film film que l'on veut répéter.
     */
    public FilmRépéter(Film film) {
        this.film = film;
        this.nombreDeRépétition = 1;
        this.répétitionRestante = 1;
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
        boolean suivant = film.suivante(écran);
        if (!suivant) {
            --répétitionRestante;
            film.rembobiner();
            if (répétitionRestante !=0)
                film.suivante(écran);
        }
        return répétitionRestante != 0;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        répétitionRestante = nombreDeRépétition;
    }
}
