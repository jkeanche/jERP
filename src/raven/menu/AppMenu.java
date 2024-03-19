/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.menu;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author Codepal
 */
public class AppMenu {
    private String label;
    private JComponent defaultPanel;
    private List<AppMenu> subMenuItems;

    public AppMenu(String label, JComponent defaultPanel, List<AppMenu> subMenuItems) {
        this.label = label;
        this.defaultPanel = defaultPanel;
        this.subMenuItems = subMenuItems == null ? new ArrayList<>(): subMenuItems;
        
    }
    
    public void addSubmenu(AppMenu appMenu){
        this.subMenuItems.add(appMenu);
    }
    
    

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public JComponent getDefaultPanel() {
        return defaultPanel;
    }

    public void setDefaultPanel(JComponent defaultPanel) {
        this.defaultPanel = defaultPanel;
    }

    public List<AppMenu> getSubMenuItems() {
        return subMenuItems;
    }

    public void setSubMenuItems(List<AppMenu> subMenuItems) {
        this.subMenuItems = subMenuItems;
    }
    
    
}
