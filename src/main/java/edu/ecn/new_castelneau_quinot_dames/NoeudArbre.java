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
    /** La prise faite avant d'arriver en ce noeud. */
    private Pion dernierePrise;
    
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
     * @param dP La derniere prise
     */
    public NoeudArbre(Pion p, NoeudArbre parent, Pion dP) {
        pion = p;
        this.parent = parent;
        dernierePrise = dP;
    }
    
    /**
     * Constructeur de NoeudArbre.
     * @param p Le pion correspondant au noeud
     * @param parent Le noeud parent
     * @param feuilles Les noeuds feuilles
     * @param dP La derniere prise
     */
    public NoeudArbre(Pion p, NoeudArbre parent, LinkedList<NoeudArbre> feuilles, Pion dP) {
        pion = p;
        this.parent = parent;
        this.feuilles = feuilles;
        dernierePrise = dP;
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
    
    public Pion getDernierePrise() {
        return dernierePrise;
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
    
    public void setDernierePrise(Pion dP) {
        dernierePrise = dP;
    }
}
