/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sebas.lab07_refactoring;

/**
 *
 * @author jacks
 */
public class Customer {

    private String name;
    private PhoneNumber phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = new PhoneNumber(phone);
    }
    
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone.getNumber();
    }

    public void setPhone(String phone) {
        this.phone = new PhoneNumber(phone);
    }

    public boolean hasValidPhone() {
        return phone.isValid();
    }

    public String getFormattedPhone() {
        return phone.getFormattedPhone();
    }
}
