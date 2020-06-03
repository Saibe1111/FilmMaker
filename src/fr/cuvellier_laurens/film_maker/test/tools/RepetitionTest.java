package fr.cuvellier_laurens.film_maker.test.tools;

import fr.cuvellier_laurens.film_maker.film.Film;
import fr.cuvellier_laurens.film_maker.film.tools.Repetition;
import fr.cuvellier_laurens.film_maker.test.necessary_for_testing.LaDiagonaleDuFou;
import fr.cuvellier_laurens.film_maker.test.necessary_for_testing.LaLigneDuFou;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

class RepetitionTest {

    @Test
    void repetitionNull(){
        Film film = new Repetition(new LaDiagonaleDuFou(),0);
        char[][] ecran = new char[film.hauteur()][film.largeur()];
        assertFalse(film.suivante(ecran));
    }

    @Test
    void repetitionNegative(){
        Film film = new Repetition(new LaDiagonaleDuFou(),-4);
        char[][] ecran = new char[film.hauteur()][film.largeur()];
        assertFalse(film.suivante(ecran));
    }

    @Test
    void hauteur() {
        Film film = new Repetition(new LaDiagonaleDuFou(),2);
        assertEquals(4, film.hauteur());
    }

    @Test
    void largeur() {
        Film film = new Repetition(new LaDiagonaleDuFou(),2);
        assertEquals(4, film.largeur());
    }

    @Test
    void suivante() {
        Film f2 = new Repetition(new LaLigneDuFou(),2);
        char[][] ecran = new char[f2.hauteur()][f2.largeur()];
        ArrayList<char[][]> ordre = new ArrayList<>();

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][0] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][1] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][0] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][1] = 'a';

        for (char[][] chars : ordre) {
            effacer(ecran);
            f2.suivante(ecran);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j)
                    assertEquals(chars[i][j], ecran[i][j]);
        }


    }
    public static void effacer(char[][] ecran) {
        for (char[] ligne : ecran)
            Arrays.fill(ligne, ' ');
    }

    @Test
    void rembobiner() {
        Film film = new Repetition(new LaDiagonaleDuFou(),2);
        char[][] ecran1 = new char[film.hauteur()][film.largeur()];
        ArrayList<char[][]> ordre1 = new ArrayList<>();
        while (film.suivante(ecran1)) {
            ordre1.add(ecran1.clone());
        }
        film.rembobiner();
        for (char[][] chars : ordre1) {
            film.suivante(ecran1);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j)
                    assertEquals(chars[i][j], ecran1[i][j]);
        }
    }




}