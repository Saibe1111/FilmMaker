package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.test.necessary_for_testing.LaDiagonaleDuFou;
import fr.cuvellier.film_maker.test.necessary_for_testing.LaLigneDuFou;
import fr.cuvellier.film_maker.film.tools.Extrait;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExtraitTest {

    @Test
    void hauteur() {
        Film film = new Extrait(new LaLigneDuFou(),2,4);
        assertEquals(2, film.hauteur());
    }

    @Test
    void largeur() {
        Film film = new Extrait(new LaLigneDuFou(),2,4);
        assertEquals(2, film.largeur());
    }

    @Test
    void suivante() {
        Film f2 = new Extrait(new LaDiagonaleDuFou(),1,2);

        char[][] écran = new char[f2.hauteur()][f2.largeur()];
        ArrayList<char[][]> ordre = new ArrayList<>();

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[1][1] = 'a';

        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));
        ordre.get(ordre.size() - 1)[2][2] = 'a';

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
        Film film = new Extrait(new LaLigneDuFou(),2,4);
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
}