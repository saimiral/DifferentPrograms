package guiexamples;

import javax.swing.JFrame;

public class GUIExamples {

    public static void main(String[] args) {
        GUI MyGUI = new GUI();
        MyGUI.setSize(450, 250);
        MyGUI.setTitle("Akoma dk ti kanw");
        MyGUI.Init();
        MyGUI.setVisible(true);
        MyGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
