import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        char c = 't';
//        Character cc = c;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you name");
        String name = scanner.nextLine();

        System.out.println("Enter your age");
        int age = scanner.nextInt();
        System.out.println("Numbers are: ");

        for (int i = (age % 2 == 1) ? 1 : 0; i <= age; i += 2) {
            System.out.print(i);
            System.out.print(' ');
        }
        scanner.close();
    }
}

