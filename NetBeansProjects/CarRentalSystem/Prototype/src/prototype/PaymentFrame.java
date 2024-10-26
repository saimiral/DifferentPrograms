package prototype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PaymentFrame extends JPanel {

    static final String MYSQL_SUB = "jdbc:mysql:";
    static final String DB_SERVER = "//localhost:3306/";
    static final String DB_NAME = "CRS";
    static final String DB_USER = "root";
    static final String DB_PASS = "mysql";
    static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = MYSQL_SUB + DB_SERVER + DB_NAME;

    public PaymentFrame(CardLayout cardLayout, JPanel mainPanel, Vehicle vehicle, int clientId) {
        try {
            Class.forName(MYSQL_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            String query = "SELECT cNAME, cSURNAME, cAGE FROM CLIENT WHERE cID = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, clientId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String name = rs.getString("cNAME");
                        String surname = rs.getString("cSURNAME");
                        int age = rs.getInt("cAGE");

                        JLabel titleLabel = new JLabel("Payment");
                        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.gridwidth = GridBagConstraints.REMAINDER;
                        add(titleLabel, gbc);

                        JLabel nameLabel = new JLabel("Name:");
                        gbc.gridx = 0;
                        gbc.gridwidth = 1;
                        gbc.gridy++;
                        add(nameLabel, gbc);

                        JLabel nameLabelValue = new JLabel();
                        nameLabelValue.setText(name);
                        gbc.gridx++;
                        add(nameLabelValue, gbc);

                        JLabel surnameLabel = new JLabel("Surname:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(surnameLabel, gbc);

                        JLabel surnameLabelValue = new JLabel();
                        surnameLabelValue.setText(surname);
                        gbc.gridx++;
                        add(surnameLabelValue, gbc);

                        JLabel ageLabel = new JLabel("Age:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(ageLabel, gbc);

                        JLabel ageLabelValue = new JLabel();
                        ageLabelValue.setText(String.valueOf(age));
                        gbc.gridx++;
                        add(ageLabelValue, gbc);

                        JLabel makeLabel = new JLabel("Make:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(makeLabel, gbc);

                        JLabel makeLabelValue = new JLabel();
                        makeLabelValue.setText(vehicle.getMake());
                        gbc.gridx++;
                        add(makeLabelValue, gbc);

                        JLabel modelLabel = new JLabel("Model:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(modelLabel, gbc);

                        JLabel modelLabelValue = new JLabel();
                        modelLabelValue.setText(vehicle.getModel());
                        gbc.gridx++;
                        add(modelLabelValue, gbc);

                        JLabel yearLabel = new JLabel("Year:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(yearLabel, gbc);

                        JLabel yearLabelValue = new JLabel();
                        yearLabelValue.setText(String.valueOf(vehicle.getYear()));
                        gbc.gridx++;
                        add(yearLabelValue, gbc);

                        JLabel licensePlateLabel = new JLabel("License Plate:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(licensePlateLabel, gbc);

                        JLabel licensePlateLabelValue = new JLabel();
                        licensePlateLabelValue.setText(vehicle.getLicensePlate());
                        gbc.gridx++;
                        add(licensePlateLabelValue, gbc);

                        JLabel costLabel = new JLabel("Cost per Day:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(costLabel, gbc);

                        JLabel costLabelValue = new JLabel();
                        costLabelValue.setText(String.valueOf(vehicle.getCost()));
                        gbc.gridx++;
                        add(costLabelValue, gbc);

                        JLabel daysLabel = new JLabel("Days:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(daysLabel, gbc);

                        JLabel daysLabelValue = new JLabel();
                        daysLabelValue.setText(String.valueOf(vehicle.getDays()));
                        gbc.gridx++;
                        add(daysLabelValue, gbc);

                        JLabel statusLabel = new JLabel("Status:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(statusLabel, gbc);

                        JLabel statusLabelValue = new JLabel();
                        statusLabelValue.setText(String.valueOf(vehicle.getStatus()));
                        gbc.gridx++;
                        add(statusLabelValue, gbc);

                        JLabel finalPrice = new JLabel("Final price");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(finalPrice, gbc);

                        JLabel finalPriceValue = new JLabel();
                        int totalCost = vehicle.getDays() * vehicle.getCost();
                        finalPriceValue.setText(String.valueOf(totalCost));
                        gbc.gridx++;
                        add(finalPriceValue, gbc);

                        JLabel methodLabel = new JLabel("Payment method:");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        add(methodLabel, gbc);

                        JRadioButton cashButton = new JRadioButton("Cash");
                        JRadioButton cardButton = new JRadioButton("Card");
                        cashButton.setSelected(true);
                        ButtonGroup grp = new ButtonGroup();
                        grp.add(cashButton);
                        grp.add(cardButton);
                        gbc.gridwidth = 1;
                        gbc.gridx++;
                        add(cashButton, gbc);
                        gbc.gridx++;
                        add(cardButton, gbc);

                        JButton payButton = new JButton("Pay");
                        gbc.gridx = 0;
                        gbc.gridy++;
                        gbc.gridwidth = GridBagConstraints.REMAINDER;
                        add(payButton, gbc);

                        payButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int totalCost = vehicle.getDays() * vehicle.getCost();

                                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) { // Open a new connection here
                                    conn.setAutoCommit(false); // Start transaction

                                    String insertQuery = "INSERT INTO RESERVATION (cID, vID, rSURNAME, rMODEL, rLICENSE_PLATE, rDAYS, rRESERVATION_DATE, rRETURN_DATE, rTOTAL_COST) "
                                            + "VALUES (?, ?, ?, ?, ?, ?, DATE_ADD(NOW(), INTERVAL 1 DAY), DATE_ADD(NOW(), INTERVAL ? DAY), ?)";
                                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                                        insertStmt.setInt(1, clientId);
                                        insertStmt.setInt(2, vehicle.getId());
                                        insertStmt.setString(3, surname);
                                        insertStmt.setString(4, vehicle.getModel());
                                        insertStmt.setString(5, vehicle.getLicensePlate());
                                        insertStmt.setInt(6, vehicle.getDays());
                                        insertStmt.setInt(7, vehicle.getDays() + 1);
                                        insertStmt.setInt(8, totalCost);

                                        insertStmt.executeUpdate();

                                        String updateVehicleStatusQuery = "UPDATE VEHICLE SET vSTATUS = 'rented' WHERE vID = ?";
                                        try (PreparedStatement updateStmt = conn.prepareStatement(updateVehicleStatusQuery)) {
                                            updateStmt.setInt(1, vehicle.getId());
                                            updateStmt.executeUpdate();
                                        }

                                        conn.commit();
                                        JOptionPane.showMessageDialog(null, "Payment Successful. Vehicle is now rented.");
                                        cardLayout.show(mainPanel, "Filters");

                                    } catch (SQLException ex) {
                                        conn.rollback();
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(null, "Error during payment: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(this, "Client not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
