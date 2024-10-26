package prototype;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoginSignUpFrame extends JFrame{
    
    CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    public void init(){
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	this.setTitle("CRS - Car Rental System");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LoginSignUpDAO loginPanel = new LoginSignUpDAO(mainPanel, cardLayout,"login");
        LoginSignUpDAO signupPanel = new LoginSignUpDAO(mainPanel, cardLayout, "signup");
            
        mainPanel.add(loginPanel, "login");
        mainPanel.add(signupPanel, "signup");

        this.add(mainPanel);
        cardLayout.show(mainPanel, "login");
        
        this.setVisible(true);
    }
}