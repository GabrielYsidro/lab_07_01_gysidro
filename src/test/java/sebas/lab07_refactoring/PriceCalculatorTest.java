/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sebas.lab07_refactoring;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacks
 */
public class PriceCalculatorTest {

    /**
     * Test calculateTotal con precio mayor a 1000 - debe aplicar descuento del 15%
     */
    @Test
    public void testCalculateTotalConPrecioMayorA1000() {
        // Arrange
        // basePrice * quantity = 100 * 15 = 1500
        // discount = 1500 * 0.15 = 225
        // subtotal = 1500 - 225 = 1275
        // tax = 1275 * 0.18 = 229.5
        // total = 1275 + 229.5 = 1504.5
        PriceCalculator calculator = new PriceCalculator(100, 15);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 1504.5;
        assertEquals("El total debe incluir 15% de descuento para precios > 1000",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio entre 500 y 1000 - debe aplicar descuento del 10%
     */
    @Test
    public void testCalculateTotalConPrecioEntre500Y1000() {
        // Arrange
        // basePrice * quantity = 50 * 15 = 750
        // discount = 750 * 0.10 = 75
        // subtotal = 750 - 75 = 675
        // tax = 675 * 0.18 = 121.5
        // total = 675 + 121.5 = 796.5
        PriceCalculator calculator = new PriceCalculator(50, 15);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 796.5;
        assertEquals("El total debe incluir 10% de descuento para precios entre 500 y 1000",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio menor o igual a 500 - debe aplicar descuento del 5%
     */
    @Test
    public void testCalculateTotalConPrecioMenorIgualA500() {
        // Arrange
        // basePrice * quantity = 50 * 8 = 400
        // discount = 400 * 0.05 = 20
        // subtotal = 400 - 20 = 380
        // tax = 380 * 0.18 = 68.4
        // total = 380 + 68.4 = 448.4
        PriceCalculator calculator = new PriceCalculator(50, 8);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 448.4;
        assertEquals("El total debe incluir 5% de descuento para precios <= 500",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio exactamente 1000 - debe aplicar descuento del 10%
     */
    @Test
    public void testCalculateTotalConPrecioExactamente1000() {
        // Arrange
        // basePrice * quantity = 100 * 10 = 1000
        // discount = 1000 * 0.10 = 100 (No es > 1000, entonces aplica 10%)
        // subtotal = 1000 - 100 = 900
        // tax = 900 * 0.18 = 162
        // total = 900 + 162 = 1062
        PriceCalculator calculator = new PriceCalculator(100, 10);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 1062.0;
        assertEquals("El total con precio exactamente 1000 debe aplicar 10% de descuento",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio exactamente 500 - debe aplicar descuento del 5%
     */
    @Test
    public void testCalculateTotalConPrecioExactamente500() {
        // Arrange
        // basePrice * quantity = 100 * 5 = 500
        // discount = 500 * 0.05 = 25 (No es > 500, entonces aplica 5%)
        // subtotal = 500 - 25 = 475
        // tax = 475 * 0.18 = 85.5
        // total = 475 + 85.5 = 560.5
        PriceCalculator calculator = new PriceCalculator(100, 5);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 560.5;
        assertEquals("El total con precio exactamente 500 debe aplicar 5% de descuento",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio justo por encima de 1000 - debe aplicar descuento del 15%
     */
    @Test
    public void testCalculateTotalConPrecioJustoArriba1000() {
        // Arrange
        // basePrice * quantity = 100.1 * 10 = 1001
        // discount = 1001 * 0.15 = 150.15
        // subtotal = 1001 - 150.15 = 850.85
        // tax = 850.85 * 0.18 = 153.153
        // total = 850.85 + 153.153 = 1004.003
        PriceCalculator calculator = new PriceCalculator(100.1, 10);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 1004.003;
        assertEquals("El total con precio > 1000 debe aplicar 15% de descuento",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio justo por encima de 500 - debe aplicar descuento del 10%
     */
    @Test
    public void testCalculateTotalConPrecioJustoArriba500() {
        // Arrange
        // basePrice * quantity = 50.1 * 10 = 501
        // discount = 501 * 0.10 = 50.1
        // subtotal = 501 - 50.1 = 450.9
        // tax = 450.9 * 0.18 = 81.162
        // total = 450.9 + 81.162 = 532.062
        PriceCalculator calculator = new PriceCalculator(50.1, 10);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 532.062;
        assertEquals("El total con precio > 500 debe aplicar 10% de descuento",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con cantidad 1 - debe calcular correctamente
     */
    @Test
    public void testCalculateTotalConCantidadUno() {
        // Arrange
        // basePrice * quantity = 300 * 1 = 300
        // discount = 300 * 0.05 = 15
        // subtotal = 300 - 15 = 285
        // tax = 285 * 0.18 = 51.3
        // total = 285 + 51.3 = 336.3
        PriceCalculator calculator = new PriceCalculator(300, 1);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 336.3;
        assertEquals("El total con cantidad 1 debe calcularse correctamente",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precio base muy pequeño - debe aplicar descuento del 5%
     */
    @Test
    public void testCalculateTotalConPrecioMuyPequeno() {
        // Arrange
        // basePrice * quantity = 1 * 10 = 10
        // discount = 10 * 0.05 = 0.5
        // subtotal = 10 - 0.5 = 9.5
        // tax = 9.5 * 0.18 = 1.71
        // total = 9.5 + 1.71 = 11.21
        PriceCalculator calculator = new PriceCalculator(1, 10);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 11.21;
        assertEquals("El total con precio pequeño debe calcularse correctamente",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con cantidad grande - debe aplicar descuento del 15%
     */
    @Test
    public void testCalculateTotalConCantidadGrande() {
        // Arrange
        // basePrice * quantity = 20 * 100 = 2000
        // discount = 2000 * 0.15 = 300
        // subtotal = 2000 - 300 = 1700
        // tax = 1700 * 0.18 = 306
        // total = 1700 + 306 = 2006
        PriceCalculator calculator = new PriceCalculator(20, 100);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 2006.0;
        assertEquals("El total con cantidad grande debe aplicar 15% de descuento",
                expected, result, 0.01);
    }

    /**
     * Test calculateTotal con precios decimales - debe calcular correctamente
     */
    @Test
    public void testCalculateTotalConPreciosDecimales() {
        // Arrange
        // basePrice * quantity = 99.99 * 6 = 599.94
        // discount = 599.94 * 0.10 = 59.994
        // subtotal = 599.94 - 59.994 = 539.946
        // tax = 539.946 * 0.18 = 97.19028
        // total = 539.946 + 97.19028 = 637.13628
        PriceCalculator calculator = new PriceCalculator(99.99, 6);

        // Act
        double result = calculator.calculateTotal();

        // Assert
        double expected = 637.136;
        assertEquals("El total con precios decimales debe calcularse correctamente",
                expected, result, 0.01);
    }
}

