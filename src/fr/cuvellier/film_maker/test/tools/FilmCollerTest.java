package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmColler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FilmCollerTest {
    private Film film;
    private Film f;

    @BeforeEach
    void setUp() {
        Film film1 = new TextFileInterpreter("decompte.txt");
        Film film2 = new TextFileInterpreter("joconde-anim.txt");
        film = new FilmColler(film1, film2);
        Film f1 = new TextFileInterpreter("marche.txt");
        Film f2 = new TextFileInterpreter("gym.txt");
        f = new FilmColler(f1, f2);
    }

    @Test
    void hauteur() {
        assertEquals(52, f.hauteur());
        assertEquals(105, film.hauteur());
    }

    @Test
    void largeur() {
        assertEquals(80, f.largeur());
        assertEquals(150, film.largeur());
    }

    @Test
    void suivante() {
        
    }

    @Test
    void rembobiner() {
        char[][] écran = new char[f.hauteur()][f.largeur()];
        ArrayList<char[][]> ordre = new ArrayList<>();
        while (f.suivante(écran)) {
            ordre.add(écran.clone());
        }
        f.rembobiner();
        for (char[][] chars : ordre) {
            f.suivante(écran);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j)
                    assertEquals(chars[i][j], écran[i][j]);
        }
    }


    @Test
    void mainTEST() {
        Films.projeter(f);
        f.rembobiner();
        try {
            Films.sauvegarder(f, "FilmeCollerTest.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'out.txt' n'a pas pu être créé.");
        }
    }
}