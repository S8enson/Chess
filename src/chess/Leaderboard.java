/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import static chess.Game.blackPlayer;
import static chess.Game.gameState;
import static chess.Game.whitePlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *
 * @author Sam
 */
public class Leaderboard {
    
    String id;
    String wins;
    String losses;
    BufferedReader reader;
    
    ArrayList<Player> players;
    static PrintWriter leaderboard;
    
    public Leaderboard(){
        players = new ArrayList<>();
        
        // reads in saved leaderboard from txt file
    try {
            reader = new BufferedReader(new FileReader(new File("Leaderboard.txt")));
            String line = reader.readLine();
            StringTokenizer st;
            while (line != null) {
                
                st = new StringTokenizer(line, ",");
                id = st.nextToken();
                wins = st.nextToken();
                losses = st.nextToken();
                players.add(new Player(id, Integer.parseInt(wins), Integer.parseInt(losses)));
                
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // to string method
        public void printLeaderboard() {
            String win = "";
            String loss = "";

        sort();
        System.out.println("||♔LEADERBOARD♚||");
            for(int i =0; i < players.size(); i++){
                
                win = String.format("%03d" , players.get(i).win);
                loss = String.format("%03d" , players.get(i).loss);
                System.out.print("||" + players.get(i).getName() + "||" + win + "||" + loss + "||\n");
                // read next line
            }
        }
        
        // updates leaderboard and prints to txt file
        public void updateLeaderboard(){
        
        try {
                    leaderboard = new PrintWriter(new FileOutputStream("Leaderboard.txt"));
                } catch (FileNotFoundException e) {
                }
      
            sort();
            for(int i = 0; i < players.size(); i++){
               
                leaderboard.print(players.get(i).name+","+players.get(i).win+","+players.get(i).loss+"\n");
            }
            leaderboard.close();
            
        
        }
        
        // sorts players based on nunber of wins
        public void sort(){
            
            Collections.sort(players, Collections.reverseOrder());
        
        }
        
        // adds player(checks if player already exists first
        public void add(Player newPlayer){
        // search for player
        String name = "";
        String newName = newPlayer.getName();
        for(int i = 0; i < players.size(); i++){
            name = players.get(i).getName();
        if(newName.equals(name)){
            newPlayer.loss = players.get(i).loss;
            newPlayer.win = players.get(i).win;
            players.set(i, newPlayer);
        return;
        }
        }
        // add if new
        
        players.add(newPlayer);
        }
}
