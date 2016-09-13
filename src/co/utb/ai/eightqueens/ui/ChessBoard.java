/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.utb.ai.eightqueens.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author william
 */
public class ChessBoard extends JFrame {

    public ChessBoard(List<Double> positions) throws IOException {
        this.setSize(new Dimension(1000, 1000));

        this.setLayout(new BorderLayout());
        JPanel board = new JPanel();
        Border margin = new EmptyBorder(100, 100, 100, 100);
        board.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black), margin));
        this.add(board, BorderLayout.CENTER);

        GridLayout grid = new GridLayout(8, 8);
        board.setLayout(grid);
        int k = 0;
        BufferedImage crown = ImageIO.read(new File("crown1.png"));
        for (int i = 0; i < 8; i++) {
            int start = (i+1)%2;
            for (int j = 0; j < 8; j++) {
                JPanel square = new JPanel();
                if ((start + 1) % 2 == 0) {
                    square.setBackground(Color.black);
                } else {
                    square.setBackground(Color.white);
                }
                
                Double q = positions.get(k);
                if (q == 1.0) {
                    square.setBorder(new EmptyBorder(10, 10, 10, 10));
                    JLabel picLabel = new JLabel(new ImageIcon(crown));
                    square.setLayout(new BorderLayout());
                    square.add(picLabel, BorderLayout.CENTER);
                    
                }
                board.add(square); 
                start++;
                k++;
            }
            
        }
    }
}
