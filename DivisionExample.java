package two;
 import java.util.Scanner;

public class DivisionExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter numerator: ");
        int numerator = sc.nextInt();
        System.out.print("Enter denominator: ");
        int denominator = sc.nextInt();

        try {
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Exception handled.");
        }

        sc.close();
        System.out.println("Name: Divya Bharathi D");  

        System.out.println("Reg no: 2117240020095");  
      
        
    }
}

