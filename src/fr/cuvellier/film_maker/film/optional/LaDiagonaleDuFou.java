package fr.cuvellier.film_maker.film.optional;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;

import java.io.FileNotFoundException;


/**
 * Un exemple basique d'implémentation de l'interface Film.
 */

public class LaDiagonaleDuFou implements Film {
    private int num = 0;
    private static final int NB_IMAGES = 20;
    char test = 'a';
    @Override
    public int hauteur() {
        return 10;
    }

    @Override
    public int largeur() {
        return hauteur(); // ce sera un carré
    }

    @Override
    public boolean suivante(char[][] écran) {
        if (num == NB_IMAGES)
           return false;
        //écran[num % hauteur()][num % hauteur()] = 'a'; // un 'a' se balade sur
        écran[0][0] = test;
        test++;
        // la diagonale
        System.out.println(this.num);
        ++num;
        return true;
    }

    @Override
    public void rembobiner() {
        num = 0;
        test = 'a';
    }

    /**
     * La projection (puis la sauvegarde) d'un tel film.
     */
    public static void main(String[] args) {
        Film film = new LaDiagonaleDuFou();
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, "fou.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'fou.txt' n'a pas pu être créé.");
        }
    }
}
