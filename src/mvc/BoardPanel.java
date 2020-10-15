/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

/**
 *
 * @author Sam
 */
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class BoardPanel extends JPanel {

    public JToggleButton[] buttons;
    public Board board;
    public JToggleButton button;
    public String s;
    public Color dark;
    public Color light;

    public BoardPanel() {
        buttons = new JToggleButton[64];
        light = new Color(255, 223, 158);
        dark = new Color(138, 101, 54);
        
        
        this.setLayout(new GridLayout(8, 8));

        update();

    }

    public void update(){
    for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                s = Character.toString((char)(j+65))+(8-i);
                button = new JToggleButton(s);
                //button.seti
                if (i % 2 == 1) {
                    if (j % 2 == 0) {
                        //g.setColor(light);
                        button.setBackground(dark);
                    }
                    else{
                    //g.setColor(dark);
                    button.setBackground(light);
                    }
                }
                else{
                if (j % 2 == 0) {
                        //g.setColor(dark);
                        button.setBackground(light);
                    }
                else{
                //g.setColor(light);
                button.setBackground(dark);
                }
                }
                //g.fillRect(20 + 40*i,20 + 40*j, 40, 40);
                button.setOpaque(true);
                button.setBorderPainted(false);
                
                this.add(button);
                buttons[i*j]=button;
                
//              this.button.addActionListener(listener);
            }
        }
    
    }
    public void setBoard(Board board){
    this.board = board;
    }


//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//                Color light = new Color(255, 223, 158);
//        Color dark = new Color(138, 101, 54);
//        JButton button;
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                button = new JButton();
//                if (i % 2 == 1) {
//                    if (j % 2 == 0) {
//                        //g.setColor(light);
//                        button.setBackground(light);
//                    }
//                    else{
//                    //g.setColor(dark);
//                    button.setBackground(dark);
//                    }
//                }
//                else{
//                if (j % 2 == 0) {
//                        //g.setColor(dark);
//                        button.setBackground(dark);
//                    }
//                else{
//                //g.setColor(light);
//                button.setBackground(light);
//                }
//                }
//                //g.fillRect(20 + 40*i,20 + 40*j, 40, 40);
//                this.add(button);
//            }
//        }
//        this.setLayout(new GridLayout(8, 8));
//    }

}
