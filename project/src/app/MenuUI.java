package app;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUI {
    private final Scanner scanner;

    public MenuUI() {
        scanner = new Scanner(System.in);
    }

    public String getUserInputAsString(String message) {
        if (message != null && !message.isEmpty())
            System.out.print(message);
        return scanner.next();
    }

    public int getUserInputAsInt() {

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return getUserInputAsInt();
        }
    }
    public int getUserInputAsInt(String message) {
        if (message != null && !message.isEmpty())
            System.out.print(message);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return getUserInputAsInt(message);
        }
    }

    // Implement other methods for menu actions
    public void close() {
        scanner.close();
    }


    public void printMenu() {
        System.out.println("Library Management System");
        System.out.println("1. Add library");
        System.out.println("2. Add section");
        System.out.println("3. Add author");
        System.out.println("4. Add book");
        System.out.println("5. Add reader");
        System.out.println("6. Add librarian");
        System.out.println("7. Borrow a book");
        System.out.println("8. Return a book");
        System.out.println("9. List all sections");
        System.out.println("10. List all authors");
        System.out.println("11. List all books");
        System.out.println("12. List all readers");
        System.out.println("13. List all librarians");
        System.out.println("14. Find a book by title");
        System.out.println("15. Find books by author");
        System.out.println("16. Get all libraries");
        System.out.println("17. Get all sections in library");
        System.out.println("0. Exit");
        System.out.print("Enter your option: ");
    }

}
