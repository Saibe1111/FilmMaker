package fr.cuvellier.film_maker.film.optional;

import fr.cuvellier.film_maker.film.Film;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Sebastien CUVELLIER
 * @version 2.1 - 06/04/2020
 */
public class TextFileInterpreter implements Film {
    private ArrayList<String[]> images;
    private int hauteur;
    private int largeur;
    private String nom;
    private int marge;


    /**
     * Constructeur permetant d'initialiser le nom et la marge.
     * @param nom nom du fichier à ouvrir
     * @param marge marge si ligne sauté après chaque image.
     */
    public TextFileInterpreter(String nom, int marge) {
        this(nom);
        this.marge = marge;
    }

    /**
     * Constructeur permetant d'initialiser le nom.
     * @param nom nom du fichier à ouvrir
     */
    public TextFileInterpreter(String nom) {
        this.marge = 0;
        this.images = new ArrayList<>();
        this.nom = nom;
        try {
            lire(nom);
        } catch (FileNotFoundException e) {
            System.out.println("le fichier est introuvable");
        }
    }

    /**
     * @see Film#hauteur()
     */
    @Override
    public int hauteur() {
        return this.hauteur;
    }

    /**
     * @see Film#largeur()
     */
    @Override
    public int largeur() {
        return this.largeur;
    }

    /**
     * @see Film#suivante(char[][])
     */
    @Override
    public boolean suivante(char[][] écran) {
        if (images.isEmpty())
            return false;
        for (int i = 0; i < this.images.get(0).length - this.marge; ++i) {
            char[] chars = this.images.get(0)[i].toCharArray();
            écran[i] = chars;
        }
        this.images.remove(0);
        return true;
    }

    /**
     * @see Film#rembobiner()
     */
    @Override
    public void rembobiner() {
        try {
            lire(nom);
        } catch (FileNotFoundException e) {
            System.out.println("le fichier est introuvable");
        }
    }

    /**
     * Permet de lire un fichier qui respecte les règles du format
     *
     * @param nom Nom du fichier a lire
     * @throws FileNotFoundException Permet permet de dire si le fichier est introuvable
     */
    private void lire(String nom) throws FileNotFoundException {
        int cpt = 0;
        Scanner in = new Scanner(new FileInputStream(nom));
        Scanner ligne = new Scanner(in.nextLine());
        this.largeur = Integer.parseInt(ligne.next());
        this.hauteur = Integer.parseInt(ligne.next());
        this.ajoutImage();
        ligne.close();
        while (in.hasNextLine()) {
            String ligneSuivante = in.nextLine();
            if (ligneSuivante.equals("\\newframe")) {
                this.ajoutImage();
                cpt = 0;
            } else {
                this.images.get(this.images.size() - 1)[cpt] = ligneSuivante;
                ++cpt;
            }
        }
        in.close();
        this.ImageDeFinVide();
    }

    /**
     * Permet d'ajouter la place pour une image dans la liste des images
     */
    private void ajoutImage() {
        this.images.add(new String[this.hauteur + this.marge]);
        Arrays.fill(images.get(this.images.size() - 1), " ");
    }

    /**
     * Permet de regarder si la dernière image est vide pour la supprimer pour éviter une image vide de transition en cas d'assemblage
     */
    private void ImageDeFinVide() {
        int cpt = 0;
        for (int i = 0; i < this.hauteur + this.marge; ++i)
            if (this.images.get(this.images.size() - 1)[i].equals(" "))
                cpt++;
        if (cpt == this.hauteur + this.marge)
            this.images.remove(this.images.size() - 1);
    }

}
