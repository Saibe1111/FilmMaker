package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;

/**
 * @author Sebastien CUVELLIER
 * D’incrusterm un ﬁlm dans un ﬁl.
 * Le point d’incrustation sera d´esign´e par les num´eros de ligne et de colonne
 * que doit prendre le coin en haut `a gauche du ﬁlm devant ˆetre incrust´e
 * dans les images du ﬁlm ou` il est incrust´e.
 * @version 1.0 - 09/04/2020
 */
public class FilmIncruster implements Film {
    private Film filmBase;
    private Film filmAIncruster;
    private int colone;
    private int ligne;


    public FilmIncruster(Film filmBase, Film filmAIncruster, int colone, int ligne) {
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

        boolean retourSuivant = this.filmBase.suivante(sousEcran1);
        boolean suivantIncruster = filmAIncruster.suivante(sousEcran2);
        if (retourSuivant){
            for (int i = 0; i < hauteur(); ++i)
                for (int j = 0; j < largeur(); ++j)
                    if (i >= this.ligne && j >= this.colone && i<= this.filmAIncruster.hauteur() && j <= this.filmAIncruster.largeur()){
                        if ( i - this.ligne < sousEcran2.length && j - this.colone < sousEcran2[i - this.colone].length )
                            écran[i][j] = sousEcran2[i - this.colone][j - this.ligne];
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
