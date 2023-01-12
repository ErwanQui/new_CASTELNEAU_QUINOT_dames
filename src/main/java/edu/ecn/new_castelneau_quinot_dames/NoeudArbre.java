/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;
import java.util.LinkedList;

/**
 * Classe representant les noeuds d'un arbre de prises
 * @author eugen
 */
public class NoeudArbre {
    /** Le pion correspondant au noeud. */
    private Pion pion;
    /** Le noeud parent de ce noeud. */
    private NoeudArbre parent;
    /** Les noeuds feuilles de ce noeud. */
    private LinkedList<NoeudArbre> feuilles;
    
    /** Constructeur de NoeudArbre. */
    public NoeudArbre() {
        feuilles = new LinkedList();
    }
    
    /**
     * Constructeur de NoeudArbre.
     * @param p Le pion correspondant au noeud
     */
    public NoeudArbre(Pion p) {
        pion = p;
        feuilles = new LinkedList();
    }
    
    /**
     * Constructeur de NoeudArbre.
     * @param p Le pion correspondant au noeud
     * @param parent Le noeud parent
     */
    public NoeudArbre(Pion p, NoeudArbre parent) {
        pion = p;
        this.parent = parent;
    }
    
    /**
     * Constructeur de NoeudArbre.
     * @param p Le pion correspondant au noeud
     * @param parent Le noeud parent
     * @param feuilles Les noeuds feuilles
     */
    public NoeudArbre(Pion p, NoeudArbre parent, LinkedList<NoeudArbre> feuilles) {
        pion = p;
        this.parent = parent;
        this.feuilles = feuilles;
    }
    
    public Pion getPion() {
        return pion;
    }
    
    public NoeudArbre getParent() {
        return parent;
    }
    
    public LinkedList<NoeudArbre> getFeuilles() {
        return feuilles;
    }
    
    public void setPion(Pion p) {
        pion = p;
    }
    
    public void setParent(NoeudArbre nA) {
        parent = nA;
    }
    
    public void setFeuilles(LinkedList<NoeudArbre> feuilles) {
        this.feuilles = feuilles;
    }
}
