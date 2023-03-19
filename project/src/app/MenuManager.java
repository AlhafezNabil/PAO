package app;

import db.LibraryDBManager;
import entities.*;

import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private final LibraryDBManager dbManager;
    private final Scanner scanner;

    public MenuManager() {
        dbManager = new LibraryDBManager();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int option;
        do {
            printMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1 -> addLibrary();
                case 2 -> addSection();
                case 3 -> addAuthor();
                case 4 -> addBook();
                case 5 -> addReader();
                case 6 -> addLibrarian();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> listSections();
                case 10 -> listAuthors();
                case 11 -> listBooks();
                case 12 -> listReaders();
                case 13 -> listLibrarians();
                case 14 -> findBookByTitle();
                case 15 -> findBooksByAuthor();
                case 16 -> getAllLibraries();
                case 17 -> getAllSectionsInLibrary();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Please try again.");
            }

        } while (option != 0);
    }

    private void printMenu() {
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

    private void addLibrary() {
        System.out.print("Enter library ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter library name: ");
        String name = scanner.nextLine();
        System.out.print("Enter library address: ");
        String address = scanner.nextLine();
        System.out.print("Enter library phone number: ");
        String phoneNumber = scanner.nextLine();
        dbManager.addLibrary(id, name, address, phoneNumber);
    }

    private void addSection() {
        System.out.print("Enter section ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter section name: ");
        String name = scanner.nextLine();
        System.out.print("Enter library ID for the section: ");
        int libraryId = scanner.nextInt();
        dbManager.addSection(id, name, libraryId);
    }

    private void addAuthor() {
        System.out.print("Enter author ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author birth date (yyyy-MM-dd): ");
        String birthDate = scanner.nextLine();
        dbManager.addAuthor(id, name, birthDate);
    }

    private void addBook() {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author ID: ");
        int authorId = scanner.nextInt();
        System.out.print("Enter book section ID: ");
        int sectionId = scanner.nextInt();
        System.out.print("Enter publication year: ");
        int publicationYear = scanner.nextInt();
        dbManager.addBook(id, title, authorId, sectionId, publicationYear);
    }

    private void addReader() {
        System.out.print("Enter reader ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter reader name: ");
        String name = scanner.nextLine();
        System.out.print("Enter reader email: ");
        String email = scanner.nextLine();
        System.out.print("Enter reader phone number: ");
        String phoneNumber = scanner.nextLine();
        dbManager.addReader(id, name, email, phoneNumber);
    }

    private void addLibrarian() {
        System.out.print("Enter librarian ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter librarian name: ");
        String name = scanner.nextLine();
        System.out.print("Enter librarian email: ");
        String email = scanner.nextLine();
        System.out.print("Enter librarian phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter library ID for the librarian: ");
        int libraryId = scanner.nextInt();
        dbManager.addLibrarian(id, name, email, phoneNumber, libraryId);
    }

    private void borrowBook() {
        System.out.print("Enter borrowing ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter reader ID: ");
        int readerId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter borrowing date (yyyy-MM-dd): ");
        String borrowingDate = scanner.nextLine();
        System.out.print("Enter due date (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();
        dbManager.borrowBook(id, readerId, bookId, borrowingDate, dueDate);
    }

    private void returnBook() {
        System.out.print("Enter borrowing ID: ");
        int id = scanner.nextInt();
        dbManager.returnBook(id);
    }

    private void listSections() {
        List<Section> sections = dbManager.getAllSections();
        System.out.println("All Sections:");
        for (Section section : sections) {
            System.out.println(section.getId() + "\t" + section.getName() + "\t" + section.getLibraryId());
        }
    }

    private void listAuthors() {
        List<Author> authors = dbManager.getAllAuthors();
        System.out.println("All Authors:");
        for (Author author : authors) {
            System.out.println(author.getId() + "\t" + author.getName() + "\t" + author.getBirthDate());
        }
    }

    private void listBooks() {
        List<Book> books = dbManager.getAllBooks();
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getAvailableCopies());
        }
    }

    private void listReaders() {
        List<Reader> readers = dbManager.getAllReaders();
        System.out.println("All Readers:");
        for (Reader reader : readers) {
            System.out.println(reader.getId() + "\t" + reader.getName() + "\t" + reader.getEmail() + "\t" + reader.getPhoneNumber());
        }
    }

    private void listLibrarians() {
        List<Librarian> librarians = dbManager.getAllLibrarians();
        System.out.println("All Librarians:");
        for (Librarian librarian : librarians) {
            System.out.println(librarian.getId() + "\t" + librarian.getName() + "\t" + librarian.getEmail() + "\t" + librarian.getPhoneNumber() + "\t" + librarian.getLibraryId());
        }
    }

    private void findBookByTitle() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        List<Book> books = dbManager.getBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No books found with title \"" + title + "\"");
        } else {
            System.out.println("Books with title \"" + title + "\":");
            for (Book book : books) {
                System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getPublicationYear());
            }
        }
    }

    private void findBooksByAuthor() {
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        List<Book> books = dbManager.getBooksByAuthor(authorName);
        if (books.isEmpty()) {
            System.out.println("No books found by author \"" + authorName + "\"");
        } else {
            System.out.println("Books by author \"" + authorName + "\":");
            for (Book book : books) {
                System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getPublicationYear());
            }
        }
    }

    private void getAllLibraries() {
        List<Library> libraries = dbManager.getAllLibraries();
        System.out.println("All Libraries:");
        for (Library library : libraries) {
            System.out.println(library.getId() + "\t" + library.getName() + "\t" + library.getAddress() + "\t" + library.getPhoneNumber());
        }
    }

    private void getAllSectionsInLibrary() {
        System.out.print("Enter library ID: ");
        int libraryId = scanner.nextInt();
        List<Section> sections = dbManager.getAllSectionsInLibrary(libraryId);
        if (sections.isEmpty()) {
            System.out.println("No sections found for library with ID " + libraryId);
        } else {
            System.out.println("All sections in library with ID " + libraryId + ":");
            for (Section section : sections) {
                System.out.println(section.getId() + "\t" + section.getName());
            }
        }
    }
}
