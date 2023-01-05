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
public class ReturnType {
    public int nbPrises;
    public int[] posFinale;
    public LinkedList<Pion> prises;
    
    public ReturnType() {
        nbPrises = 0;
        posFinale = new int[2];
        prises = new LinkedList();
    }
    
    public ReturnType(int n, int[] pos, LinkedList<Pion> prises) {
        nbPrises = n;
        posFinale = pos;
        this.prises = prises;
    }
}
