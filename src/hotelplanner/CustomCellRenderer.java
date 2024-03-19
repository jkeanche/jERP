/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelplanner;

import component.HotelPlanner;
import interfaces.PlannableAsset;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Codepal
 */
    // Custom cell renderer to customize cell appearance
 public class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof PlannableAsset) {
                PlannableAsset asset = (PlannableAsset) value;
                setText(asset.getClient());
                setBackground(Color.YELLOW);
                setToolTipText(asset.getClient() + " - " + asset.getName());
            } else {
                setText("");
                setBackground(Color.WHITE);
                setToolTipText("");
            }
            return c;
        }
    }