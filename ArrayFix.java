package secondyear;

public class ArrayFix {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30};

        // Fix with a conditional check
        int index = 2; // A valid index
        if (index >= 0 && index < numbers.length) {
            System.out.println(numbers[index]);
        } else {
            System.out.println("Invalid index: " + index);
        }
        
        // Fix with a try-catch block
        try {
            System.out.println(numbers[3]); // Invalid index
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Caught an exception: " + e.getMessage());
        }
    }


}
