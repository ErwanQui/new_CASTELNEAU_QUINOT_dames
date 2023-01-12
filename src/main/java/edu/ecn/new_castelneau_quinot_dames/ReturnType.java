/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;
import java.util.LinkedList;

/**
 * Classe permettant de renvoyer les donnees utiles a la creation d'arbres de prises.
 * @author eugen
 */
public class ReturnType {
    /** Le nombre de prises. */
    public int nbPrises;
    /** La position du pion apres les prises. */
    public int[] posFinale;
    /** Liste des prises. */
    public LinkedList<Pion> prises;
    
    /** Constructeur de ReturnType. */
    public ReturnType() {
        nbPrises = 0;
        posFinale = new int[2];
        prises = new LinkedList();
    }
    
    /**
     * Constructeur de ReturnType.
     * @param n Le nombre de prises
     * @param pos La position apres la prise du pion
     * @param prises La liste des prises
     */
    public ReturnType(int n, int[] pos, LinkedList<Pion> prises) {
        nbPrises = n;
        posFinale = pos;
        this.prises = prises;
    }
}
