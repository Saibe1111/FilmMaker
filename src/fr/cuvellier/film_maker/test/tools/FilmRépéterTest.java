package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmColler;
import fr.cuvellier.film_maker.film.tools.FilmRépéter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FilmRépéterTest {
    private Film film;
    private Film f;

    @BeforeEach
    void setUp() {
        Film film1 = new TextFileInterpreter("decompte.txt");
        film = new FilmRépéter(film1, 1);
        Film f1 = new TextFileInterpreter("marche.txt");
        f = new FilmRépéter(f1,2);
    }

    @Test
    void répétitionNull(){
        Film filmIntéNull = new TextFileInterpreter("decompte.txt");
        Film filmNull = new FilmRépéter(filmIntéNull, 0);
        char[][] écran = new char[filmIntéNull.hauteur()][filmIntéNull.largeur()];
        assertFalse(filmNull.suivante(écran));
    }
    @Test
    void répétitionNégative(){
        Film filmIntéNull = new TextFileInterpreter("decompte.txt");
        Film filmNull = new FilmRépéter(filmIntéNull, -1);
        char[][] écran = new char[filmIntéNull.hauteur()][filmIntéNull.largeur()];
        assertFalse(filmNull.suivante(écran));
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
        char[][] écran1 = new char[film.hauteur()][film.largeur()];
        ArrayList<char[][]> ordre1 = new ArrayList<>();
        while (film.suivante(écran1)) {
            ordre1.add(écran1.clone());
        }
        film.rembobiner();
        for (char[][] chars : ordre1) {
            film.suivante(écran1);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j)
                    assertEquals(chars[i][j], écran1[i][j]);
        }
    }

//    @Test
//    void mainTest() {
//        String nom = "euler-house.txt";
//        Film film1 = new TextFileInterpreter(nom);
//        Film film = new FilmRépéter(film1, 1);
//        Films.projeter(film);
//        film.rembobiner();
//        try {
//            Films.sauvegarder(film, ("Reproduction" + nom));
//        } catch (FileNotFoundException e) {
//            System.err.println("Le fichier n'a pas pu être créé.");
//        }
//    }
}