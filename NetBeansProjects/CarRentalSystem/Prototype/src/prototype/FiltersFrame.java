package prototype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltersFrame extends JPanel {

    public FiltersFrame(JPanel mainPanel, CardLayout cardLayout, int clientId) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel mainTitle = new JLabel("Search By Using Filters");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(mainTitle, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel makeLabel = new JLabel("Make");
        gbc.gridx = 0;
        gbc.gridy++;
        add(makeLabel, gbc);

        JTextField makeTF = new JTextField(15);
        gbc.gridx = 1;
        add(makeTF, gbc);

        JLabel modelLabel = new JLabel("Model");
        gbc.gridx = 0;
        gbc.gridy++;
        add(modelLabel, gbc);

        JTextField modelTF = new JTextField(15);
        gbc.gridx = 1;
        add(modelTF, gbc);

        JLabel yearLabel = new JLabel("Year");
        gbc.gridx = 0;
        gbc.gridy++;
        add(yearLabel, gbc);

        JTextField yearTF = new JTextField(15);
        gbc.gridx = 1;
        add(yearTF, gbc);

        JLabel costLabel = new JLabel("Cost (MAX)");
        gbc.gridx = 0;
        gbc.gridy++;
        add(costLabel, gbc);

        JTextField costTF = new JTextField(15);
        gbc.gridx = 1;
        add(costTF, gbc);

        JLabel daysLabel = new JLabel("Days (MAX)");
        gbc.gridx = 0;
        gbc.gridy++;
        add(daysLabel, gbc);

        JTextField daysTF = new JTextField(15);
        gbc.gridx = 1;
        add(daysTF, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton searchBT = new JButton("Search");
        add(searchBT, gbc);

        gbc.gridy++;
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton logoutBT = new JButton("Logout");
        JButton currentReservationsBT = new JButton("Reservations");
        buttonsPanel.add(logoutBT);
        buttonsPanel.add(currentReservationsBT);
        add(buttonsPanel, gbc);

        searchBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String make = makeTF.getText();
                if (make.isEmpty()) {
                    make = null;
                }

                String model = modelTF.getText();
                if (model.isEmpty()) {
                    model = null;
                }

                String yearStr = yearTF.getText();
                Integer year;
                if (yearStr.isEmpty()) {
                    year = null;
                } else {
                    year = Integer.valueOf(yearStr);
                }

                String costStr = costTF.getText();
                Integer cost;
                if (costStr.isEmpty()) {
                    cost = null;
                } else {
                    cost = Integer.valueOf(costTF.getText());
                }

                String daysStr = daysTF.getText();
                Integer days;
                if (daysStr.isEmpty()) {
                    days = null;
                } else {
                    days = Integer.valueOf(daysTF.getText());
                }

                Filters filters = new Filters(make, model, year, cost, days);
                makeTF.setText("");
                modelTF.setText("");
                yearTF.setText("");
                costTF.setText("");
                daysTF.setText("");
                ResultsFrame resultsFrame = new ResultsFrame(cardLayout, mainPanel, filters, clientId);

                mainPanel.add(resultsFrame, "ResultsFrame");
                cardLayout.show(mainPanel, "ResultsFrame");
            }
        });

        logoutBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        //ΣΟΥ ΕΧΩ ΒΑΣΗ ΝΑ ΠΑΙΞΕΙΣ ΜΠΑΛΑ, ΣΟΥ ΠΕΡΝΑΩ ΤΟ clientId για να φτιάξεις το κατάλληλο query
        currentReservationsBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationsFrame reservationsFrame = new ReservationsFrame(clientId, cardLayout, mainPanel);
                mainPanel.add(reservationsFrame, "ReservationsFrame");
                cardLayout.show(mainPanel, "ReservationsFrame");
            }
        });
        
        revalidate();
        repaint();
    }
}
