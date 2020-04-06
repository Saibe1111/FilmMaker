package fr.cuvellier.film_maker.film.tools;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;
import fr.cuvellier.film_maker.film.optional.TextFileInterpreter;

import java.io.FileNotFoundException;

public class FilmRépéter implements Film {
    private int nombreDeRépétition;
    private int répétitionRestante;
    private Film film;


    public FilmRépéter(Film film, int nombreDeRépétition) {
        this(film);
        this.nombreDeRépétition = nombreDeRépétition;
        this.répétitionRestante = nombreDeRépétition;
    }
    public FilmRépéter(Film film){
        this.film = film;
        this.nombreDeRépétition = 0;
        this.répétitionRestante = 0;
    }

    @Override
    public int hauteur() {
        return film.hauteur();
    }

    @Override
    public int largeur() {
        return film.largeur();
    }

    @Override
    public boolean suivante(char[][] écran) {
        Boolean suivant = film.suivante(écran);
        if(!suivant){
            --répétitionRestante;
            film.rembobiner();
        }
        if(répétitionRestante == -1)
            return false;
        return true;
    }

    @Override
    public void rembobiner() {
        répétitionRestante = nombreDeRépétition;
    }



    public static void main(String[] args) {
        String nom = "euler-house.txt";
        Film film1 = new TextFileInterpreter(nom);
        Film film = new FilmRépéter(film1,1);
        Films.projeter(film);
        film.rembobiner();
        try {
            Films.sauvegarder(film, ("Reproduction" + nom));
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'a pas pu être créé.");
        }
    }


}
