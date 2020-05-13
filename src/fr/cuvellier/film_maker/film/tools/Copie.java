package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;

import java.util.ArrayList;

/**
 * @author Sebastien CUVELLIER
 * @version 1.0 - 08/05/2020
 * Permet de faire une copie profonde de d'un Film
 */
public class Copie implements Film {
    private ArrayList<char[][]> images;
    private Film film;
    private int img;



    /**
     * Constructeur permet faire une copie d'un film
     * @param f film que l'on veut copier
     */
    public Copie(Film f) {
        this.film = f;
        this.img = 0;
        images = new ArrayList<>();
        lire();
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
     * @see Film#suivante(char[][]) ()
     */
    @Override
    public boolean suivante(char[][] écran) {
        if (this.img >= this.images.size())
            return false;
        for(int i = 0; i< this.images.get(this.img).length; ++i)
            System.arraycopy(this.images.get(this.img)[i], 0, écran[i], 0, this.images.get(this.img)[i].length);
        ++this.img;
        return true;
    }
    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        img = 0;
    }

    private void lire(){
        boolean test = true;
        this.images.add(new char[hauteur()][largeur()]);
        Films.effacer(this.images.get(this.images.size() - 1));
        while (test) {
            test = film.suivante(this.images.get(this.images.size() - 1));
            if (test){
                this.images.add(new char[hauteur()][largeur()]);
                Films.effacer(this.images.get(this.images.size() - 1));
            }else{
                this.images.remove(this.images.size() -1);
            }
        }
        film.rembobiner();
    }
}
