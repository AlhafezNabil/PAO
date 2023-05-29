package app;

import core.AppResult;
import core.errors.BaseError;
import entities.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuUI {
    private final Scanner scanner;

    public MenuUI() {
        scanner = new Scanner(System.in);
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    private void _printErrorMessage(AppResult result) {
        System.out.println("Error occurred: " + result.getError().getMessage());

    }

    private Boolean _checkYesAnswer(String answer) {
        String temp = answer.toLowerCase();
        return temp.equals("y") || temp.equals("Y") || temp.equals("Yes") || temp.equals("yes");
    }

    public void _close() {
        scanner.close();
    }

    public void printSuccess() {

        _printMessage("Task succeed");

    }

    public void printFailure() {

        _printMessage("Task Failed");

    }

    public void printFailure(BaseError baseError) {

        _printMessage("Task Failed, "+baseError.getMessage());

    }

    private void _printMessage(String message) {
        System.out.println(message);

    }

    public String getUserInputAsString(String message) {
        if (message != null && !message.isEmpty()) System.out.print(message);
        String temp = scanner.nextLine();
        return temp;
    }

    public void getUserInputAsNone(String message) {
        if (message != null && !message.isEmpty()) System.out.println(message);
        try {
            System.in.read();
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
        }
    }

    public int getUserInputAsInt() {
        try {
            var temp = scanner.nextInt();
            scanner.nextLine();
            return temp;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return getUserInputAsInt();
        }
    }

    public int getUserInputAsInt(String message) {
        System.out.print(message);
        try {
            var temp = scanner.nextInt();
            scanner.nextLine();
            return temp;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return getUserInputAsInt(message);
        }
    }

    public Sorting getSortAlphabetically() {
        String answer = getUserInputAsString("Sort them Alphabetically [Y/n]:");
        if (answer.isEmpty()) answer = "y";
        return new Sorting.Builder().alphabeticSorted(_checkYesAnswer(answer)).build();
    }

    public Sorting getSortOnDate() {
        String answer = getUserInputAsString("Sort them base on date [Y/n]:");
        if (answer.isEmpty()) answer = "y";
        return new Sorting.Builder().dateSorted(_checkYesAnswer(answer)).build();
    }

    public Sorting getAlphabeticallyAndSortOnDate() {
        String answer1 = getUserInputAsString("Sort them Alphabetically [Y/n]:");
        String answer2 = getUserInputAsString("Sort them base on date [Y/n]:");
        if (answer1.isEmpty()) answer1 = "y";
        if (answer2.isEmpty()) answer2 = "y";
        return new Sorting.Builder()
                .alphabeticSorted(_checkYesAnswer(answer2))
                .dateSorted(_checkYesAnswer(answer1)).build();
    }

    public void printMenu() {
        System.out.println("Library Management System: ");
        System.out.println("1. Library options");
        System.out.println("2. Section options");
        System.out.println("3. Author options");
        System.out.println("4. Book options");
        System.out.println("5. Reader options");
        System.out.println("6. Librarian options");
        System.out.println("0. Exit");
        System.out.println("Enter your option: ");
        System.out.print(">> ");
    }

    public void printLibraryMenu() {
        System.out.println("* Library options: ");
        System.out.println("    1- Add A library");
        System.out.println("    2- Get All libraries");
        System.out.println("    3- Get a library");
        System.out.println("    4- Update a library");
        System.out.println("    5- delete a library");
        System.out.println("    0- Return to main menu...");
        System.out.print("    >> ");
    }


    public Library readLibrary() {
        String name = getUserInputAsString("Enter library name: ");
        String address = getUserInputAsString("Enter library address: ");
        String phoneNumber = getUserInputAsString("Enter library phone number: ");
        return new Library(name, address, phoneNumber);

    }


    public void printLibraryResults(AppResult<List<Library>> result) {
        if (result.hasDataOnly()) {
            List<Library> libraries = result.getData();
            if (libraries.isEmpty()) {
                System.out.println("No libraries found.");
            } else {
                System.out.println("All Libraries:");
                for (Library library : libraries) {
                    System.out.println(library);
                }
            }
        } else {
            _printErrorMessage(result);
        }
    }

    public void printLibraryResult(AppResult<Library> result) {
        if (result.hasDataOnly()) {
            Library library = result.getData();
            System.out.println(library);

        } else {
            _printErrorMessage(result);
        }
    }


    //----------------------------------------- Sections
    public void printSectionMenu() {
        System.out.println("* Section options: ");
        System.out.println("    1- Add a section");
        System.out.println("    2- Get all sections");
        System.out.println("    3- Get a section");
        System.out.println("    4- Update a section");
        System.out.println("    5- Delete a section");
        System.out.println("    6- Get all sections in a library");
        System.out.println("    0- Return to main menu...");
        System.out.print("    >> ");
    }


    public Section readSection() {

        String name = getUserInputAsString("Enter section name: ");
        int libraryId = getUserInputAsInt("Enter library id for this section: ");
        Section section = new Section(name, libraryId);
        return section;

    }


    public void printSectionResults(AppResult<List<Section>> result) {

        if (result.hasDataOnly()) {
            List<Section> list = result.getData();
            if (list.isEmpty()) {
                System.out.println("No sections found.");
            } else {
                System.out.println("All sections:");
                for (Section item : list) {
                    System.out.println(item);
                }
            }
        } else {
            _printErrorMessage(result);
        }
    }

    public void printSectionResult(AppResult<Section> result) {
        if (result.hasDataOnly()) {
            Section section = result.getData();
            System.out.println(section);

        } else {
            _printErrorMessage(result);
        }
    }


    //----------------------------------------- Authors
    public void printAuthorMenu() {
        System.out.println("* Author options: ");
        System.out.println("    1- Add A author");
        System.out.println("    2- Get All authors");
        System.out.println("    3- Get a author");
        System.out.println("    4- Update a author");
        System.out.println("    5- delete a author");
        System.out.println("    0- Return to main menu...");
        System.out.print("    >> ");
    }


    public Author readAuthor() {
        int id = getUserInputAsInt("Enter author ID: ");
        String name = getUserInputAsString("Enter author name: ");
        String birthDate = getUserInputAsString("Enter author birth date (yyyy-MM-dd): ");
        return new Author(id, name, birthDate);
    }


    public void printAuthorResults(AppResult<List<Author>> result) {

        if (result.hasDataOnly()) {
            List<Author> list = result.getData();
            if (list.isEmpty()) {
                System.out.println("No data found.");
            } else {
                System.out.println("All authors:");
                for (var item : list) {
                    System.out.println(item);
                }
            }
        } else {
            _printErrorMessage(result);
        }
    }

    public void printAuthorResult(AppResult<Author> result) {
        if (result.hasDataOnly()) {
            var data = result.getData();
            System.out.println(data);

        } else {
            _printErrorMessage(result);
        }
    }

    public void printBookMenu() {
        System.out.println("* Book options: ");
        System.out.println("    1- Add a book");
        System.out.println("    2- Get all books");
        System.out.println("    3- Get a book");
        System.out.println("    4- Update a book");
        System.out.println("    5- Delete a book");
        System.out.println("    6- Find a book by author name");
        System.out.println("    7- Find a book by its name");
        System.out.println("    8- Borrow a book");
        System.out.println("    9- Return a book");
        System.out.println("    0- Return to main menu...");
        System.out.print("    >> ");
    }

    public Book readBook() {
        int id = getUserInputAsInt("Enter book ID: ");
        String title = getUserInputAsString("Enter book title: ");
        int authorId = getUserInputAsInt("Enter author ID: ");
        int sectionId = getUserInputAsInt("Enter section ID: ");
        String publicationDate = getUserInputAsString("Enter publication date (yyyy-MM-dd): ");
        int totalCopies = getUserInputAsInt("Enter total copies: ");
        int availableCopies = getUserInputAsInt("Enter available copies: ");

        return new Book(id, title, authorId, sectionId, publicationDate, totalCopies, availableCopies);
    }

    public void printBookResults(AppResult<List<Book>> result) {
        if (result.hasDataOnly()) {
            List<Book> list = result.getData();
            if (list.isEmpty()) {
                System.out.println("No data found.");
            } else {
                System.out.println("All books:");
                for (var item : list) {
                    System.out.println(item);
                }
            }
        } else {
            _printErrorMessage(result);
        }
    }

    public void printBookResult(AppResult<Book> result) {
        if (result.hasDataOnly()) {
            var data = result.getData();
            System.out.println(data);
        } else {
            _printErrorMessage(result);
        }
    }

    public void printReaderMenu() {
        System.out.println("* Reader options: ");
        System.out.println("    1- Add a reader");
        System.out.println("    2- Get all readers");
        System.out.println("    3- Get a reader");
        System.out.println("    4- Update a reader");
        System.out.println("    5- Delete a reader");
        System.out.println("    0- Return to main menu...");
        System.out.print("    >> ");
    }

    public Reader readReader() {
        int id = getUserInputAsInt("Enter reader ID: ");
        String name = getUserInputAsString("Enter reader name: ");
        String email = getUserInputAsString("Enter reader email: ");
        String phoneNumber = getUserInputAsString("Enter reader phone number: ");
        String location = getUserInputAsString("Enter reader location: ");
        String createdDate = getUserInputAsString("Enter reader created date (yyyy-MM-dd): ");

        return new Reader(id, name, email, phoneNumber, location, createdDate);
    }

    public void printReaderResults(AppResult<List<Reader>> result) {
        if (result.hasDataOnly()) {
            List<Reader> list = result.getData();
            if (list.isEmpty()) {
                System.out.println("No data found.");
            } else {
                System.out.println("All readers:");
                for (var item : list) {
                    System.out.println(item);
                }
            }
        } else {
            _printErrorMessage(result);
        }
    }

    public void printReaderResult(AppResult<Reader> result) {
        if (result.hasDataOnly()) {
            var data = result.getData();
            System.out.println(data);
        } else {
            _printErrorMessage(result);
        }
    }

    public void printLibrarianMenu() {
        System.out.println("* Librarian options: ");
        System.out.println("    1- Add a librarian");
        System.out.println("    2- Get all librarians");
        System.out.println("    3- Get a librarian");
        System.out.println("    4- Update a librarian");
        System.out.println("    5- Delete a librarian");
        System.out.println("    0- Return to main menu...");
        System.out.print("    >> ");
    }

    public Librarian readLibrarian() {
        int id = getUserInputAsInt("Enter librarian ID: ");
        String name = getUserInputAsString("Enter librarian name: ");
        String email = getUserInputAsString("Enter librarian email: ");
        String phoneNumber = getUserInputAsString("Enter librarian phone number: ");
        int libraryId = getUserInputAsInt("Enter library ID for the librarian: ");

        return new Librarian(id, name, email, phoneNumber, libraryId);
    }

    public void printLibrarianResults(AppResult<List<Librarian>> result) {
        if (result.hasDataOnly()) {
            List<Librarian> list = result.getData();
            if (list.isEmpty()) {
                System.out.println("No data found.");
            } else {
                System.out.println("All librarians:");
                for (var item : list) {
                    System.out.println(item);
                }
            }
        } else {
            _printErrorMessage(result);
        }
    }

    public void printLibrarianResult(AppResult<Librarian> result) {
        if (result.hasDataOnly()) {
            var data = result.getData();
            System.out.println(data);
        } else {
            _printErrorMessage(result);
        }
    }


}
