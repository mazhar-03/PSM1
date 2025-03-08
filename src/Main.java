import java.util.Scanner;

public class Main {

    public static double taylor(double value, double sign, int power) {
        return sign * (Math.pow(value, power) / factorial(power));
    }

    public static double factorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double normalizeAngle(double value) {
        double normalizedValue = value % (2 * Math.PI);

        if (0 < normalizedValue && normalizedValue <= (Math.PI / 2)) {
            return normalizedValue;
        } else if ((Math.PI / 2) < normalizedValue && normalizedValue <= Math.PI) {
            return Math.PI - normalizedValue;
        } else if (Math.PI < normalizedValue && normalizedValue <= (3 * (Math.PI / 2))) {
            return Math.PI - normalizedValue;
        } else {
            normalizedValue = (2 * Math.PI) - normalizedValue;
            return normalizedValue - (2 * Math.PI);
        }
    }

    public static double calculateSin(double value) {
        value = normalizeAngle(value);

        double sum = value;
        double sign = -1.0;
        int power = 3;

        for (int i = 1; i <= 10; i++) {
            sum += taylor(value, sign, power);
            sign *= -1;
            power += 2;
        }
        return sum;
    }

    public static void calculateAndPrintSin(double x, boolean isDegrees) {
        double radian = isDegrees ? Math.toRadians(x) : x;
        double sinFromLibrary = Math.sin(radian);
        System.out.println("Library: " + sinFromLibrary);
        System.out.println("Calculated: " + calculateSin(radian));
        System.out.println("Err: " + Math.abs(calculateSin(radian) - sinFromLibrary));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter:\n- 1 for x in degrees\n- 2 for x in radians\n\nChoice: ");
        int choice = scanner.nextInt();

        System.out.print("Enter x: ");
        double x = scanner.nextDouble();

        if (choice == 1) {
            calculateAndPrintSin(x, false);
        } else if (choice == 2) {
            calculateAndPrintSin(x, true);
        } else {
            System.out.println("Invalid option - please try again!");
        }

        scanner.close();
    }
}
