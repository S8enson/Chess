/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Pieces;

import mvc.Colour;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam
 */
public class PawnTest {
    
    private Pawn pawn;
    
    public PawnTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    

    
    @Before
    public void setUp() {
        pawn = new Pawn(0, 1, Colour.WHITE);
    }
    
    @After
    public void tearDown() {
        pawn = null;
    }

    /**
     * Test of validMove method, of class Pawn.
     */
    @Test
    public void testValidMove() {
        
        // 1 space
        int newX = 0;
        int newY = 2;
        boolean expResult = true;
        boolean result = pawn.validMove(newX, newY);
        assertEquals(expResult, result);
        // 2 spaces
        newX = 0;
        newY = 3;
        expResult = true;
        result = pawn.validMove(newX, newY);
        assertEquals(expResult, result);
        // illegal 2 space
        pawn.y = 3;
        newX = 0;
        newY = 5;
        expResult = false;
        result = pawn.validMove(newX, newY);
        assertEquals(expResult, result);
        // illegal horizontal
        newX = 1;
        newY = 3;
        expResult = false;
        result = pawn.validMove(newX, newY);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Pawn.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        Type expResult = Type.PAWN;
        Type result = pawn.getType();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColour method, of class Pawn.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        
        Colour expResult = Colour.WHITE;
        Colour result = pawn.getColour();
        assertEquals(expResult, result);
        
    }



    /**
     * Test of validTake method, of class Pawn.
     */
    @Test
    public void testValidTake() {
        System.out.println("validTake");
        int newX = 1;
        int newY = 2;
        boolean expResult = true;
        boolean result = pawn.validTake(newX, newY);
        assertEquals(expResult, result);
        
        newX = 2;
        newY = 3;
        expResult = false;
        result = pawn.validTake(newX, newY);
        assertEquals(expResult, result);
        
    }
    
}
