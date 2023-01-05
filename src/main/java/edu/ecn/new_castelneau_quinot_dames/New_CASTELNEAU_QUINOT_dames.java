/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package edu.ecn.new_castelneau_quinot_dames;

/**
 *
 * @author erwan
 */
public class New_CASTELNEAU_QUINOT_dames {

    /**
     * Le main du projet.
     * @param args
     */
    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        boolean estBlanc = true;
        while (true) {
            plateau.tourDeJeu(estBlanc);
            estBlanc = !estBlanc;            
        }
    }
}
