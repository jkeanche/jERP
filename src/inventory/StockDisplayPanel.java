/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package inventory;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author Codepal
 */
public class StockDisplayPanel extends javax.swing.JPanel {
   private JTable stockTable;
    private DefaultTableModel tableModel;
    /**
     * Creates new form StockDisplayPanel
     */
    public StockDisplayPanel() {
        initComponents();
        setLayout(new BorderLayout());

        // Create toolbar with buttons
        JToolBar toolBar = new JToolBar();
        JButton addStockButton = new JButton("Add Stock");
        JButton editProductButton = new JButton("Edit Product");
        JButton adjustStockButton = new JButton("Adjust Stock");
        JButton addVariantButton = new JButton("Add Variant");

        // Add buttons to toolbar
        toolBar.add(addStockButton);
        toolBar.add(editProductButton);
        toolBar.add(adjustStockButton);
        toolBar.add(addVariantButton);

        // Add toolbar to north
        add(toolBar, BorderLayout.NORTH);

        // Create panel for displaying stock
        JPanel stockPanel = new JPanel(new MigLayout());

        // Example: Adding labels to display stock
        JLabel productNameLabel = new JLabel("Product Name");
        JLabel stockLabel = new JLabel("Stock");

        // Example: Adding labels with MigLayout constraints
        stockPanel.add(productNameLabel, "cell 0 0, gapx 10");
        stockPanel.add(stockLabel, "cell 1 0, gapx 10");

        // Example: Adding sample stock data
        JLabel productName1 = new JLabel("Product 1");
        JLabel stock1 = new JLabel("100");
        JLabel productName2 = new JLabel("Product 2");
        JLabel stock2 = new JLabel("50");

        // Example: Adding sample stock data with MigLayout constraints
        stockPanel.add(productName1, "cell 0 1, gapx 10");
        stockPanel.add(stock1, "cell 1 1, gapx 10");
        stockPanel.add(productName2, "cell 0 2, gapx 10");
        stockPanel.add(stock2, "cell 1 2, gapx 10");

        // Add stock panel to center
        add(new JScrollPane(stockPanel), BorderLayout.CENTER);

        // Example: Adding search functionality
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        // Example: Adding search components with MigLayout constraints
        JPanel searchPanel = new JPanel(new MigLayout());
        searchPanel.add(searchField, "gapx 10");
        searchPanel.add(searchButton, "gapx 10");

        // Add search panel to south
//        add(searchPanel, BorderLayout.SOUTH);
       String[] columns = {"Product Code", "Variant Price", "Available Quantity"};
        tableModel = new DefaultTableModel(columns, 0);

        // Create JTable with table model
        stockTable = new JTable(tableModel);

        // Fetch dummy stock data and populate the table
        List<Object[]> dummyStockData = getDummyStockData();
        for (Object[] rowData : dummyStockData) {
            tableModel.addRow(rowData);
        }

        // Add table to scroll pane and add to center
        JScrollPane scrollPane = new JScrollPane(stockTable);
        add(scrollPane, BorderLayout.CENTER);
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
  private List<Object[]> getDummyStockData() {
        List<Object[]> dummyData = new ArrayList<>();
        dummyData.add(new Object[]{"P001", 50.0, 100});
        dummyData.add(new Object[]{"P002", 30.0, 75});
        dummyData.add(new Object[]{"P003", 20.0, 150});
        return dummyData;
    }
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Stock Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new StockDisplayPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
