/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Codepal
 */
public class LeadSourcePanel extends javax.swing.JPanel {
private JTextField sourceField;
    private JButton addButton;
    private JList<String> sourceList;
    private DefaultListModel<String> listModel;

    private List<String> leadSources;
    /**
     * Creates new form LeadSourcePanel
     */
    public LeadSourcePanel() {
        initComponents();
        setLayout(new BorderLayout());

        // Create input fields
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Lead Source:"));
        sourceField = new JTextField();
        inputPanel.add(sourceField);

        // Create add button
        addButton = new JButton("Add Source");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSource();
            }
        });

        // Create lead source list
        listModel = new DefaultListModel<>();
        sourceList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(sourceList);

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSource();
            }
        });
        buttonPanel.add(deleteButton);

        // Add components to panel
        add(inputPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.CENTER);
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize lead sources list
        leadSources = new ArrayList<>();
    }
    private void addSource() {
        String source = sourceField.getText().trim();
        if (!source.isEmpty()) {
            leadSources.add(source);
            listModel.addElement(source);
            sourceField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a lead source.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSource() {
        int selectedIndex = sourceList.getSelectedIndex();
        if (selectedIndex != -1) {
            leadSources.remove(selectedIndex);
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a lead source to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Lead Source Management");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new LeadSourcePanel());
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
