package fr.cuvellier.film_maker.test;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmRépéter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class FilmRépéterTest {

    @Test
    void mainTest() {
        String nom = "euler-house.txt";
        Film film1 = new TextFileInterpreter(nom);
        Film film = new FilmRépéter(film1, 2);
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, ("Reproduction" + nom));
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'a pas pu être créé.");
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