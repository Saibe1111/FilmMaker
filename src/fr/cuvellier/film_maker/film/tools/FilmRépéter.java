package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

public class FilmRépéter implements Film {
    private int nombreDeRépétition;

    @Override
    public int hauteur() {
        return 0;
    }

    @Override
    public int largeur() {
        return 0;
    }

    @Override
    public boolean suivante(char[][] écran) {
        return false;
    }

    @Override
    public void rembobiner() {

    }
}
