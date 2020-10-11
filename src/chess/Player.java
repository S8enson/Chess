package chess;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
public class Player implements Comparable<Player>{
    String name;
    int win;
    int loss;

    public Player(String name) {
        this.name = name;
        this.win = 0;
        this.loss = 0;
    }
    
    public Player(String name, int win, int loss){
        this.name = name;
        this.win = win;
        this.loss = loss;
    }
    
    public int gamesPlayed(){
    return win+loss;
    }
    
   public void won(){
   win++;
   }
   
   public void lost(){
   loss++;
   }

    @Override
    public int compareTo(Player arg0) {
        return this.win - arg0.win;
    }
    
    public String getName(){
    return name;
    }
    
    public int getWins(){
    return win;
    }
}
