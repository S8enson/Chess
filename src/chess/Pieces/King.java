package chess.Pieces;

import chess.Colour;
import chess.Player;
import chess.Type;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
public class King extends Piece{
    
        Type type;
        public boolean alive;
            
         public King(int x, int y, Colour c){
         super(x, y, c);
         type = Type.KING;
         alive = true;
         }
         
         public King(King copy){
         this(copy.x, copy.y, copy.colour);
         }
         
         // ensures moves only 1 square at a time
             @Override
    public boolean validMove(int newX, int newY) {
        
        int xChange = Math.abs(newX-this.x);
        int yChange = Math.abs(newY-this.y);
        
        return xChange <=1 && yChange <=1;
    }

    @Override
    public Type getType() {
        return type;
    }
    
    
    
        @Override
    public String toString() {
        String pieceString ="";
        if(colour== Colour.BLACK){
            pieceString = "♚";
        }
        else if(colour== Colour.WHITE){
            pieceString = "♔";
        }
        return pieceString;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
    
    
}
