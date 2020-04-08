package fr.cuvellier.film_maker.test;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmExtrait;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FilmExtraitTest {

    @Test
    void mainTEST() {
        Film film1 = new TextFileInterpreter("euler-house.txt");
        Film film = new FilmExtrait(film1,2,4);
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, "FilmeCo.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'fou.txt' n'a pas pu être créé.");
        }
    }

    @Test
    void hauteur() {
    }

    @Test
    void largeur() {
    }

    @Test
    void suivante() {
    }

    @Test
    void rembobiner() {
    }
}