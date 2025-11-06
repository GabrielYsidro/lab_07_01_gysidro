/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sebas.lab07_refactoring;

/**
 *
 * @author jacks
 */
public class PriceCalculator {

    private double basePrice;
    private int quantity;

    public PriceCalculator(double basePrice, int quantity) {
        this.basePrice = basePrice;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return getSubtotal() + getTax();
    }

    private double getPrice() {
        return this.basePrice * this.quantity;
    }

    private double getDiscount() {
        if (getPrice() > 1000) {
            return getPrice() * 0.15;
        } else if (getPrice() > 500) {
            return getPrice() * 0.10;
        } else {
            return getPrice() * 0.05;
        }
    }

    private double getSubtotal() {
        return getPrice() - getDiscount();
    }

    private double getTax() {
        return getSubtotal() * 0.18;
    }
}
