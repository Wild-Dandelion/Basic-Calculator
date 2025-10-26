import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// A simple calculator using Java Swing.
// Supports +, -, *, / operations with clear and equals functionality.
public class Calculator extends JFrame implements ActionListener {
    // Text field to display input and result
    private JTextField display;

    // To store current operator and operands
    private String operator;
    private double num1, num2, result;

    // Constructor to set up the GUI
    public Calculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Display field (non-editable)
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Panel for buttons with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        // Button labels in order
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // Add buttons to the panel
        for(String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Event handling for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // If number button is pressed, append to display
        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        }
        // Clear button
        else if (command.equals("C")) {
            display.setText("");
            operator = null;
            num1 = num2 = result = 0;
        }
        // Operator buttons
        else if (command.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        // Equals button
        else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": 
                        if (num2 == 0) {
                            display.setText("Cannot divide by 0");
                            return;
                        }
                        result = num1 / num2; 
                        break;
                }

                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}