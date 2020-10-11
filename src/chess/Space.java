package chess;

import chess.Pieces.Piece;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * holds player name and wins/losses
 * @author Sam
 */
public class Space {

    private Piece piece;
    private int x, y;

    public Space(int x, int y, Piece piece) {

        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Piece getPiece() {
        return this.piece;
    }
    
    public void setPiece(Piece p){
    this.piece = p;
    if (p!= null){
    p.x = this.x;
    p.y = this.y;
    }
    }
    

}
