package chess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.scene.paint.Color;
import chess.Pieces.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Game {

    public static Board board;
    public boolean over;
    public boolean whiteTurn;

    static Scanner input;
    static PrintWriter gameState;
    String move;
    int initRow, initCol, finalRow, finalCol;
    static Player whitePlayer, blackPlayer;
    static Leaderboard leaderboard;
    public ChessGUI gui;

    public Game() {
        over = false;
        whiteTurn = true;
        input = new Scanner(System.in);
        board = new Board();
        leaderboard = new Leaderboard();
        gui = new ChessGUI();
        
        

    }

    public static void main(String[] args) {
        Game game = new Game();

        int numMoves = 0;
        game.play();

    }

    // main running function  
    public void play() {
        while (true) {
            // Asks for input
            System.out.println("Type G to play, L to view leaderboard or E to exit:");
            String init = input.nextLine();
            if (init.toLowerCase().equals("l")) {
                leaderboard.printLeaderboard();
            } else if (init.toLowerCase().equals("g")) {

                System.out.println("Beginning Game!\n\nEnter exit at anytime to stop the program.\n");
                this.getPlayers();
                try {
                    gameState = new PrintWriter(new FileOutputStream(whitePlayer.name + blackPlayer.name + "gamelog.txt", true));
                } catch (FileNotFoundException e) {
                }
                gameState.print("\n " +java.time.LocalDateTime.now()+"\n");
                System.out.println("Input Move in Format StartingColRow EndingColRow, eg B2 B3\n");
                String out = "";
                while (!this.over) {
                    out = board.toString();
                    System.out.println(out);
                    gameState.print(out+"\n\n");

                    

                    this.move();
                    
                    PrintStream _err = System.err;
                    System.setErr(new PrintStream(new OutputStream() {
                        public void write(int b) {
                        }
                    }));
                    if (isChecked()) {
                        if (checkMate()) {
                            System.setErr(_err);
                            if (whiteTurn) {
                                System.out.println("CHECKMATE " + blackPlayer.name + " Wins!");
                                blackPlayer.won();
                                whitePlayer.lost();
                            } else {
                                System.out.println("CHECKMATE " + whitePlayer.name + " Wins!");
                                whitePlayer.won();
                                blackPlayer.lost();
                            }
                            this.over = true;
                            //update leaderboard
                            leaderboard.updateLeaderboard();
                        } else {
                            System.setErr(_err);

                            System.err.println("You are checked");
                        }
                    }
                    System.setErr(_err);

                }
               gameState.close();
            } else if (init.toLowerCase().equals("e")) {
                break;
            }
        }
    }

    // takes move input and implements it
    public void move() {

        if (whiteTurn) {
            System.out.println("\n" + whitePlayer.name + "'s Turn!\nInput move:");
        } else {
            System.out.println("\n" + blackPlayer.name + "'s Turn!\nInput move:");

        }

        move = input.nextLine();
        move = move.toLowerCase();

        if (move.equals("exit")) {
            over = true;
            System.out.println("Thanks for playing.");
            return;
        }

        String[] seperate = move.split(" ");
        if (move.length() == 5) {
            initRow = (seperate[0].charAt(1) - '1');
            initCol = seperate[0].charAt(0) - 'a';
            finalRow = (seperate[1].charAt(1) - '1');
            finalCol = seperate[1].charAt(0) - 'a';
            Board current = new Board(board);
            if (moveValid(initRow, initCol, finalRow, finalCol, false)) {
                gameState.print(move+"\n\n");
                board.move(initRow, initCol, finalRow, finalCol);

                Piece piece = board.getPiece(finalRow, finalCol);
                if ((piece.getType() == Type.PAWN) && ((whiteTurn && piece.y == 7) || (!whiteTurn && piece.y == 0))) {
                    promotion(piece);
                }
                whiteTurn = !whiteTurn;

            }
        } else {
            System.err.println("Invalid Move");
        }

    }

    // checks if move is valid 
    public boolean moveValid(int initRow, int initCol, int finalRow, int finalCol, boolean checking) {

        boolean take = false;
        // invalid if the move origin or destination is outside the board

        if (initRow < 0 || initRow > 7 || initCol < 0 || initCol > 7 || finalRow < 0 || finalRow > 7 || finalCol < 0 || finalCol > 7) {
            System.err.println("Move is outside of bounds");
            return false;
        }

        // Invalid if piece does not exist
        if (board.getPiece(initRow, initCol) == null) {
            System.err.println("No piece exists there");
            return false;
        }

        // Invalid if player attempts to moves out of turn
        if (!checking && ((board.getPiece(initRow, initCol).colour == Colour.WHITE && !whiteTurn) || (board.getPiece(initRow, initCol).colour == Colour.BLACK && whiteTurn))) {
            System.err.println("Wrong player");
            return false;
        }

        // check if move obeys chess rules for type of piece
        if (!board.getPiece(initRow, initCol).validMove(finalCol, finalRow)) {
            if (board.getPiece(initRow, initCol).getType() == Type.PAWN && board.getPiece(finalRow, finalCol) != null) {
                Pawn pawn = (Pawn) board.getPiece(initRow, initCol);
                if (pawn.validTake(finalCol, finalRow)) {
                    take = true;
                } else {
                    System.err.println("Invalid type of move for piece type");
                    return false;
                }
            } else {
                System.err.println("Invalid type of move for piece type");
                return false;
            }
        }
        // check if taking piece pawn

        //check path is clear
        if (!checkPath(take, initRow, initCol, finalRow, finalCol)) {
            System.err.println("Piece in the way");
            return false;
        }

        // stops next statement from null check
        if (board.getPiece(finalRow, finalCol) == null) {
            return true;
        }

        // invalid if the white lands on white
        if (board.getPiece(initRow, initCol).colour == board.getPiece(finalRow, finalCol).colour) {
            System.err.println("One of your own pieces already occupies this square");
            return false;
        }
        return true;
    }

    // gets players at initiation of game
    public void getPlayers() {
        String playerName;
        while (true) {
            System.out.println("NAMES MUST BE 3 CHARS");

            System.out.println("Enter White Player Name:");
            playerName = input.nextLine().toUpperCase();
            if (playerName.length() == 3) {
                whitePlayer = new Player(playerName);
                leaderboard.add(whitePlayer);
                break;
            }
        }

        //search leaderboard
        while (true) {
            System.out.println("NAMES MUST BE 3 CHARS");

            System.out.println("Enter Black Player Name:");
            playerName = input.nextLine().toUpperCase();
            if (playerName.length() == 3) {
                blackPlayer = new Player(playerName);
                leaderboard.add(blackPlayer);
                break;
            }
        }

    }


    // checks if a player is in check
    public boolean isChecked() {
        int numChecking = 0;
        Colour colour;
        if (whiteTurn) {
            colour = Colour.WHITE;
        } else {
            colour = Colour.BLACK;
        }
        //find king
        int[] coordinates = findKing(colour);

        // checks all pieces to see if they are able to take the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null && board.getPiece(i, j).getColour() != colour) {
                    if (moveValid(i, j, coordinates[0], coordinates[1], true)) {
                        board.getPiece(i, j).checking = true;
                        numChecking++;

                    } else {
                        board.getPiece(i, j).checking = false;
                    }
                }
            }
        }

        return numChecking > 0;
    }

    // checks for checkmate, called only if already in check to find if in checkmate
    public boolean checkMate() {
        Colour colour;
        //String name;
        if (whiteTurn) {
            colour = Colour.WHITE;
            //name = blackPlayer.name;
        } else {
            colour = Colour.BLACK;
            //name = whitePlayer.name;
        }
        Board current;

        // checks if any possible move of any piece will result in the check being stopped
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null && board.getPiece(i, j).getColour() == colour) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (moveValid(i, j, k, l, true)) {
                                
                                current = new Board(board);

                                board.move(i, j, k, l);

                                
                                if (!isChecked()) {
                                    
                                    return false;
                                }
                                board = new Board(current);
                            }

                        }

                    }

                }

            }
        }

        return true;
    }

    // finds king
    public int[] findKing(Colour colour) {
        int[] coordinates = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) != null && board.getPiece(i, j).getType() == Type.KING && board.getPiece(i, j).getColour() == colour) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }
        return null;
    }

    // checks if a path can be followed by a piece without running into another piece
    public boolean checkPath(boolean take, int initRow, int initCol, int finalRow, int finalCol) {
        //check path is clear
        int h, i, j, k;
        if (finalRow - initRow > 0) {
            j = 1;
        } else if (finalRow - initRow < 0) {
            j = -1;
        } else {
            j = 0;
        }
        if (finalCol - initCol > 0) {
            k = 1;
        } else if (finalCol - initCol < 0) {
            k = -1;
        } else {
            k = 0;
        }

        if (!(board.getPiece(initRow, initCol).getType() == Type.KNIGHT)) {
            i = initRow;
            h = initCol;

            while (!(i == finalRow && h == finalCol)) {
                i += j;
                h += k;
                if (i == finalRow && h == finalCol && !(board.getPiece(initRow, initCol).getType() == Type.PAWN)) {
                    take = true;
                }
                if (board.getPiece(i, h) != null && !take) {

                    return false;
                }

            }
        }
        return true;
    }

    // promotes pawn to a queen, rook, bishop or knight
    public void promotion(Piece piece) {
        System.out.println("What piece would you like to be promoted to?\n type Q for queen, R for rook, K for knight or B for bishop");
        Piece newPiece;
        while (true) {
            String newType = input.nextLine().toLowerCase();
            if (newType.equals("q")) {
                newPiece = new Queen(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                return;
            } else if (newType.equals("r")) {
                newPiece = new Rook(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                return;
            } else if (newType.equals("k")) {
                newPiece = new Knight(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                return;
            } else if (newType.equals("b")) {
                newPiece = new Bishop(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                return;
            } else {
                System.err.println("Invalid input");
                return;
            }

        }
    }

}
