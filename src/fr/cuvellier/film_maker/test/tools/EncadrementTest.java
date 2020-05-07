package fr.cuvellier.film_maker.test.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.tools.Encadrement;
import fr.cuvellier.film_maker.test.necessary_for_testing.LaLigneDuFou;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncadrementTest {

    @Test
    void hauteur() {
        Film film = new Encadrement(new LaLigneDuFou());
        assertEquals(4, film.hauteur());
    }

    @Test
    void largeur() {
        Film film = new Encadrement(new LaLigneDuFou());
        assertEquals(4, film.largeur());
    }

    @Test
    void suivante() {
        Film f2 = new Encadrement(new LaLigneDuFou());

        ArrayList<char[][]> ordre = new ArrayList<>();

        //Ligne
        ordre.add(new char[f2.hauteur()][f2.largeur()]);
        Films.effacer(ordre.get(ordre.size() - 1));

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
        Films.effacer(ordre.get(ordre.size() - 1));
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
            Films.effacer(écran);
            f2.suivante(écran);
            for(int i=0;i<chars.length;++i)
                for(int j = 0; j<chars[i].length;++j){
                    assertEquals(chars[i][j], écran[i][j]);
                }
        }
    }

    @Test
    void rembobiner() {
        Film film = new Encadrement(new LaLigneDuFou());
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