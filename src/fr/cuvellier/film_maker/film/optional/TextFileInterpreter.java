package fr.cuvellier.film_maker.film.optional;

import fr.cuvellier.film_maker.film.Film;
import fr.cuvellier.film_maker.film.Films;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileInterpreter implements Film {
    private ArrayList<String> ligneFilm;
    private int hauteur;
    private int largeur;
    private int nombreImages;
    private String nom;
    private int num;

    public TextFileInterpreter(String nom) {
        ligneFilm = new ArrayList<>();
        this.nom = nom;
        try {
            lire(nom);
        } catch (FileNotFoundException e) {
            System.out.println("le fichier est introuvable");
        }
    }

    @Override
    public int hauteur() {
        return this.hauteur;
    }

    @Override
    public int largeur() {
        return this.largeur;
    }

    @Override
    public boolean suivante(char[][] écran) {


//        while (!ligneFilm.isEmpty()){
//            System.out.println( ligneFilm.get(0));
//            ligneFilm.remove(0);
//        }



        if (num == nombreImages)
            return false;
        int traitement = num * hauteur ;
        for(int i = 0; i < hauteur + 1; ++i){
            char[] chars = ligneFilm.get(i).toCharArray();
            ligneFilm.remove(i);
            if(chars.length !=0)
                for(int j = 0; j < largeur; ++j)
                     //écran[i][j] = chars[j];
                    System.out.print("[" + chars[j] + "] ");
            System.out.println();
        }
        System.out.println(this.num);
        ++this.num;

        return true;
    }

    @Override
    public void rembobiner() {
        try {
            lire(nom);
        } catch (FileNotFoundException e) {
            System.out.println("le fichier est introuvable");
        }
        num = 0;
    }

    private void lire(String nom) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream(nom));
        Scanner ligne = new Scanner(in.nextLine());
        this.largeur = Integer.parseInt(ligne.next());
        this.hauteur = Integer.parseInt(ligne.next());
        ligne.close();
        while (in.hasNextLine()) {
            String ligneSuivante = in.nextLine();
            if (ligneSuivante.equals("\\newframe"))
                ++nombreImages;
            else
                ligneFilm.add(ligneSuivante);
        }
        in.close();
    }

    /**
     * La projection (puis la sauvegarde) d'un tel film.
     */
    public static void main(String[] args) {

        TextFileInterpreter film = new TextFileInterpreter("fou.txt");
        //Films.projeter(film);
        //film.rembobiner();
        try {
            Films.sauvegarder(film, "fouSave.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier 'fou.txt' n'a pas pu être créé.");
        }
    }
}
