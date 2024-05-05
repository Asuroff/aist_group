package lesson1.task6;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ədədləri daxil edin və hər əməliyyatdan sonra '=' daxil edin:");
        double result = scanner.nextDouble();
        char operator;

        while (true) {
            operator = scanner.next().charAt(0);
            if (operator == '=') {
                break;
            }
            double operand = scanner.nextDouble();
            switch (operator) {
                case '+':
                    result += operand;
                    break;
                case '-':
                    result -= operand;
                    break;
                case '*':
                    result *= operand;
                    break;
                case '/':
                    if (operand != 0) {
                        result /= operand;
                    } else {
                        System.out.println("Sıfıra bölmə mümkün deyil!");
                    }
                    break;
                default:
                    System.out.println("Düzgün operator daxil edin: +, -, *, /");
                    break;
            }
        }

        System.out.println("Nəticə: " + result);
        scanner.close();
    }
}
