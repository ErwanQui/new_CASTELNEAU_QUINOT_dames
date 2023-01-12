/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author eugene
 */
public class Plateau {
    // Taille du plateau
    public static final int TAILLE_PLATEAU = 10;
    // Plateau de jeu, contenant les pions
    private LinkedList<LinkedList<Pion>> plateau;
    
    /** Constructeur de Plateau */
    public Plateau() {
        boolean couleur = false;
        plateau = new LinkedList<>();
        for (int x = 0; x < TAILLE_PLATEAU; x++) {
            LinkedList<Pion> ligne = new LinkedList<>();
            if (x > 5) { couleur = true; }
            for (int y = 0; y < TAILLE_PLATEAU; y++) {
                if (x > 3 && x < 6) { ligne.add(null); }
                else {
                    switch ((x + y)%2) {
                        case 0:
                        case 2:
                            ligne.add(null);
                            break;
                        case 1:
                            ligne.add(new Pion(couleur, x, y));
                            break;
                        default:
                            ligne.add(null);
                            break;
                    }
                }
            }
            plateau.add(ligne);
        }
    }
    
    /**
     * Demande au joueur dont c'est le tour de choisir un pion valide à déplacer
     * @param couleur Couleur du joueur dont c'est le tour
     * @return Position (x, y) du pion valide choisi
     */
    public Pion choixPion(boolean couleur) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Entrez les coordonnées du pion à déplacer (x puis y) :");
        int x = Integer.parseInt(scan.next());
        int y = Integer.parseInt(scan.next());
        Pion choix = plateau.get(x).get(y);
        while (choix == null || choix.getEstBlanc() != couleur) {
            if (choix == null) {
                System.out.println("Aucun pion à ces coordonnées. Entrez-en une nouvelle :");
            }
            else {
                System.out.println("Ce pion ne vous appartient pas. Entrez de nouvelles coordonnées :");
            }
            x = Integer.parseInt(scan.next());
            y = Integer.parseInt(scan.next());
            choix = plateau.get(x).get(y);
        }
        return choix;
    }
    
    /**
     * Initialisation de la recherche du meilleur chemin de prises (crée la root et l'elargi).
     * @param p Le pion dont on cherche les cibles
     * @return Le noeud initial de l'arbre des prises possibles
     */
    public NoeudArbre creerArbre(Pion p) {
        NoeudArbre root = new NoeudArbre(p);
        if (p.getEstDame()) {
            elargirDame(root);
        }
        else {
            elargir(root);
        }
        return root;
    }
    
    /**
     * Trouve les feuilles possibles d'un arbre de recherche de cibles.
     * @param root Le noeud actuel.
     */
    public void elargir(NoeudArbre root) {
        Pion pionRoot = root.getPion();
        boolean coulRoot = pionRoot.getEstBlanc();
        Pion pionParent = root.getParent().getPion();
        int xRoot = pionRoot.getPositionX();
        int yRoot = pionRoot.getPositionY();
        int xParent = pionParent.getPositionX();
        int yParent = pionParent.getPositionY();
        LinkedList<Pion> pions = new LinkedList();
        for (int pasX = 1; pasX > -2; pasX -= 2) {
            for (int pasY = 1; pasY > -2; pasY -= 2) {
                int xSuiv = xRoot + 2*pasX;
                int ySuiv = yRoot + 2*pasY;
                if (xSuiv != xParent || ySuiv != yParent && plateau.get(xSuiv).get(ySuiv) == null
                        && plateau.get(xSuiv - pasX).get(ySuiv - pasY) != null) {
                    if (coulRoot != plateau.get(xSuiv - pasX).get(ySuiv - pasY).getEstBlanc()) {
                        Pion p = new Pion(coulRoot, xSuiv, ySuiv);
                        pions.add(p);
                    }
                }
            }
        }
        LinkedList<NoeudArbre> feuilles = new LinkedList();
        for (Pion p : pions) {
            NoeudArbre f = new NoeudArbre(p, root);
            feuilles.add(f);
            elargir(f);
        }
        root.setFeuilles(feuilles);
    }
    
    /**
     * Trouve les feuilles possibles d'un arbre de recherche de cibles, pour une dame.
     * @param root Le noeud actuel.
     */
    public void elargirDame(NoeudArbre root) {
        Pion pionRoot = root.getPion();
        boolean coulRoot = pionRoot.getEstBlanc();
        Pion pionParent = root.getParent().getPion();
        int xRoot = pionRoot.getPositionX();
        int yRoot = pionRoot.getPositionY();
        int xParent = pionParent.getPositionX();
        int yParent = pionParent.getPositionY();
        LinkedList<Pion> pions = new LinkedList();
        for (int pasX = 1; pasX > -2; pasX -= 2) {
            for (int pasY = 1; pasY > -2; pasY -= 2) {
                for (int i = 2; i < 9; i++) {
                    int xSuiv = xRoot + i*pasX;
                    int ySuiv = yRoot + i*pasY;
                    if (xSuiv < 10 && xSuiv > -1 && ySuiv < 10 && ySuiv > -1) {
                        if (xSuiv != xParent || ySuiv != yParent && plateau.get(xSuiv).get(ySuiv) == null
                                && plateau.get(xSuiv - pasX).get(ySuiv - pasY) != null) {
                            if (coulRoot != plateau.get(xSuiv - pasX).get(ySuiv - pasY).getEstBlanc()) {
                                Pion p = new Pion(coulRoot, xSuiv, ySuiv);
                                pions.add(p);
                            }
                        }
                    }
                }
            }
        }
        LinkedList<NoeudArbre> feuilles = new LinkedList();
        for (Pion p : pions) {
            NoeudArbre f = new NoeudArbre(p, root);
            feuilles.add(f);
            elargirDame(f);
        }
        root.setFeuilles(feuilles);
    }
    
    /**
     * Retire un pion p qui a ete pris.
     * @param p Le pion a retirer
     */
    public void retirer(Pion p) {
        LinkedList<Pion> ligne = plateau.get(p.getPositionX());
        ligne.set(p.getPositionY(), null);
        plateau.set(p.getPositionX(), ligne);
    }
    
    /**
     * Cherche les pionts que p doit prendre, les prends et deplace p.
     * @param p Le pion qui prend
     * @param root Le noeud initial de l'arbre de recherche de cibles
     */
    public void prendre(Pion p, NoeudArbre root) {
        ReturnType rt = chercheCible(root);
        if (rt.posFinale[0] != p.getPositionX() || rt.posFinale[1] !=)
        deplacer(p, rt.posFinale[0], rt.posFinale[1]);
        for (Pion prise : rt.prises) {
            retirer(prise);
        }
    }
    
    /**
     * Cherche le chemin de pions a prendre permettant d'en prendre le plus
     * @param root le noeud initial de l'arbre de recherche correspondant au pion qui prend
     * @return la position finale du pion, le nombre de prises et la liste de ces prises
     */
    public ReturnType chercheCible(NoeudArbre root) {
        LinkedList<NoeudArbre> feuilles = root.getFeuilles();
        ReturnType rt = new ReturnType();
        
        if (feuilles.isEmpty()) {
            int x = root.getPion().getPositionX();
            int y = root.getPion().getPositionY();
            rt.posFinale[0] = x;
            rt.posFinale[1] = y;
            return rt;
        }
        
        int nbPrisesMax = 0;
        int indexPrisesMax = 0;
        for (int i = 0; i < feuilles.size(); i++) {
            ReturnType newRt = chercheCible(feuilles.get(i));
            if (newRt.nbPrises > nbPrisesMax) {
                indexPrisesMax = i;
                nbPrisesMax = newRt.nbPrises;
                rt.posFinale = newRt.posFinale;
                rt.prises = newRt.prises;
            }
        }
        rt.prises.add(feuilles.get(indexPrisesMax).getPion());
        rt.nbPrises = nbPrisesMax;
        return rt;
    }
    
    /**
     * DEplace le pion p en (x, y).
     * @param p Le pion a deplacer
     * @param x
     * @param y 
     */
    public void deplacer(Pion p, int x, int y) {
        retirer(p);
        p.deplacer(x, y);
        LinkedList<Pion> ligne = plateau.get(x);
        ligne.set(y, p);
        plateau.set(x, ligne);
    }
    
    /**
     * Permet au joueur dont c'est le tour de jouer le pion p
     * @param p Le pion a jouer
     */
    private void jouer(Pion p) {
        NoeudArbre root = creerArbre(p);
        boolean aPris = prendre(p, root);
    }
    
    /**
     * Permet au joueur dont c'est le tour de le jouer
     * @param couleur Couleur du joueur dont c'est le tour
     */
    public void tourDeJeu(boolean couleur) {
        System.out.println("Tour du joueur ");
        if (couleur) { System.out.print("blanc :"); }
        else { System.out.print("noir :"); }
        
        affichePlateau();
        Pion p = choixPion(couleur);
        jouer(p);
    }
    
    public void affichePlateau() {
        System.out.println();
        System.out.println("  0 1 2 3 4 5 6 7 8 9 y");
        for (int i = 0; i < TAILLE_PLATEAU; i++) {
            System.out.print(i);
            for (int j = 0; j < TAILLE_PLATEAU; j++) {
                if (plateau.get(i).get(j) == null) {
                    System.out.print(" -");
                }
                else {
                    Pion currentPion = (Pion)plateau.get(i).get(j);
                    if (currentPion.getEstBlanc() == true) {
                        System.out.print(" B");
                    }
                    else {
                        System.out.print(" N");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("x");
    }
    
    public LinkedList<LinkedList<Pion>> getPlateau() {
        return plateau;
    }
    
    public void setPlateau(LinkedList<LinkedList<Pion>> plateau) {
        this.plateau = plateau;
    }
}
