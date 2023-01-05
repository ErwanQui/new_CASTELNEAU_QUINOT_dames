/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

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
    
    public int[] peutPrendreInitial(Pion a, Pion b) {
        int aX = a.getPositionX();
        int aY = a.getPositionY();
        int bX = b.getPositionX();
        int bY = b.getPositionY();
        int difX = bX - aX;
        int difY = bY - aY;
        int[] pos = new int[2];
        if (a.getEstDame() && difX*difX == difY*difY && difX != 0) {
            boolean trajectoireVide = true;
            int[] pas = new int[2];
            if (difX > 0) { pas[0] = 1;  }
            else          { pas[0] = -1; }
            if (difY > 0) { pas[1] = 1;  }
            else          { pas[1] = -1; }
            for (int i = aX; i < bX; i += pas[0]) {
                for (int j = aY; j < bY; j += pas[1]) {
                    if (plateau.get(i).get(j) != null) {
                        trajectoireVide = false;
                    }
                }
            }
            if (trajectoireVide) {
                pos[0] = bX + pas[0];
                pos[1] = bY + pas[1];
                return pos;
            }
        }
        else if (difX*difX == 1 && difY*difY == 1
                && plateau.get(bX + difX).get(bY + difY) == null
                && a.getEstBlanc() != b.getEstBlanc()) {
            pos[0] = bX + difX;
            pos[1] = bY + difY;
            return pos;
        }
        return null;
    }
    
    /**
     * Renvoie les coordonnees en lesquels le pion p peut etre pris, s'il peut l'etre
     */
    public int peutPrendre(boolean coul, int xCour, int yCour, int xPrec, int yPrec, int iter) {
        int[] pasXY = new int[2];
        pasXY[0] = -1;
        pasXY[1] = 1;
        for (int pasX : pasXY) {
            for (int pasY : pasXY) {
                int xSuiv = xCour + 2*pasX;
                int ySuiv = yCour + 2*pasY;
                if (xSuiv != xPrec || ySuiv != yPrec && plateau.get(xSuiv).get(ySuiv) == null
                        && coul != plateau.get(xSuiv - pasX).get(ySuiv - pasY).getEstBlanc()) {
                    int iterCour = peutPrendre(coul, xSuiv, ySuiv, xCour, yCour, iter + 1);
                }
                else {
                    int iterCour = iter;
                }
                
            }
        }
        return(1);
    }
    
    public NoeudArbre creerArbrePrises(Pion p) {
        NoeudArbre root = new NoeudArbre(p);
        elargir(root);
        return root;
    }
    
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
                        && coulRoot != plateau.get(xSuiv - pasX).get(ySuiv - pasY).getEstBlanc()) {
                    pions.add(plateau.get(xSuiv).get(ySuiv));
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
                int xSuiv = xRoot + 2*pasX;
                int ySuiv = yRoot + 2*pasY;
                if (xSuiv != xParent || ySuiv != yParent && plateau.get(xSuiv).get(ySuiv) == null
                        && coulRoot != plateau.get(xSuiv - pasX).get(ySuiv - pasY).getEstBlanc()) {
                    pions.add(plateau.get(xSuiv).get(ySuiv));
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
    
    public void retirer(Pion p) {
        LinkedList<Pion> ligne = plateau.get(p.getPositionX());
        ligne.set(p.getPositionY(), null);
        plateau.set(p.getPositionX(), ligne);
    }
    
    /**
     * Prend le pion p et deplace le pion en (x, y)
     * @param a Le pion qui prend
     * @param b Le pion a prendre (qui peut l'etre)
     * @param x abscisse d'arrivee
     * @param y ordonnee d'arrivee
     */
    public void prendre(Pion a, Pion b, int x, int y) {
        deplacer(a, x, y);
        retirer(b);
        if ((a.getEstBlanc() && y == 0) || (!a.getEstBlanc() && y == 9)) {
            a.devientDame();
        }
        LinkedList<int[]> prisesArrivee = new LinkedList();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
//                int[] pos = peutPrendre(a, plateau.get(x).get(y));
//                if (pos != null) {
//                    prisesArrivee.add(pos);
//                }
            }
        }
//        if (!prises.isEmpty()) {
//            Random r = new Random();
//            int i = r.nextInt(prises.size());
//            int[] pos = prises.get(i);
//            prendre(a, plateau.get(pos[0]).get(pos[1]), )
//        }
    }
    
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