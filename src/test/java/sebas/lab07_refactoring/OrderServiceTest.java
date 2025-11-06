/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sebas.lab07_refactoring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author jacks
 */
public class OrderServiceTest {

    private OrderService orderService;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        orderService = new OrderService();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Test processOrder con orden válida - debe calcular total correctamente
     */
    @Test
    public void testProcessOrderConOrdenValida() {
        // Arrange
        Customer customer = new Customer("Juan Pérez", "987654321");
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Laptop", 1000.0, 1),
                new OrderItem("Mouse", 25.0, 2)
        );
        Order order = new Order("ORD-001", customer, items);

        // Act
        orderService.processOrder(order);

        // Assert
        // Subtotal = 1000 + (25 * 2) = 1050
        // Tax = 1050 * 0.18 = 189
        // Total = 1050 + 189 = 1239
        double expectedTotal = 1239.0;
        assertEquals("El total debe ser el subtotal más el 18% de impuestos",
                expectedTotal, order.getTotal(), 0.01);
    }

    /**
     * Test processOrder con orden nula - debe lanzar IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testProcessOrderConOrdenNula() {
        // Act & Assert
        orderService.processOrder(null);
    }

    /**
     * Test processOrder con orden sin items - debe lanzar IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testProcessOrderConOrdenSinItems() {
        // Arrange
        Customer customer = new Customer("María López", "987654321");
        Order order = new Order("ORD-002", customer, new ArrayList<>());

        // Act & Assert
        orderService.processOrder(order);
    }

    /**
     * Test processOrder con orden sin customer - debe lanzar IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testProcessOrderConOrdenSinCustomer() {
        // Arrange
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Teclado", 50.0, 1)
        );
        Order order = new Order("ORD-003", null, items);

        // Act & Assert
        orderService.processOrder(order);
    }

    /**
     * Test processOrder debe imprimir información de la orden
     */
    @Test
    public void testProcessOrderImprimeInformacionOrden() {
        // Arrange
        Customer customer = new Customer("Pedro Gómez", "912345678");
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Monitor", 300.0, 1)
        );
        Order order = new Order("ORD-004", customer, items);

        // Act
        orderService.processOrder(order);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue("Debe imprimir mensaje de guardado",
                output.contains("Saving order to database..."));
        assertTrue("Debe imprimir el ID de la orden",
                output.contains("Order ID: ORD-004"));
        assertTrue("Debe imprimir el total correcto",
                output.contains("Total: 354.0"));
    }

    /**
     * Test processOrder debe imprimir confirmación al cliente
     */
    @Test
    public void testProcessOrderImprimeConfirmacionCliente() {
        // Arrange
        Customer customer = new Customer("Ana Torres", "923456789");
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Tablet", 500.0, 1)
        );
        Order order = new Order("ORD-005", customer, items);

        // Act
        orderService.processOrder(order);

        // Assert
        String output = outputStreamCaptor.toString();
        assertTrue("Debe imprimir el teléfono formateado del cliente",
                output.contains("Sending confirmation message to: 923-456-789"));
        assertTrue("Debe imprimir el nombre del cliente",
                output.contains("Dear Ana Torres"));
        assertTrue("Debe imprimir confirmación con el ID de la orden",
                output.contains("Your order #ORD-005 has been confirmed."));
        assertTrue("Debe imprimir el total en el mensaje de confirmación",
                output.contains("Total: $590.0"));
    }

    /**
     * Test processOrder con múltiples items - debe calcular total correctamente
     */
    @Test
    public void testProcessOrderConMultiplesItems() {
        // Arrange
        Customer customer = new Customer("Carlos Ruiz", "934567890");
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Producto A", 100.0, 2),  // 200
                new OrderItem("Producto B", 50.0, 3),   // 150
                new OrderItem("Producto C", 25.0, 4)    // 100
        );
        Order order = new Order("ORD-006", customer, items);

        // Act
        orderService.processOrder(order);

        // Assert
        // Subtotal = 200 + 150 + 100 = 450
        // Tax = 450 * 0.18 = 81
        // Total = 450 + 81 = 531
        double expectedTotal = 531.0;
        assertEquals("El total debe ser calculado correctamente con múltiples items",
                expectedTotal, order.getTotal(), 0.01);
    }

    /**
     * Test processOrder con item de precio cero - debe calcular total correctamente
     */
    @Test
    public void testProcessOrderConItemPrecioCero() {
        // Arrange
        Customer customer = new Customer("Laura Díaz", "945678901");
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Producto gratuito", 0.0, 1),
                new OrderItem("Producto normal", 100.0, 1)
        );
        Order order = new Order("ORD-007", customer, items);

        // Act
        orderService.processOrder(order);

        // Assert
        // Subtotal = 0 + 100 = 100
        // Tax = 100 * 0.18 = 18
        // Total = 100 + 18 = 118
        double expectedTotal = 118.0;
        assertEquals("El total debe ser calculado correctamente incluso con items de precio cero",
                expectedTotal, order.getTotal(), 0.01);
    }

    /**
     * Test processOrder con decimales - debe calcular total correctamente
     */
    @Test
    public void testProcessOrderConPreciosDecimales() {
        // Arrange
        Customer customer = new Customer("Roberto Vega", "956789012");
        List<OrderItem> items = Arrays.asList(
                new OrderItem("Producto 1", 19.99, 3),  // 59.97
                new OrderItem("Producto 2", 12.50, 2)   // 25.00
        );
        Order order = new Order("ORD-008", customer, items);

        // Act
        orderService.processOrder(order);

        // Assert
        // Subtotal = 59.97 + 25.00 = 84.97
        // Tax = 84.97 * 0.18 = 15.2946
        // Total = 84.97 + 15.2946 = 100.2646
        double expectedTotal = 100.2646;
        assertEquals("El total debe ser calculado correctamente con precios decimales",
                expectedTotal, order.getTotal(), 0.01);
    }
}

