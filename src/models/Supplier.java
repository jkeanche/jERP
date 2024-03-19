/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Codepal
 */
// Class representing a supplier
public class Supplier {
    private String name, code;
    private String contact;
    private SupplierGroup group;

    public Supplier(String name, String contact, SupplierGroup group) {
        this.name = name;
        this.contact = contact;
        this.group = group;
    }

    // Getter for supplier name
    public String getName() {
        return name;
    }

    // Getter for contact information
    public String getContact() {
        return contact;
    }

    // Getter for supplier group
    public SupplierGroup getGroup() {
        return group;
    }
}

