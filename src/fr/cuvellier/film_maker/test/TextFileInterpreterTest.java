package fr.cuvellier.film_maker.test;

import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class TextFileInterpreterTest {

    @Test
    void mainTEST() {
        String nom = "euler-house.txt";
        TextFileInterpreter film = new TextFileInterpreter(nom);
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