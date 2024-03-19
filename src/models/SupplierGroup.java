/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Codepal
 */
import java.util.ArrayList;
import java.util.List;

// Class representing a supplier group
public class SupplierGroup {
    private String groupName;
    private List<Supplier> suppliers;

    public SupplierGroup(String groupName) {
        this.groupName = groupName;
        this.suppliers = new ArrayList<>();
    }

    // Add supplier to the group
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    // Remove supplier from the group
    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }

    // Getter for group name
    public String getGroupName() {
        return groupName;
    }

    // Getter for list of suppliers
    public List<Supplier> getSuppliers() {
        return suppliers;
    }
}


