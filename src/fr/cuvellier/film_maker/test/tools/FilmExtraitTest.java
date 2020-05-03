package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.LaDiagonaleDuFou;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmExtrait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilmExtraitTest {

    private Film film;
    private Film f;
    @BeforeEach
    void setUp() {
        Film film1 = new TextFileInterpreter("decompte.txt");
        film = new FilmExtrait(film1,2,4);
        Film f1 = new TextFileInterpreter("marche.txt");
        f = new FilmExtrait(f1,5,9);
    }

    @Test
    void hauteur() {
        assertEquals(52, f.hauteur());
        assertEquals(105, film.hauteur()    );
    }

    @Test
    void largeur() {
        assertEquals(80, f.largeur());
        assertEquals(150, film.largeur());
    }

    @Test
    void suivante() {
        Film f2 = new FilmExtrait(new LaDiagonaleDuFou(),1,2);
        char[][] écran = new char[f2.hauteur()][f2.largeur()];
        ArrayList<char[][]> ordre = new ArrayList<>();

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[1][1] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[2][2] = 'a';

        for (char[][] chars : ordre) {
            effacer(écran);
            f2.suivante(écran);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j)
                    assertEquals(chars[i][j], écran[i][j]);
        }
    }
    public static void effacer(char[][] écran) {
        for (char[] ligne : écran)
            Arrays.fill(ligne, ' ');
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


    @Test
    void mainTEST() {
        Film film = new FilmExtrait(new LaDiagonaleDuFou(),1,2);
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, "FilmeCo.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'out.txt' n'a pas pu être créé.");
        }
    }

}