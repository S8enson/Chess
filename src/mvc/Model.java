/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

//import chess.Game;
//import static chess.Game.board;
import chess.Leaderboard;
import chess.Pieces.Bishop;
import chess.Pieces.Knight;
import chess.Pieces.Pawn;
import chess.Pieces.Piece;
import chess.Pieces.Queen;
import chess.Pieces.Rook;
import chess.Player;
import chess.Pieces.Type;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Shiqing Wu
 */
public class Model extends Observable {

    public Database db;
    public Data data;
    public int answer = 0;
    public String username; // To store the user name for later use.

    public static Board board;
    public boolean over;
    public boolean whiteTurn;

    static Scanner input;
    static PrintWriter gameState;
    String move;
    int initRow, initCol, finalRow, finalCol;
    static Player whitePlayer, blackPlayer;
    static Leaderboard leaderboard;
    private String invalidString;
    public Piece lastPiece;

    /**
     * Step 2: Initialize the instance of Database in the constructor, and build
     * the connection between the program and the database.
     *
     * Go to Database.java for Step 3.
     */
    public Model() {
        this.db = new Database();
        this.db.dbsetup();

        over = false;
        whiteTurn = true;
        input = new Scanner(System.in);
        board = new Board();
        leaderboard = new Leaderboard();
        

    }

    /**
     * Step 6:
     *
     * @param username
     * @param password
     */
    public void newGame() {

        this.notifyObservers(board);
    }

    public void checkName(String wUsername, String bUsername) {
        this.username = username; // Store username
        /**
         * Compare username and password with that inside database. Go to
         * checkName() of Database.java.
         *
         * Note: You should define attributes of Data before you go to Database
         * class.
         */
        this.data = this.db.checkName(wUsername, bUsername);

        /**
         * After you finish Step 7. Generate a new question if data.loginFlag is
         * true, otherwise do nothing.
         */
//        if (data.whiteLoginFlag && data.blackLoginFlag) {
//            this.newGame();
//        }
        this.setChanged(); // Essential. To mark this observable instance has been modified.
        /**
         * Pass data to Observers. Here, the observer is view. notifyObservers()
         * would invoke update() of View automatically.
         *
         * Go to update() of View.java for the next step.
         */
        //this.notifyObservers(this.data);
    }


    public void move(String start, String end) {
        if (!data.quitFlag) {
            initRow = start.charAt(1) - '1';
            initCol = start.charAt(0) - 'A';
            finalRow = end.charAt(1) - '1';
            finalCol = end.charAt(0) - 'A';
            Board current = new Board(board);
            if (moveValid(initRow, initCol, finalRow, finalCol, false)) {
                //gameState.print(move + "\n\n");
                board.move(initRow, initCol, finalRow, finalCol);
                this.setChanged();
                this.notifyObservers(board);
                lastPiece = board.getPiece(finalRow, finalCol);
                if ((lastPiece.getType() == Type.PAWN) && ((whiteTurn && lastPiece.y == 7) || (!whiteTurn && lastPiece.y == 0))) {
                    this.setChanged();
                    this.notifyObservers("PROMOTION");
                    //promotion(piece);
                    
                }
                whiteTurn = !whiteTurn;
                this.setChanged();
                this.notifyObservers(board);
            }

//            PrintStream _err = System.err;
//            System.setErr(new PrintStream(new OutputStream() {
//                public void write(int b) {
//                }
//            }));
            if (isChecked()) {
                if (checkMate()) {
                    //System.setErr(_err);
                    if (whiteTurn) {
//                            System.out.println("CHECKMATE " + blackPlayer.name + " Wins!");
//                            blackPlayer.won();
//                            whitePlayer.lost();
                        this.data.blackWins++;
                        this.data.whiteLosses++;
                        this.data.quitFlag = true;
                        this.data.winner = this.data.bUsername;
                        this.db.gameOver(data);
                        this.setChanged();
                        this.notifyObservers(data);
                    } else {
//                            System.out.println("CHECKMATE " + whitePlayer.name + " Wins!");
//                            whitePlayer.won();
//                            blackPlayer.lost();
                        this.data.whiteWins++;
                        this.data.blackLosses++;
                        this.data.winner = this.data.wUsername;
                        this.db.gameOver(data);
                        this.data.quitFlag = true;
                        this.setChanged();
                        
                        this.notifyObservers(data);
                    }
                    this.over = true;
                    //update leaderboard
                    leaderboard.updateLeaderboard();
                } else {
                    //System.setErr(_err);

                    //System.err.println("You are checked");
                }
            }
            //System.setErr(_err);
        }
       

    }
    //gameState.close();

    public boolean moveValid(int initRow, int initCol, int finalRow, int finalCol, boolean checking) {

        boolean take = false;
        // invalid if the move origin or destination is outside the board

        if (initRow < 0 || initRow > 7 || initCol < 0 || initCol > 7 || finalRow < 0 || finalRow > 7 || finalCol < 0 || finalCol > 7) {
            //System.err.println("Move is outside of bounds");
            if(!checking){
                this.setChanged();
                this.notifyObservers("Move is outside of bounds");
            }
            return false;
        }

        // Invalid if piece does not exist
        if (board.getPiece(initRow, initCol) == null) {
            //System.err.println("No piece exists there");
            if(!checking){
                this.setChanged();
                this.notifyObservers("No piece exists there");
            }
            return false;
        }

        // Invalid if player attempts to moves out of turn
        if (!checking && ((board.getPiece(initRow, initCol).colour == Colour.WHITE && !whiteTurn) || (board.getPiece(initRow, initCol).colour == Colour.BLACK && whiteTurn))) {
            //System.err.println("Wrong player");
            if(!checking){
                this.setChanged();
                this.notifyObservers("Wrong player");
            }
            return false;
        }

        // check if move obeys chess rules for type of piece
        if (!board.getPiece(initRow, initCol).validMove(finalCol, finalRow)) {
            if (board.getPiece(initRow, initCol).getType() == Type.PAWN && board.getPiece(finalRow, finalCol) != null) {
                Pawn pawn = (Pawn) board.getPiece(initRow, initCol);
                if (pawn.validTake(finalCol, finalRow)) {
                    take = true;
                } else {
                    //System.err.println("Invalid type of move for piece type");
                    if(!checking){
                this.setChanged();
                this.notifyObservers("Invalid type of move for piece type");
            }
                    
                    return false;
                }
            } else {
                //System.err.println("Invalid type of move for piece type");
                if(!checking){
                this.setChanged();
                this.notifyObservers("Invalid type of move for piece type");
            }
                return false;
            }
        }
        // check if taking piece pawn

        //check path is clear
        if (!checkPath(take, initRow, initCol, finalRow, finalCol)) {
            //System.err.println("Piece in the way");
            if(!checking){
                this.setChanged();
                this.notifyObservers("Piece in the way");
            }
            return false;
        }

        // stops next statement from null check
        if (board.getPiece(finalRow, finalCol) == null) {
            return true;
        }

        // invalid if the white lands on white
        if (board.getPiece(initRow, initCol).colour == board.getPiece(finalRow, finalCol).colour) {
            //System.err.println("One of your own pieces already occupies this square");
            if(!checking){
                this.setChanged();
                this.notifyObservers("One of your own pieces already occupies this square");
            }
            return false;
        }
        return true;
    }

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

    void leaderboard() {
        this.setChanged();
                this.notifyObservers(this.db.leaderboard());
        
    }

    void updateBoard() {
        this.setChanged();
                this.notifyObservers(board);
    }

}
