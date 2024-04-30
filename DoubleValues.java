import  java.util.Scanner;

public class DoubleValues {
    public static void main(String[] args) {
        // Initialize the Scanner
        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[20];
        // the crux of the while loop is initiating a count, as a python user with a
        // specialization in scrapping and list comprehension
        int count = 0;

        // Loop to collect double values
        while (count < 20) {
            double input = scanner.nextDouble();

            // Check for quit signal
            if (input == 99999) {
                break;
            }
            // As a datasci major, who uses pandas, this style of permutation of data is very similar
            numbers[count] = input;
            //note the count ++ is similar to += in python
            count++;
        }
        // Now in the instructions told us to check if no number are entered
        if (count == 0){
            // add a print statement
            System.out.println("Nothing Entered");
        } else {
            System.out.println("You entered:");
            // make sure we have no colon after the for loop, something I didn't understand
            for (int i = 0; i < count; i++) {
                System.out.println(numbers[i]);
            }
        }
        // close scanner
        scanner.close();
    }
}