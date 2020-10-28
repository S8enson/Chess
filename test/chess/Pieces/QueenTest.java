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
public class QueenTest {
    
    private Queen queen;
    
    public QueenTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    

    
    @Before
    public void setUp() {
        queen = new Queen(3, 0, Colour.WHITE);
    }
    
    @After
    public void tearDown() {
        queen = null;
    }

    /**
     * Test of validMove method, of class Queen.
     */
    @Test
    public void testValidMove() {
        
        // straight
        int newX = 3;
        int newY = 3;
        boolean expResult = true;
        boolean result = queen.validMove(newX, newY);
        assertEquals(expResult, result);
        // diagonal
        newX = 0;
        newY = 3;
        expResult = true;
        result = queen.validMove(newX, newY);
        assertEquals(expResult, result);
        // illegal
        newX = 7;
        newY = 2;
        expResult = false;
        result = queen.validMove(newX, newY);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Queen.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        Type expResult = Type.QUEEN;
        Type result = queen.getType();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColour method, of class Queen.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        
        Colour expResult = Colour.WHITE;
        Colour result = queen.getColour();
        assertEquals(expResult, result);
        
    }




    
}
