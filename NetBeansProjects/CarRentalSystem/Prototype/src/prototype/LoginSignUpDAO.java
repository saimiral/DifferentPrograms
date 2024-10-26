package prototype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginSignUpDAO extends JPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JLabel titleLabel, userLabel, passLabel, nameLabel, surnameLabel, ageLabel;
    private JTextField userField, nameField, surnameField, ageField;

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";
    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    public LoginSignUpDAO(JPanel mainPanel, CardLayout cardLayout, String type) {

        try {
            Class.forName(MYSQL_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            this.mainPanel = mainPanel;
            this.cardLayout = cardLayout;

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            titleLabel = new JLabel(type.equals("login") ? "Login" : "Signup");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(titleLabel, gbc);

            userLabel = new JLabel("Username:");
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridy++;
            add(userLabel, gbc);

            userField = new JTextField(15);
            gbc.gridx = 1;
            add(userField, gbc);

            passLabel = new JLabel("Password:");
            gbc.gridx = 0;
            gbc.gridy++;
            add(passLabel, gbc);

            JPasswordField passField = new JPasswordField(15);
            gbc.gridx = 1;
            add(passField, gbc);

            if (type.equals("signup")) {
                gbc.gridy++;

                nameLabel = new JLabel("Name:");
                gbc.gridx = 0;
                add(nameLabel, gbc);

                nameField = new JTextField(15);
                gbc.gridx = 1;
                add(nameField, gbc);

                surnameLabel = new JLabel("Surname:");
                gbc.gridx = 0;
                gbc.gridy++;
                add(surnameLabel, gbc);

                surnameField = new JTextField(15);
                gbc.gridx = 1;
                add(surnameField, gbc);

                ageLabel = new JLabel("Age:");
                gbc.gridx = 0;
                gbc.gridy++;
                add(ageLabel, gbc);

                ageField = new JTextField(15);
                gbc.gridx = 1;
                add(ageField, gbc);
            }

            gbc.gridy++;

            JButton submitButton = new JButton(type.equals("login") ? "Login" : "Signup");
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            add(submitButton, gbc);

            gbc.gridy++;

            JButton switchButton = new JButton(type.equals("login") ? "Go to Signup" : "Go to Login");
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            add(switchButton, gbc);

            submitButton.addActionListener((ActionEvent e) -> {
                String password = new String(passField.getPassword());

                if (userField.getText().isEmpty() || userField.getText().length() > 30) {
                    JOptionPane.showMessageDialog(null, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (password.isEmpty() || password.length() > 30) {
                    JOptionPane.showMessageDialog(null, "Invalid password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (type.equals("login")) {
                        String loginQuery = "SELECT cID, cUSERNAME FROM CLIENT WHERE cUSERNAME = ? AND cPASSWORD = ?";
                        try (PreparedStatement stmt = conn.prepareStatement(loginQuery)) {
                            stmt.setString(1, userField.getText());
                            stmt.setString(2, passField.getText());
                            try (ResultSet rs = stmt.executeQuery()) {
                                if (rs.next()) {
                                    int clientId = rs.getInt("cID");
                                    String username = rs.getString("cUSERNAME");
                                    
                                    JOptionPane.showMessageDialog(null, "Logged in as: " + username);
                                    userField.setText("");
                                    passField.setText("");
                                    FiltersFrame filtersFrame = new FiltersFrame(mainPanel, cardLayout, clientId);
                                    mainPanel.add(filtersFrame, "Filters");
                                    cardLayout.show(mainPanel, "Filters");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (nameField.getText().isEmpty() || nameField.getText().length() > 30) {
                            JOptionPane.showMessageDialog(null, "Invalid name", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (surnameField.getText().isEmpty() || surnameField.getText().length() > 30) {
                            JOptionPane.showMessageDialog(null, "Invalid surname", "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (ageField.getText().isEmpty() || Integer.parseInt(ageField.getText()) > 99 || Integer.parseInt(ageField.getText()) < 18) {
                            JOptionPane.showMessageDialog(null, "Invalid age", "Error", JOptionPane.ERROR_MESSAGE);
                        } 
                        else {
                            String signupQuery = "INSERT INTO CLIENT (cNAME, cSURNAME, cAGE, cUSERNAME, cPASSWORD) VALUES (?, ?, ?, ?, ?)";
                            try (PreparedStatement stmt = conn.prepareStatement(signupQuery, Statement.RETURN_GENERATED_KEYS)) {
                                stmt.setString(1, nameField.getText());
                                stmt.setString(2, surnameField.getText());
                                stmt.setInt(3, Integer.parseInt(ageField.getText()));
                                stmt.setString(4, userField.getText());
                                stmt.setString(5, passField.getText());
                                stmt.executeUpdate();
                                
                                try (ResultSet rs = stmt.getGeneratedKeys()) {
                                    if (rs.next()) {
                                        int clientId = rs.getInt(1);
                                        
                                        JOptionPane.showMessageDialog(null, "Signed up as: " + userField.getText() + " with cID: " + clientId);
                                        nameField.setText("");
                                        surnameField.setText("");
                                        ageField.setText("");
                                        userField.setText("");
                                        passField.setText("");
                                        FiltersFrame filtersFrame = new FiltersFrame(mainPanel, cardLayout, clientId);
                                        mainPanel.add(filtersFrame, "Filters");
                                        cardLayout.show(mainPanel, "Filters");
                                    }
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            });

            switchButton.addActionListener((ActionEvent e) -> {
                cardLayout.show(mainPanel, type.equals("login") ? "signup" : "login");
            });

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
