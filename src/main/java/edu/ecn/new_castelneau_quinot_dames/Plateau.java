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
            root = elargirDame(root);
        }
        else {
            root = elargir(root);
        }
        return root;
    }
    
    /**
     * Trouve les feuilles possibles d'un arbre de recherche de cibles.
     * @param root Le noeud actuel.
     * @return le noeud elargi.
     */
    public NoeudArbre elargir(NoeudArbre root) {
        Pion pionRoot = root.getPion();
        boolean coulRoot = pionRoot.getEstBlanc();
        boolean aUnParent = false;
        Pion pionParent;
        int xParent = 0;
        int yParent = 0;
        if (root.getParent() != null) {
            aUnParent = true;
            pionParent = root.getParent().getPion();
            xParent = pionParent.getPositionX();
            yParent = pionParent.getPositionY();
        }
        int xRoot = pionRoot.getPositionX();
        int yRoot = pionRoot.getPositionY();
        LinkedList<Pion> pions = new LinkedList();
        LinkedList<Pion> prises = new LinkedList();
        for (int pasX = 1; pasX > -2; pasX -= 2) {
            for (int pasY = 1; pasY > -2; pasY -= 2) {
                int xSuiv = xRoot + 2*pasX;
                int ySuiv = yRoot + 2*pasY;
                int xPrise = xSuiv - pasX;
                int yPrise = ySuiv - pasY;
                if ((!aUnParent || xSuiv != xParent || ySuiv != yParent)
                        && xSuiv < 10 && xSuiv > -1 && ySuiv < 10 && ySuiv > -1) {
                    if (plateau.get(xSuiv).get(ySuiv) == null
                            && plateau.get(xPrise).get(yPrise) != null) {
                        if (coulRoot != plateau.get(xPrise).get(yPrise).getEstBlanc()) {
                            pions.add(new Pion(coulRoot, xSuiv, ySuiv));
                            prises.add(new Pion(!coulRoot, xPrise, yPrise));
                        }
                    }
                }
            }
        }
        LinkedList<NoeudArbre> feuilles = new LinkedList();
        for (int i = 0; i < pions.size(); i++) {
            NoeudArbre f = new NoeudArbre(pions.get(i), root, prises.get(i));
            f = elargir(f);
            feuilles.add(f);
        }
        root.setFeuilles(feuilles);
        return root;
    }
    
    /**
     * Trouve les feuilles possibles d'un arbre de recherche de cibles, pour une dame.
     * @param root Le noeud actuel
     * @return Le noeud elargi
     */
    public NoeudArbre elargirDame(NoeudArbre root) {
        Pion pionRoot = root.getPion();
        boolean coulRoot = pionRoot.getEstBlanc();
        boolean aUnParent = false;
        Pion pionParent;
        int xParent = 0;
        int yParent = 0;
        if (root.getParent() != null) {
            aUnParent = true;
            pionParent = root.getParent().getPion();
            xParent = pionParent.getPositionX();
            yParent = pionParent.getPositionY();
        }
        int xRoot = pionRoot.getPositionX();
        int yRoot = pionRoot.getPositionY();
        LinkedList<Pion> pions = new LinkedList();
        LinkedList<Pion> prises = new LinkedList();
        for (int pasX = 1; pasX > -2; pasX -= 2) {
            for (int pasY = 1; pasY > -2; pasY -= 2) {
                for (int i = 2; i < 9; i++) {
                    int xSuiv = xRoot + i*pasX;
                    int ySuiv = yRoot + i*pasY;
                    int xPrise = xSuiv - pasX;
                    int yPrise = ySuiv - pasY;
                    if ((!aUnParent || xSuiv != xParent || ySuiv != yParent)
                        && xSuiv < 10 && xSuiv > -1 && ySuiv < 10 && ySuiv > -1) {
                        if (xSuiv != xParent || ySuiv != yParent && plateau.get(xSuiv).get(ySuiv) == null
                                && plateau.get(xPrise).get(yPrise) != null) {
                            if (coulRoot != plateau.get(xPrise).get(yPrise).getEstBlanc()) {
                                pions.add(new Pion(coulRoot, xSuiv, ySuiv));
                                prises.add(new Pion(!coulRoot, xPrise, yPrise));
                            }
                        }
                    }
                }
            }
        }
        LinkedList<NoeudArbre> feuilles = new LinkedList();
        for (int i = 0; i < pions.size(); i++) {
            NoeudArbre f = new NoeudArbre(pions.get(i), root, prises.get(i));
            f = elargirDame(f);
            feuilles.add(f);
        }
        root.setFeuilles(feuilles);
        return root;
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
     * @param rt Les donnees de l'arbre de recherche de cibles optimal
     */
    public void prendre(Pion p, ReturnType rt) {
        deplacer(p, rt.posFinale[0], rt.posFinale[1]);
        for (Pion prise : rt.prises) {
            System.out.println("prise :" + prise.getPositionX() + " " + prise.getPositionY());
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
        
        int indexPrisesMax = 0;
        for (int i = 0; i < feuilles.size(); i++) {
            ReturnType newRt = chercheCible(feuilles.get(i));
            if (newRt.nbPrises >= rt.nbPrises) {
                indexPrisesMax = i;
                rt.nbPrises = newRt.nbPrises;
                rt.posFinale = newRt.posFinale;
                rt.prises = newRt.prises;
            }
        }
        rt.prises.add(feuilles.get(indexPrisesMax).getDernierePrise());
        rt.nbPrises += 1;
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
        if (x == 9) {
            p.setEstDame(true);
        }
        LinkedList<Pion> ligne = plateau.get(x);
        ligne.set(y, new Pion(p));
        plateau.set(x, ligne);
    }
    
    /**
     * Permet au joueur dont c'est le tour de jouer le pion p
     * @param p Le pion a jouer
     * @return True si le pion a pu etre joue, false sinon
     */
    private boolean jouer(Pion p) {
        Scanner scan = new Scanner(System.in);
        int[] dirPossible = new int[4];
        int x = p.getPositionX();
        int y = p.getPositionY();
        int dirVert;
        if (p.getEstBlanc()) {
            dirVert = -1;
        }
        else {
            dirVert = 1;
        }
        if (x + dirVert < 10 && x + dirVert > -1) {
            if (y + 1 < 10) {
                if (plateau.get(x + dirVert).get(y + 1) == null) {
                    dirPossible[0] = 1;
                }
            }
            if (y - 1 > -1) {
                if (plateau.get(x + dirVert).get(y - 1) == null) {
                    dirPossible[1] = 1;
                }
            }
        }
        if (p.getEstDame() && x - dirVert < 10 && x - dirVert > -1) {
            if (plateau.get(x - dirVert).get(y + 1) == null) {
                dirPossible[2] = 1;
            }
            if (plateau.get(x - dirVert).get(y - 1) == null) {
                dirPossible[3] = 1;
            }
        }
        if (dirPossible[0] == 0 && dirPossible[1] == 0 && dirPossible[2] == 0 && dirPossible[3] == 0) {
            System.out.println("Ce pion ne peut pas etre deplace !");
            return false;
        }
        System.out.println("Voulez-vous deplacer le pion :");
        if (dirPossible[0] == 1) {
            System.out.println("En avant a droite ? (AD)");
        }
        if (dirPossible[1] == 1) {
            System.out.println("En avant a gauche ? (AG)");
        }
        if (dirPossible[2] == 1) {
            System.out.println("En arriere a droite ? (RD)");
        }
        if (dirPossible[3] == 1) {
            System.out.println("En arriere a gauche ? (RG)");
        }
        String dir = scan.next();
        int deplX;
        int deplY;
        switch (dir) {
            case "AD":
                deplX = dirVert;
                deplY = 1;
                break;
            case "AG":
                deplX = dirVert;
                deplY = -1;
                break;
            case "RD":
                deplX = -dirVert;
                deplY = 1;
                break;
            case "RG":
                deplX = -dirVert;
                deplY = -1;
                break;
            default:
                deplX = 0;
                deplY = 0;
                break;
        }
        int i = 1;
        if (p.getEstDame()) {
            boolean deplacementPossible = true;
            while (deplacementPossible) {
                i++;
                deplacementPossible = (plateau.get(x + i * deplX).get(y + deplY * i) == null);
            }
            System.out.println("De combien de cases le deplacer ?");
            int j = Integer.parseInt(scan.next());
            while (j >= i) {
                System.out.println("Deplacement impossible. De combien de cases le deplacer ?");
                j = Integer.parseInt(scan.next());
            }
            i = j;
        }
        deplacer(p, x + i * deplX, y + i * deplY);
        return true;
    }
    
    /**
     * Permet au joueur dont c'est le tour de le jouer
     * @param couleur Couleur du joueur dont c'est le tour
     * @return True si le joueur est encore en lice, false s'il a perdu la partie
     */
    public boolean tourDeJeu(boolean couleur) {
        System.out.println("Tour du joueur ");
        if (couleur) {
            System.out.print("blanc :");
        }
        else {
            System.out.print("noir :");
        }
        
        affichePlateau();
        LinkedList<Pion> pionsJouables = new LinkedList();
        LinkedList<NoeudArbre> arbres = new LinkedList();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Pion pion = plateau.get(x).get(y);
                if (pion != null) {
                    if (pion.getEstBlanc() == couleur) {
                        pionsJouables.add(pion);
                        arbres.add(creerArbre(pion));
                    }
                }
            }
        }
        if (pionsJouables.isEmpty()) {
            return false;
        }
        LinkedList<ReturnType> bestRts = new LinkedList();
        bestRts.add(chercheCible(arbres.get(0)));
        LinkedList<Pion> bestPions = new LinkedList();
        bestPions.add(pionsJouables.get(0));
        for (int i = 1; i < arbres.size(); i++) {
            ReturnType rt = chercheCible(arbres.get(i));
            if (rt.nbPrises > bestRts.get(0).nbPrises) {
                bestRts.clear();
                bestRts.add(rt);
                bestPions.clear();
                bestPions.add(pionsJouables.get(i));
            }
            else if (rt.nbPrises == bestRts.get(0).nbPrises) {
                bestRts.add(rt);
                bestPions.add(pionsJouables.get(i));
            }
        }
        if (bestRts.get(0).nbPrises == 0) {
            Pion p = choixPion(couleur);
            while (!jouer(p)) {
                p = choixPion(couleur);
            }
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Choisissez le pion a jouer parmi :");
            for (int i = 0; i < bestPions.size(); i++) {
                System.out.println(i + " : (" + bestPions.get(i).getPositionX() + ", "
                        + bestPions.get(i).getPositionY() + ")");
            }
            int i = Integer.parseInt(scan.next());
            prendre(bestPions.get(i), bestRts.get(i));
            System.out.println("Votre pion a pris des pieces !");
        }
        return true;
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
