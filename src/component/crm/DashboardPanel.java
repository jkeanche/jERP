/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component.crm;

/**
 *
 * @author Codepal
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import raven.menu.AppMenu;

public class DashboardPanel extends JPanel {
    private List<AppMenu> menu;
    private JPanel centerPanel;
    
    private AppName appName;
    
    public enum AppName{
       iCRM, iHR, LedgerLogic, iFarm, iSchool
    }


    public DashboardPanel(List<AppMenu> menu) {
        this.menu = menu;
        this.centerPanel = new JPanel(new BorderLayout());
        initializeUI();
    }
    
    public DashboardPanel(AppName appName) {
        this.centerPanel = new JPanel(new BorderLayout());
        loadMenu(appName);
        initializeUI();
    }
    
    
    private void loadMenu(AppName appName){
        this.menu = new ArrayList<>();
        if(appName.name().equalsIgnoreCase("crm")){
            AppMenu cb = new AppMenu("Customer Board", new CustomerPanel(), null);
            AppMenu tc = new AppMenu("Task Calender", new TaskCalender(), null);
            AppMenu cust = new AppMenu("Customers", new CustomerPanel(), null);
            AppMenu prod = new AppMenu("Products", new ProductsPanel(), null);
            AppMenu qt = new AppMenu("Quotes", new QuotesPanel(), null);
            AppMenu tasks = new AppMenu("Tasks", new TasksPanel(), null);
            AppMenu settingsPanel = new AppMenu("Settings", new SettingsPanel(), null);

            AppMenu settingsSubMenu = new AppMenu("Custom Fields", new CustomerPanel(), null); // change panel
            settingsPanel.addSubmenu(settingsSubMenu);
            settingsPanel.addSubmenu(prod);
              settingsSubMenu = new AppMenu("Lead Sources", new CustomerPanel(), null); // change panel
            settingsPanel.addSubmenu(prod);
              settingsSubMenu = new AppMenu("Pipeline Stages", new CustomerPanel(), null); // change panel
            settingsPanel.addSubmenu(prod);

            settingsSubMenu = new AppMenu("Tags", new CustomerPanel(), null); // change panel
            
            menu.add(cb);
            menu.add(tc);
            menu.add(cust);
            menu.add(prod);menu.add(qt);menu.add(tasks);
            menu.add(settingsPanel);
            
        }
       
    }
    
    public boolean isAppReady(){
        return !this.menu.isEmpty();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Create a toolbar for menu items
        JToolBar toolBar = new JToolBar();
        for (AppMenu menuItem : menu) {
            JButton button = new JButton(menuItem.getLabel());
            button.addActionListener(new MenuButtonListener(menuItem));
            toolBar.add(button);
        }
        add(toolBar, BorderLayout.NORTH);

        // Display the default panel of the first menu item in the center
        if (!menu.isEmpty()) {
            centerPanel.add(menu.get(0).getDefaultPanel(), BorderLayout.CENTER);
        }
        add(centerPanel, BorderLayout.CENTER);
    }

    private class MenuButtonListener implements ActionListener {
        private AppMenu menuItem;

        public MenuButtonListener(AppMenu menuItem) {
            this.menuItem = menuItem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            centerPanel.add(menuItem.getDefaultPanel(), BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();

            // If the menu item has submenus, show a context menu
            if (!menuItem.getSubMenuItems().isEmpty()) {
                JPopupMenu popupMenu = new JPopupMenu();
                for (AppMenu subMenu : menuItem.getSubMenuItems()) {
                    JMenuItem subMenuItem = new JMenuItem(subMenu.getLabel());
                    subMenuItem.addActionListener(new SubMenuActionListener(subMenu));
                    popupMenu.add(subMenuItem);
                }
                popupMenu.show((Component) e.getSource(), 0, ((Component) e.getSource()).getHeight());
            }
        }
    }

    private class SubMenuActionListener implements ActionListener {
        private AppMenu subMenu;

        public SubMenuActionListener(AppMenu subMenu) {
            this.subMenu = subMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            centerPanel.removeAll();
            centerPanel.add(subMenu.getDefaultPanel(), BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        List<AppMenu> subMenus = List.of(
                new AppMenu("SubMenu1", new JPanel(), null),
                new AppMenu("SubMenu2", new JPanel(), null)
        );

        AppMenu mainMenu = new AppMenu("MainMenu", new JPanel(), subMenus);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Menu Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new DashboardPanel(List.of(mainMenu)));
            frame.setSize(400, 300);
            frame.setVisible(true);
        });
    }
}

