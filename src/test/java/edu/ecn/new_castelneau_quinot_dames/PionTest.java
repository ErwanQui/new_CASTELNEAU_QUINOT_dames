/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package edu.ecn.new_castelneau_quinot_dames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author erwan
 */
public class PionTest {
    
    public PionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of deplacer method, of class Pion.
     */
    @Test
    public void testDeplacer() {
        System.out.println("deplacer");
        Pion instance = new Pion();
        instance.deplacer(5, 8);
        assertEquals(5, instance.getPositionX());
        assertEquals(8, instance.getPositionY());
    }

    /**
     * Test of devientDame method, of class Pion.
     */
    @Test
    public void testDevientDame() {
        System.out.println("devientDame");
        Pion instance = new Pion(false, 0, 0);
        assertFalse(instance.getEstDame());
        instance.setEstDame(true);
        assertTrue(instance.getEstDame());
    }

    /**
     * Test of getEstBlanc method, of class Pion.
     */
    @Test
    public void testGetEstBlanc() {
        System.out.println("getEstBlanc");
        Pion instance = new Pion(false, 0, 0);      
        assertFalse(instance.getEstBlanc());
        Pion instance2 = new Pion(true, 0, 0);      
        assertTrue(instance2.getEstBlanc());
    }

    /**
     * Test of setEstBlanc method, of class Pion.
     */
    @Test
    public void testSetEstBlanc() {
        System.out.println("setEstBlanc");
        Pion instance = new Pion(false, 0, 0);
        assertFalse(instance.getEstBlanc());
        instance.setEstBlanc(true);       
        assertTrue(instance.getEstBlanc());
    }

//    /**
//     * Test of getEstDame method, of class Pion.
//     */
//    @Test
//    public void testGetEstDame() {
//        System.out.println("getEstDame");
//        Pion instance = new Pion();
//        boolean expResult = false;
//        boolean result = instance.getEstDame();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEstDame method, of class Pion.
//     */
//    @Test
//    public void testSetEstDame() {
//        System.out.println("setEstDame");
//        boolean estDame = false;
//        Pion instance = new Pion();
//        instance.setEstDame(estDame);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getPositionX method, of class Pion.
     */
    @Test
    public void testGetPositionX() {
        System.out.println("getPositionX");
        Pion instance = new Pion(true, 2, 8);
        assertEquals(2, instance.getPositionX());
    }

    /**
     * Test of setPositionX method, of class Pion.
     */
    @Test
    public void testSetPositionX() {
        System.out.println("setPositionX");
        Pion instance = new Pion();
        assertEquals(0, instance.getPositionX());
        instance.setPositionX(58);
        assertEquals(58, instance.getPositionX());
    }

//    /**
//     * Test of getPositionY method, of class Pion.
//     */
//    @Test
//    public void testGetPositionY() {
//        System.out.println("getPositionY");
//        Pion instance = new Pion();
//        int expResult = 0;
//        int result = instance.getPositionY();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPositionY method, of class Pion.
//     */
//    @Test
//    public void testSetPositionY() {
//        System.out.println("setPositionY");
//        int positionY = 0;
//        Pion instance = new Pion();
//        instance.setPositionY(positionY);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
