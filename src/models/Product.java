/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import enums.Unit;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Codepal
 */
public class Product {
  private String name;
    private Category category;
    private Brand brand;
    private Map<Variant, Integer> variants;
    private Unit unit;
    private double price;

    public Product(String name, Category category, Brand brand, Unit unit, double price) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.unit = unit;
        this.price = price;
        this.variants = new HashMap<>();
    }

    // Add variant to product
    public void addVariant(Variant variant, int quantity) {
        variants.put(variant, quantity);
    }

    // Getter for product name
    public String getName() {
        return name;
    }

    // Getter for category
    public Category getCategory() {
        return category;
    }

    // Getter for brand
    public Brand getBrand() {
        return brand;
    }

    // Getter for unit
    public Unit getUnit() {
        return unit;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Getter for variants
    public Map<Variant, Integer> getVariants() {
        return variants;
    }

    public static class Variant {

        public Variant() {
            
        }
    }

}
