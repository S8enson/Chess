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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HomePanel extends JPanel {

    public BufferedImage img = null;

    public HomePanel() {
        

        try {
            img = ImageIO.read(getClass().getResource("/images/homeBG.png"));
        } catch (IOException e) {
        }

    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Text
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

}
