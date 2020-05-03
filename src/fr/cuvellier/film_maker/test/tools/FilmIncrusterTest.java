package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.LaDiagonaleDuFou;
import fr.cuvellier.film_maker.film.optional.LaLigneDuFou;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmIncruster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FilmIncrusterTest {
    private Film film;
    private Film f;

    @BeforeEach
    void setUp() {
        Film film1 = new TextFileInterpreter("decompte.txt");
        Film film2 = new TextFileInterpreter("joconde-anim.txt");
         film = new FilmIncruster(film1,film2, 1,1);
        Film f1 = new TextFileInterpreter("marche.txt");
        Film f2 = new TextFileInterpreter("gym.txt");
        f = new FilmIncruster(f1,f2, 18,18);
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
        Film f2 = new FilmIncruster(new LaDiagonaleDuFou(), new LaLigneDuFou(), 0,0);

        char[][] écran = new char[f2.hauteur()][f2.largeur()];
        ArrayList<char[][]> ordre = new ArrayList<>();

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][0] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][1] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[2][2] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[3][3] = 'a';

        for (char[][] chars : ordre) {
            Films.effacer(écran);
            f2.suivante(écran);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j)
                    assertEquals(chars[i][j], écran[i][j]);
        }
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
//    void mainTEST() {
//        Film film1 = new TextFileInterpreter("decompte.txt");
//        Film film2 = new TextFileInterpreter("euler-house.txt");
//        Film film = new FilmIncruster(film1,film2, 145,95);
//        Films.projeter(film);
//        film.rembobiner();
//        try {
//            Films.sauvegarder(film, "out.txt");
//        } catch (FileNotFoundException e) {
//            System.err.println("Le fichier 'out.txt' n'a pas pu être créé.");
//        }
//    }
}