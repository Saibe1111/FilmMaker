package fr.cuvellier_laurens.film_maker.test.tools;

import fr.cuvellier_laurens.film_maker.film.Film;
import fr.cuvellier_laurens.film_maker.film.Films;
import fr.cuvellier_laurens.film_maker.film.tools.Copie;
import fr.cuvellier_laurens.film_maker.film.tools.Extrait;
import fr.cuvellier_laurens.film_maker.test.necessary_for_testing.LaDiagonaleDuFou;
import fr.cuvellier_laurens.film_maker.test.necessary_for_testing.LaLigneDuFou;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

class CopieTest {

    @Test
    void hauteur() {
        Film film = new Copie(new LaLigneDuFou());
        assertEquals(2, film.hauteur());
    }

    @Test
    void largeur() {
        Film film = new Copie(new LaLigneDuFou());
        assertEquals(2, film.largeur());
    }


    @Test
    void suivante() {
        Film f2 = new Copie(new LaDiagonaleDuFou());
        char[][] ecran = new char[f2.hauteur()][f2.largeur()];
        ArrayList<char[][]> ordre = new ArrayList<>();

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[0][0] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[1][1] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[2][2] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[3][3] = 'a';

        for (char[][] chars : ordre) {
            Films.effacer(ecran);
            f2.suivante(ecran);
            for (int i = 0; i < chars.length; ++i)
                for (int j = 0; j < chars[i].length; ++j)
                    assertEquals(chars[i][j], ecran[i][j]);
        }
    }

    @Test
    void rembobiner() {
        Film film = new Extrait(new LaLigneDuFou(),2,4);
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