package sebas.lab07_refactoring;

public class PhoneNumber {
    private final String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isValid() {
        return isValid(this.number);
    }

    private boolean isValid(String number) {
        return number != null && number.matches("\\d{9}");
    }

    public String getFormattedPhone() {
        if (!isValid()) {
            return number;
        }
        return number.substring(0, 3) + "-" +
               number.substring(3, 6) + "-" +
               number.substring(6);
    }
}
