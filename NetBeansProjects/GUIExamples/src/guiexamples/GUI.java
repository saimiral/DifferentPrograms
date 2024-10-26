package guiexamples;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame{
    
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    JTextField tf1;
    Panel p1;
    
    public GUI(){
        p1 = new Panel();
        b1 = new JButton("7");
        b2 = new JButton("8");
        b3 = new JButton("9");
        b4 = new JButton("+");
        b5 = new JButton("4");
        b6 = new JButton("5");
        b7 = new JButton("6");
        b8 = new JButton("-");
        b9 = new JButton("1");
        b10 = new JButton("2");
        b11 = new JButton("3");
        b12 = new JButton("*");
        b13 = new JButton("0");
        b14 = new JButton(".");
        b15 = new JButton("=");
        b16 = new JButton("/");
        tf1 = new JTextField();
    }
    
    public void Init(){
        
        p1.setLayout(new GridLayout(4, 4));
        
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        p1.add(b4);
        p1.add(b5);
        p1.add(b6);
        p1.add(b7);
        p1.add(b8);
        p1.add(b9);
        p1.add(b10);
        p1.add(b11);
        p1.add(b12);
        p1.add(b13);
        p1.add(b14);
        p1.add(b15);
        p1.add(b16);
        
        this.add(tf1, BorderLayout.NORTH);
        this.add(p1, BorderLayout.CENTER);
    }
}
