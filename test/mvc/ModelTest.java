/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import chess.Pieces.*;
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
public class ModelTest {
    
    Model model;
    
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        model = new Model();
        model.whiteTurn = false;
    }
    
    @After
    public void tearDown() {
        model = null;
    }



    /**
     * Test of moveValid method, of class Model.
     */
    @Test
    public void testMoveValid() {
        System.out.println("moveValid");
        //legal
        int initRow = 0;
        int initCol = 0;
        int finalRow = 0;
        int finalCol = 0;
        boolean checking = false;
        boolean expResult = true;
        boolean result = model.moveValid(initRow, initCol, finalRow, finalCol, checking);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isChecked method, of class Model.
     */
    @Test
    public void testIsChecked() {
        System.out.println("isChecked");
        //fasle
        boolean expResult = false;
        boolean result = model.isChecked();
        assertEquals(expResult, result);
        // true
        model.board.squares[0][3] = new Space(3, 0, null);
        model.board.squares[6][5] = new Space(5, 6, new Queen(5, 6, Colour.WHITE));
        model.board.squares[0][5] = new Space(5, 0, null);
        model.board.squares[3][2] = new Space(2, 3, new Bishop(2, 3, Colour.WHITE));
        model.board.squares[1][4] = new Space(4, 1, null);
        model.board.squares[3][4] = new Space(4, 3, new Pawn(4, 3, Colour.WHITE));
        
        model.board.squares[7][1] = new Space(1, 7, null);
        model.board.squares[5][2] = new Space(2, 5, new Knight(2, 5, Colour.BLACK));
        model.board.squares[7][6] = new Space(6, 7, null);
        model.board.squares[5][5] = new Space(5, 5, new Knight(5, 5, Colour.BLACK));
        model.board.squares[6][4] = new Space(4, 6, null);
        model.board.squares[4][4] = new Space(4, 4, new Pawn(4, 4, Colour.BLACK));
        expResult = true;
        result = model.isChecked();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMate method, of class Model.
     */
    @Test
    public void testCheckMate() {
        System.out.println("checkMate");
        //fasle
        boolean expResult = false;
        boolean result = model.checkMate();
        assertEquals(expResult, result);
        // true
        model.board.squares[0][3] = new Space(3, 0, null);
        model.board.squares[6][5] = new Space(5, 6, new Queen(5, 6, Colour.WHITE));
        model.board.squares[0][5] = new Space(5, 0, null);
        model.board.squares[3][2] = new Space(2, 3, new Bishop(2, 3, Colour.WHITE));
        model.board.squares[1][4] = new Space(4, 1, null);
        model.board.squares[3][4] = new Space(4, 3, new Pawn(4, 3, Colour.WHITE));
        
        model.board.squares[7][1] = new Space(1, 7, null);
        model.board.squares[5][2] = new Space(2, 5, new Knight(2, 5, Colour.BLACK));
        model.board.squares[7][6] = new Space(6, 7, null);
        model.board.squares[5][5] = new Space(5, 5, new Knight(5, 5, Colour.BLACK));
        model.board.squares[6][4] = new Space(4, 6, null);
        model.board.squares[4][4] = new Space(4, 4, new Pawn(4, 4, Colour.BLACK));
        expResult = true;
        result = model.checkMate();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of findKing method, of class Model.
     */
    @Test
    public void testFindKing() {
        System.out.println("findKing");
        Colour colour = Colour.WHITE;
        int[] expResult = new int[2];
        expResult[0] = 0;
        expResult[1] = 4;
        int[] result = model.findKing(colour);
        assertArrayEquals(expResult, result);
        colour = Colour.BLACK;
        expResult[0] = 7;
        result = model.findKing(colour);
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of checkPath method, of class Model.
     */
    @Test
    public void testCheckPath() {
        System.out.println("checkPath");
        boolean take = false;
        int initRow = 0;
        int initCol = 0;
        int finalRow = 0;
        int finalCol = 0;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.checkPath(take, initRow, initCol, finalRow, finalCol);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}
