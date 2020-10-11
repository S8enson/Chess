package chess;

import chess.Pieces.*;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Board {

    Space[][] squares;

    public Board() {
        squares = new Space[8][8];
        
        // initialize Colour.WHITE pieces 
        squares[0][0] = new Space(0, 0, new Rook(0, 0, Colour.WHITE));
        squares[0][1] = new Space(1, 0, new Knight(1, 0, Colour.WHITE));
        squares[0][2] = new Space(2, 0, new Bishop(2, 0, Colour.WHITE));
        squares[0][3] = new Space(3, 0, new Queen(3, 0, Colour.WHITE));
        squares[0][4] = new Space(4, 0, new King(4, 0, Colour.WHITE));
        squares[0][5] = new Space(5, 0, new Bishop(5, 0, Colour.WHITE));
        squares[0][6] = new Space(6, 0, new Knight(6, 0, Colour.WHITE));
        squares[0][7] = new Space(7, 0, new Rook(7, 0, Colour.WHITE));
        
        for(int i = 0; i < 8; i++){
        squares[1][i] = new Space(i, 1, new Pawn(i, 1, Colour.WHITE));
        }
        

        // initialize Colour.BLACK pieces 
        squares[7][0] = new Space(0, 7, new Rook(0, 7, Colour.BLACK));
        squares[7][1] = new Space(1, 7, new Knight(1, 7, Colour.BLACK));
        squares[7][2] = new Space(2, 7, new Bishop(2, 7, Colour.BLACK));
        squares[7][3] = new Space(3, 7, new Queen(3, 7, Colour.BLACK));
        squares[7][4] = new Space(4, 7, new King(4, 7, Colour.BLACK));
        squares[7][5] = new Space(5, 7, new Bishop(5, 7, Colour.BLACK));
        squares[7][6] = new Space(6, 7, new Knight(6, 7, Colour.BLACK));
        squares[7][7] = new Space(7, 7, new Rook(7, 7, Colour.BLACK));
        
        for(int i = 0; i < 8; i++){
        squares[6][i] = new Space(i, 6, new Pawn(i, 6, Colour.BLACK));
        }

        // initialize remaining Squares without any piece 
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Space(j, i, null);

            }
        }
    }
    
    // copy constructor for testing possible moves
    public Board(Board copy){
        this.squares = new Space[8][8];
    for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(copy.getPiece(i, j) != null){
                Type typeCopy = copy.getPiece(i, j).getType();
                if(typeCopy == Type.BISHOP){
                this.squares[i][j] = new Space(j, i, new Bishop((Bishop)copy.getPiece(i, j)));
                }
                else if(typeCopy == Type.KING){
                this.squares[i][j] = new Space(j, i, new King((King)copy.getPiece(i, j)));
                }
                else if(typeCopy == Type.QUEEN){
                this.squares[i][j] = new Space(j, i, new Queen((Queen)copy.getPiece(i, j)));
                }
                else if(typeCopy == Type.KNIGHT){
                this.squares[i][j] = new Space(j, i, new Knight((Knight)copy.getPiece(i, j)));
                }
                else if(typeCopy == Type.ROOK){
                this.squares[i][j] = new Space(j, i, new Rook((Rook)copy.getPiece(i, j)));
                }
                else if(typeCopy == Type.PAWN){
                this.squares[i][j] = new Space(j, i, new Pawn((Pawn)copy.getPiece(i, j)));
                }
                }
                else{
                this.squares[i][j] = new Space(j, i,null);
                }
                }

            }
        }
    
    // converts board to string
    public String toString() {
        String boardString ="|_|A|B|C|D|E|F|G|H|_|\n";

        for (int i = 0; i < 8; i++) {
            boardString +="|" + (8-i) + "|";
            for (int j = 0; j < 8; j++) {
                if (squares[7-i][j].getPiece() == null) {
                    boardString +="_";
                } else {
                    boardString+= squares[7-i][j].getPiece().toString();
                }
                boardString += "|";
            }
            boardString += (8-i) + "|\n";
        }

        boardString += "|_|A|B|C|D|E|F|G|H|_|\n";
        
        return boardString;

    }
    
    public void drawBoard(Graphics g){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
            }
        }
    
    
    }
    
    // returns piece
    public Piece getPiece(int row, int col){
    return squares[row][col].getPiece();
    }
    
    // moves piece
    public void move(int initRow, int initCol, int finalRow, int finalCol){
    squares[finalRow][finalCol].setPiece(squares[initRow][initCol].getPiece()); 
    squares[initRow][initCol].setPiece(null);
    
    }
}
