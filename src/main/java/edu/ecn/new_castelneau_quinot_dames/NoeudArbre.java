/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;
import java.util.LinkedList;

/**
 *
 * @author eugen
 */
public class NoeudArbre {
    private Pion pion;
    private NoeudArbre parent;
    private LinkedList<NoeudArbre> feuilles;
    
    public NoeudArbre() {
        feuilles = new LinkedList();
    }
    
    public NoeudArbre(Pion p) {
        pion = p;
        feuilles = new LinkedList();
    }
    
    public NoeudArbre(Pion p, NoeudArbre parent) {
        pion = p;
        this.parent = parent;
    }
    
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
