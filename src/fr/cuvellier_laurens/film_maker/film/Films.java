package fr.cuvellier_laurens.film_maker.film;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @version 1.0 - 13/02/2020
 * @author Denis Poitrenaud
 * Ensemble de méthode static permetant d'operer sur des Film
 */
public class Films {
    /**
     * Projette un film dans sa totalité sur System.in. Attention, la méthode ne
     * se termine pas si le film est infini.
     * @param f Le film devant être projeté.
     */
    public static void projeter(Film f) {
        char[][] ecran = getEcran(f);
        while (f.suivante(ecran)) {
            System.out.println(toString(ecran));
            pause(1. / 12);
            effacer(ecran);
        }
    }

    /**
     * Sauvegarder un film dans un fichier.
     * @param f Le film à sauvegarder.
     * @param nom le nom du fichier où sauvegarder le film.
     * @throws FileNotFoundException Si le nom du fichier ne permet pas de le créer.
     */
    public static void sauvegarder(Film f, String nom) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(nom)) {
            char[][] ecran = getEcran(f);
            out.println(f.largeur() + " " + f.hauteur());
            while (f.suivante(ecran)) {
                out.println(toString(ecran));
                out.println("\\newframe");
                effacer(ecran);
            }
        }
    }

    /**
     * Construit un ecran adapté à la projection d'un film.
     * @param f Le film pour lequel un ecran doit être constuit.
     * @return L'ecran adapté au film.
     */
    public static char[][] getEcran(Film f) {
        char[][] ecran = new char[f.hauteur()][f.largeur()];
        effacer(ecran);
        return ecran;
    }

    /**
     * Efface un ecran.
     * @param ecran L'ecran à effacer
     */
    public static void effacer(char[][] ecran) {
        for (char[] ligne : ecran)
            Arrays.fill(ligne, ' ');
    }

    /**
     * Convertit en chaine de caractère un ecran.
     * @param ecran L'ecran à convertir
     * @return La chaine correspondante à l'ecran.
     */
    public static String toString(char[][] ecran) {
        StringBuilder s = new StringBuilder();
        for (char[] ligne : ecran)
            s.append(new String(ligne)).append(System.lineSeparator());
        return s.toString();
    }

    /**
     * Endort le programme pendant un temps donné.
     * @param d Le temps d'endormissement exprimé en seconde.
     */
    public static void pause(double d) {
        try {
            Thread.sleep((long) (d * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Cette classe n'a pas vocation à être instanciée car elle ne contient que
    // des méthodes de classe (i.e. statiques).
    // Introduire un constructeur privé interdit toute instanciation (en dehors
    // de la classe elle même).
    private Films() {
    }
}
