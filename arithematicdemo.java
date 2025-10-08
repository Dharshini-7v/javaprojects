package secondyear;

public class arithematicdemo {
    public static void main(String[] args) {
        int a = 10;
        int b = 0; // The divisor is zero.
        
        try {
            int result = a / b; // This line will throw an ArithmeticException
            System.out.println("The result is: " + result); // This line will be skipped
        } catch (ArithmeticException e) {
            System.out.println("An ArithmeticException occurred: " + e.getMessage());
        }
    }
}

