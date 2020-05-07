package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;

/**
 * @author Sebastien CUVELLIER
 * @version 4.0 - 03/05/2020
 */
public class Incrustation implements Film {
    private Film filmBase;
    private Film filmAIncruster;
    private int colone;
    private int ligne;

    /**
     *
     * @param filmBase film dans lequel on incruste un film
     * @param filmAIncruster film que l'on incruste
     * @param colone colone à laquel on incruste le film
     * @param ligne ligne à laquel on incruste le film
     */
    public Incrustation(Film filmBase, Film filmAIncruster, int colone, int ligne) {
        this.filmBase = filmBase;
        this.filmAIncruster = filmAIncruster;
        this.colone = colone;
        this.ligne = ligne;
    }

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return this.filmBase.hauteur();
    }

    /**
     * @see Film#largeur()
     */
    @Override
    public int largeur() {
        return this.filmBase.largeur();
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] écran) {
        char[][] sousEcran1 = new char[this.filmBase.hauteur()][this.filmBase.largeur()];
        char[][] sousEcran2 = new char[this.filmAIncruster.hauteur()][this.filmAIncruster.largeur()];

        Films.effacer(sousEcran1);
        Films.effacer(sousEcran2);
        Films.effacer(écran);
        boolean retourSuivant = this.filmBase.suivante(sousEcran1);
        filmAIncruster.suivante(sousEcran2);
        if (retourSuivant){
            for (int i = 0; i < hauteur(); ++i)
                for (int j = 0; j < largeur(); ++j)
                    if (i >= this.ligne && j >= this.colone && i - this.ligne +1 <= this.filmAIncruster.hauteur() && j - this.colone + 1<= this.filmAIncruster.largeur()){
                        if ( i - this.ligne  < sousEcran2.length && j - this.colone < sousEcran2[i - this.ligne].length ){
                            écran[i][j] = sousEcran2[i - this.ligne][j - this.colone];
                        }

                    }
                    else
                    if( i < sousEcran1.length && j < sousEcran1[i].length )
                        écran[i][j] = sousEcran1[i][j];
        }

        return retourSuivant;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        filmBase.rembobiner();
        filmAIncruster.rembobiner();
    }
}