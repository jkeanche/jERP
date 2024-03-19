/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Codepal
 */
public class Inventory {
   private Map<Product, Integer> stock;

    public Inventory() {
        this.stock = new HashMap<>();
    }

    // Add product to inventory
    public void addProduct(Product product, int quantity) {
        stock.put(product, quantity);
    }

    // Increase stock of a product
    public void increaseStock(Product product, int quantity) {
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    // Reduce stock of a product
    public void reduceStock(Product product, int quantity) {
        int currentQuantity = stock.getOrDefault(product, 0);
        if (currentQuantity >= quantity) {
            stock.put(product, currentQuantity - quantity);
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    // Get current stock of a product
    public int getStock(Product product) {
        return stock.getOrDefault(product, 0);
    } 
}
