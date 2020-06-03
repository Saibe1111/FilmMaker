package fr.cuvellier_laurens.film_maker.film.tools;

import fr.cuvellier_laurens.film_maker.film.Film;
import fr.cuvellier_laurens.film_maker.film.Films;

/**
 * @author Sebastien CUVELLIER - Fleur LAURENS
 * @version 2.3 - 27/05/2020
 *Permet de répéter un Film n fois
 */
public class Repetition implements Film {
    private int nombreDeRepetition;
    private int repetitionRestante;
    private Film film;
    private boolean filmVide = false;

    /**
     *Constructeur qui initialise le film et le nombre de répétition de ce dernier.
     * @param film film que l'on veut répéter.
     * @param nombreDeRepetition nombre de fois que l'on veut le répéter.
     */
    public Repetition(Film film, int nombreDeRepetition) {
        this(film);
        this.nombreDeRepetition = nombreDeRepetition;
        this.repetitionRestante = nombreDeRepetition;
    }
    /**
     *Constructeur optionnel qui initialise le film de ce dernier et le nombre de répétition a une .
     * @param film film que l'on veut répéter.
     */
    public Repetition(Film film) {
        this.film = new Copie(film);
        this.nombreDeRepetition = 1;
        this.repetitionRestante = 1;
        testerFilmVide();
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
        if(filmVide)
            return false;
            if (!film.suivante(ecran)) {
                --repetitionRestante;
                film.rembobiner();
                if (repetitionRestante !=0)
                    film.suivante(ecran);
            }
        return repetitionRestante > 0;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        repetitionRestante = nombreDeRepetition;
    }

    private void testerFilmVide(){
        //on verifie si le film est vide
        char[][] Ecran = new char[this.film.hauteur()][this.film.largeur()];
        Films.effacer(Ecran);
        this.film.suivante(Ecran);
        boolean test = false;
        for (char[] images : Ecran)
            for (char image : images)
                if (image != ' ') {
                    test = true;
                    break;
                }
        if(!test)
            filmVide = true;
        this.film.rembobiner();
    }

}
