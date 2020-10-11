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
public class Rook extends Piece{
    
        Type type;
            
         public Rook(int x, int y, Colour c){
         super(x, y, c);
         type = Type.ROOK;
         
         }
         
         public Rook(Rook copy){
         
         this(copy.x, copy.y, copy.colour);
         }
         
         //ensures only horizontal or vertical movement
             @Override
    public boolean validMove(int newX, int newY) {
        int xChange = Math.abs(newX-this.x);
        int yChange = Math.abs(newY-this.y);
        
        return xChange == 0 || yChange == 0;
    }

    @Override
    public Type getType() {
        return type;
    }
    
        @Override
    public String toString() {
        String pieceString = "";
        if(colour== Colour.BLACK){
            pieceString = "♜";
        }
        else if(colour== Colour.WHITE){
            pieceString = "♖";
        }
        return pieceString;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
