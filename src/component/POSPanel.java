/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Codepal
 */
public class POSPanel extends javax.swing.JPanel {
    private JTextField barcodeField;
    private JTextField quantityField;
    private JButton addButton;
    private JTable cartTable;
    private DefaultTableModel cartModel;
    private JTextField totalField;
    private JButton calculateButton;
    private JPanel calculatorPanel;
    /**
     * Creates new form POSPanel
     */
    public POSPanel() {
        initComponents();
    setLayout(new MigLayout("", "[grow][shrink][grow]", "[][][][grow][]"));

        // Barcode input
        JLabel barcodeLabel = new JLabel("Barcode:");
        barcodeField = new JTextField(10);
        addButton = new JButton("Add");
        addButton.setMnemonic('A');
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        add(barcodeLabel, "align right");
        add(barcodeField, "wrap");
        add(addButton, "skip, span");

        // Quantity input
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(10);
        add(quantityLabel, "align right");
        add(quantityField, "wrap");

        // Cart table
        cartModel = new DefaultTableModel(new Object[]{"Product", "Price", "Quantity", "Total"}, 0);
        cartTable = new JTable(cartModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        add(cartScrollPane, "span, grow");

        // Total field
        JLabel totalLabel = new JLabel("Total:");
        totalField = new JTextField(10);
        totalField.setEditable(false);
        add(totalLabel, "align right");
        add(totalField, "wrap");

        // Calculator panel
        calculatorPanel = new JPanel(new MigLayout("wrap 4", "[][][][]", "[][][][]"));
        createCalculatorButtons();
        add(calculatorPanel, "span, grow");

        // Calculate total button
        calculateButton = new JButton("Calculate Total");
        calculateButton.setMnemonic('T');
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
        add(calculateButton, "align center, span");
    }

    private void createCalculatorButtons() {
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("=")) {
                        evaluateExpression();
                    } else {
                        barcodeField.setText(barcodeField.getText() + command);
                    }
                }
            });
            calculatorPanel.add(button, "grow");
        }
    }

    private void evaluateExpression() {
        String expression = barcodeField.getText();
        if (!expression.isEmpty()) {
            try {
                double result = evaluate(expression);
                barcodeField.setText(Double.toString(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid expression!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double evaluate(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }

    private void addProduct() {
        String barcode = barcodeField.getText();
        String quantity = quantityField.getText();

        // Add logic to retrieve product details using barcode and quantity
        // Add the product to the cart table and update total accordingly
        // Sample implementation:
        if (!barcode.isEmpty() && !quantity.isEmpty()) {
            // Example data
            String[] productData = {"Product " + barcode, "10.00", quantity, "" + (10.00 * Integer.parseInt(quantity))};
            cartModel.addRow(productData);
            updateTotal();
        }
    }

    private void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            String totalPrice = (String) cartModel.getValueAt(i, 3);
            total += Double.parseDouble(totalPrice.substring(1)); // Exclude "$"
        }
        totalField.setText(String.format("%.2f", total));
    }

    private void calculateTotal() {
        updateTotal();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Point of Sale System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new POSPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
