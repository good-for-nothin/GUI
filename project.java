import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParkingSpotSystem extends JFrame {
    private JPanel parkingPanel;
    private JButton[] parkingSpots;
    private JLabel statusLabel;
    private JTextField carPlateField;
    private JButton addCarButton;
    private JButton removeCarButton;
    private JButton exitButton;

    private int numParkingSpots = 10;
    private boolean[] parkingSpotOccupied;

    public ParkingSpotSystem() {
        super("Parking Spot System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        parkingPanel = new JPanel();
        parkingPanel.setLayout(new GridLayout(2, numParkingSpots / 2));

        parkingSpots = new JButton[numParkingSpots];
        parkingSpotOccupied = new boolean[numParkingSpots];

        for (int i = 0; i < numParkingSpots; i++) {
            parkingSpots[i] = new JButton("Parking Spot " + (i + 1));
            parkingSpots[i].addActionListener(new ParkingSpotListener());
            parkingPanel.add(parkingSpots[i]);
        }

        statusLabel = new JLabel("Status: ");
        carPlateField = new JTextField(10);
        addCarButton = new JButton("Add Car");
        removeCarButton = new JButton("Remove Car");
        exitButton = new JButton("Exit");

        addCarButton.addActionListener(new AddCarListener());
        removeCarButton.addActionListener(new RemoveCarListener());
        exitButton.addActionListener(new ExitListener());

        JPanel controlPanel = new JPanel();
        controlPanel.add(statusLabel);
        controlPanel.add(carPlateField);
        controlPanel.add(addCarButton);
        controlPanel.add(removeCarButton);
        controlPanel.add(exitButton);

        add(parkingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private class ParkingSpotListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            int spotNumber = Integer.parseInt(button.getText().substring(13)) - 1;

            if (parkingSpotOccupied[spotNumber]) {
                // Remove car from parking spot
                parkingSpotOccupied[spotNumber] = false;
                button.setText("Parking Spot " + (spotNumber + 1));
                statusLabel.setText("Status: Car removed from spot " + (spotNumber + 1));
            } else {
                // Add car to parking spot
                parkingSpotOccupied[spotNumber] = true;
                button.setText("Occupied");
                statusLabel.setText("Status: Car added to spot " + (spotNumber + 1));
            }
        }
    }

    private class AddCarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String carPlate = carPlateField.getText();
            if (carPlate.isEmpty()) {
                statusLabel.setText("Status: Please enter car plate number");
            } else {
                // Add car to parking spot
                for (int i = 0; i < numParkingSpots; i++) {
                    if (!parkingSpotOccupied[i]) {
                        parkingSpotOccupied[i] = true;
                        parkingSpots[i].setText("Occupied");
                        statusLabel.setText("Status: Car added to spot " + (i + 1));
                        break;
                    }
                }
            }
        }
    }

    private class RemoveCarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String carPlate = carPlateField.getText();
            if (carPlate.isEmpty()) {
                statusLabel.setText("Status: Please enter car plate number");
            } else {
                // Remove car from parking spot
                for (int i = 0; i < numParkingSpots; i++) {
                    if (parkingSpotOccupied[i]) {
                        parkingSpotOccupied[i] = false;
                        parkingSpots[i].setText("Parking Spot " + (i + 1));
                        statusLabel.setText("Status: Car removed from spot " + (i + 1));
                        break;
                    }
                }
            }
        }
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ParkingSpotSystem();
    }
}