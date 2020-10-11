package chess.Pieces;

import chess.Colour;
import chess.Player;
import chess.Space;
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
public class Bishop extends Piece{
    Type type;
            
         public Bishop(int x, int y, Colour c){
         super(x, y, c);
         type = Type.BISHOP;
         
         }
         
         public Bishop(Bishop copy){
         this(copy.x, copy.y, copy.colour);
         }

         // ensures horizontal movement is equal to vertical movement
    @Override
    public boolean validMove(int newX, int newY) {
        

        
        int xChange = Math.abs(newX-this.x);
        int yChange = Math.abs(newY-this.y);
        
        return xChange == yChange;

    }

    @Override
    public Type getType() {
        return type;
    }


    @Override
    public Colour getColour() {
        return colour;
    }



    @Override
    public String toString() {
        String pieceString = "";
        if(colour== Colour.BLACK){
            pieceString = "♝";
        }
        else if(colour== Colour.WHITE){
            pieceString = "♗";
        }
        return pieceString;
    }


}
