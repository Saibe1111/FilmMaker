package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.optional.LaLigneDuFou;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;
import fr.cuvellier.film_maker.film.tools.FilmEncadrement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilmEncadrementTest {

    private Film film;
    private Film f;

    @BeforeEach
    void setUp() {
        Film film1 = new TextFileInterpreter("decompte.txt");
         film = new FilmEncadrement(film1);
        Film f2 = new TextFileInterpreter("gym.txt");
        f = new FilmEncadrement(f2);
    }

    @Test
    void hauteur() {
        assertEquals(58, f.hauteur());
        assertEquals(113, film.hauteur());
    }

    @Test
    void largeur() {
        assertEquals(83, f.largeur());
        assertEquals(158, film.largeur());
    }

    @Test
    void suivante() {
        Film f2 = new FilmEncadrement(new LaLigneDuFou(), 1,'*');

        ArrayList<char[][]> ordre = new ArrayList<>();

        //Ligne
        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));

        ordre.get(ordre.size() - 1)[1][1] = 'a';
        //bodure
        ordre.get(ordre.size() - 1)[0][0] = '*';
        ordre.get(ordre.size() - 1)[1][0] = '*';
        ordre.get(ordre.size() - 1)[2][0] = '*';
        ordre.get(ordre.size() - 1)[3][0] = '*';
        ordre.get(ordre.size() - 1)[0][1] = '*';
        ordre.get(ordre.size() - 1)[0][2] = '*';
        ordre.get(ordre.size() - 1)[0][3] = '*';
        ordre.get(ordre.size() - 1)[3][1] = '*';
        ordre.get(ordre.size() - 1)[3][2] = '*';
        ordre.get(ordre.size() - 1)[3][3] = '*';
        ordre.get(ordre.size() - 1)[1][3] = '*';
        ordre.get(ordre.size() - 1)[2][3] = '*';
        //Ligne
        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[1][2] = 'a';
        //bodure
        ordre.get(ordre.size() - 1)[0][0] = '*';
        ordre.get(ordre.size() - 1)[1][0] = '*';
        ordre.get(ordre.size() - 1)[2][0] = '*';
        ordre.get(ordre.size() - 1)[3][0] = '*';
        ordre.get(ordre.size() - 1)[0][1] = '*';
        ordre.get(ordre.size() - 1)[0][2] = '*';
        ordre.get(ordre.size() - 1)[0][3] = '*';
        ordre.get(ordre.size() - 1)[3][1] = '*';
        ordre.get(ordre.size() - 1)[3][2] = '*';
        ordre.get(ordre.size() - 1)[3][3] = '*';
        ordre.get(ordre.size() - 1)[1][3] = '*';
        ordre.get(ordre.size() - 1)[2][3] = '*';

        char[][] écran = new char[f2.hauteur()][f2.largeur()];
        for (char[][] chars : ordre) {
            effacer(écran);
            f2.suivante(écran);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j){
                    assertEquals(chars[i][j], écran[i][j]);
                }
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

//    @Test
//    void mainTEST() {
//        Film film2 = new TextFileInterpreter("euler-house.txt");
//        Film film = new FilmEncadrement(film2);
//        Films.projeter(film);
//        film.rembobiner();
//        try {
//            Films.sauvegarder(film, "out.txt");
//        } catch (FileNotFoundException e) {
//            System.err.println("Le fichier 'out.txt' n'a pas pu être créé.");
//        }
//    }
}