/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;

/**
 * L'objet Pion du plateau de jeu
 * @author erwan
 */
public class Pion {
    /* La couleur du Pion (true s'il est blanc, false sinon). */
    private boolean estBlanc;
    /* Le statut de dame du Pion (true s'il en est une). */
    private boolean estDame;
    /* La position en abscisse du Pion. */
    private int positionX;
    /* La position en ordonnee du Pion. */
    private int positionY;
    
    /**
     * Constructeur d'un Pion.
     * @param estBlanc La couleur du Pion (true si le Pion est blanc).
     * @param x La position en abscisse du Pion.
     * @param y La position en ordonnee du Pion.
     */
    public Pion(boolean estBlanc, int x, int y) {
        this.estBlanc = estBlanc;
        this.estDame = false;
        this.positionX = x;
        this.positionY = y;
    }
    
    /**
     * Constructeur d'un pion par copie d'un autre Pion.
     * @param pion Le Pion a copier.
     */
    public Pion(Pion pion) {
        this.estBlanc = pion.getEstBlanc();
        this.estDame = pion.getEstDame();
        this.positionX = pion.getPositionX();
        this.positionY = pion.getPositionY();
    }
    
    /**
     * Constructeur par defaut d'un Pion.
     */
    public Pion() {
        this.estBlanc = false;
        this.estDame = false;
        this.positionX = 0;
        this.positionY = 1;
    }
    
    /**
     * Deplace le Pion.
     * @param x La nouvelle position du Pion en x.
     * @param y La nouvelle position du Pion en y.
     */
    public void deplacer(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    /**
     * Transforme le Pion en dame.
     */
    public void devientDame() {
        this.estDame = true;
    }

    /**
     * Renvoie si le Pion est blanc.
     * @return isDame 
     */
    public boolean getEstBlanc() {
        return estBlanc;
    }
    
    /**
     * Attributeur de la couleur du Pion.
     * @param estBlanc 
     */
    public void setEstBlanc(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    /**
     * Renvoie si le Pion est une dame.
     * @return isDame 
     */
    public boolean getEstDame() {
        return estDame;
    }

    /**
     * Attributeur du statut de dame au Pion.
     * @param estDame 
     */
    public void setEstDame(boolean estDame) {
        this.estDame = estDame;
    }
    
    /**
     * Accesseur de l'abscisse du Pion.
     * @return L'abscisse du Pion.
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Attributeur de l'abscisse du Pion.
     * @param positionX La nouvelle abscisse du Pion.
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Accesseur de l'ordonnee du Pion.
     * @return L'ordonnee du Pion.
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Attributeur de l'ordonnee du Pion.
     * @param positionY La nouvelle ordonnee du Pion.
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }  
}
