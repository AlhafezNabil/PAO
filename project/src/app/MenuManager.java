package app;

import core.AppResult;
import db.DBManager;
import entities.*;
import services.author.IAuthor;
import services.author.ImplAuthor;
import services.book.IBook;
import services.book.ImplBook;
import services.librarian.ILibrarian;
import services.librarian.ImplLibrarian;
import services.library.ILibrary;
import services.library.ImplLibrary;
import services.reader.IReader;
import services.reader.ImplReader;
import services.sections.ISection;
import services.sections.ImplSection;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuManager {

    //    private final DBManager dbManager;
    private final Scanner scanner;
    private final IAuthor authorService = ImplAuthor.getInstance();
    private final IBook bookService = ImplBook.getInstance();
    private final ILibrarian librarianService = ImplLibrarian.getInstance();
    private final ILibrary libraryService = ImplLibrary.getInstance();
    private final IReader readerService = ImplReader.getInstance();

    private final ISection sectionService = ImplSection.getInstance();


    public MenuManager() {
//        dbManager = DBManager.getInstance();
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
        Library library = new Library(id, name, address, phoneNumber);
        libraryService.createLibrary(library);
    }

    private void addSection() {
        System.out.print("Enter section ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter section name: ");
        String name = scanner.nextLine();
        System.out.print("Enter library ID for the section: ");
        int libraryId = scanner.nextInt();
        Section section = new Section(id, name, libraryId);
        sectionService.createSection(section);
    }

    private void addAuthor() {
        System.out.print("Enter author ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author birth date (yyyy-MM-dd): ");
        String birthDate = scanner.nextLine();
        Author author = new Author(id, name, birthDate);
        authorService.createAuthor(author);
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
        String publicationYear = scanner.nextLine();
        System.out.print("Enter total Copies: ");
        int totalCopies = scanner.nextInt();
        System.out.print("Enter available Copies: ");
        int availableCopies = scanner.nextInt();
//        (int id, String title, int authorId, int sectionId, String publicationYear, int totalCopies, int availableCopies)
        Book book = new Book(id, title, authorId, sectionId, publicationYear, totalCopies, availableCopies);
        bookService.createBook(book);
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
        System.out.print("Enter reader location: ");
        String location = scanner.nextLine();
        System.out.print("Enter reader created Date: ");
        String createdDate = scanner.nextLine();
        Reader reader = new Reader(id, name, email, phoneNumber, location, createdDate);
        readerService.createReader(reader);

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
        Librarian librarian = new Librarian(id, name, email, phoneNumber, libraryId);
        librarianService.createLibrarian(librarian);
    }

    private void borrowBook() {
        System.out.print("Enter borrowing ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        System.out.print("Enter reader ID: ");
        int readerId = scanner.nextInt();

        System.out.print("Enter borrowing date (yyyy-MM-dd): ");
        String borrowingDate = scanner.nextLine();

        System.out.print("Enter due date (yyyy-MM-dd): ");
        String dueDate = scanner.nextLine();

        Borrowing borrowing = new Borrowing(id, bookId, readerId, borrowingDate, dueDate);
        bookService.borrowBook(borrowing);
    }

    private void returnBook() {
        System.out.print("Enter borrowing ID: ");
        int id = scanner.nextInt();
        bookService.returnBorrowing(id);
    }

    private void listSections() {
        System.out.println("Do you want them sorted alphabetically default yes, your answer [Y/n]:");
        String answer = scanner.nextLine();
        boolean isAlphabetSorted = !answer.toLowerCase().startsWith("n");
        Sorting sorting = new Sorting(null, isAlphabetSorted);
        AppResult<List<Section>> result = sectionService.getSectionsList(sorting);
        if (result.hasDataOnly()) {
            System.out.println("All Sections:");

            for (Section section : result.getData()) {
                System.out.println(section.getId() + "\t" + section.getName() + "\t" + section.getLibraryId());
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void listAuthors() {
        System.out.println("Do you want them sorted birth date of the author default yes, your answer [Y/n]:");
        String answer = scanner.nextLine();
        boolean isDateSorted = !answer.toLowerCase().startsWith("n");
        Sorting sorting = new Sorting(isDateSorted, false);
        AppResult<List<Author>> result = authorService.getAuthorsList(sorting);
        if (result.hasDataOnly()) {
            System.out.println("All Authors:");
            for (Author author : result.getData()) {
                System.out.println(author.getId() + "\t" + author.getName() + "\t" + author.getBirthDate());
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void listBooks() {

        System.out.println("Do you want them sorted alphabetically default yes, your answer [Y/n]:");
        String answer = scanner.nextLine();

        boolean isAlphabetSorted = !answer.toLowerCase().startsWith("n");
        Sorting sorting = new Sorting(null, isAlphabetSorted);
        AppResult<List<Book>> result = bookService.getBooksList(sorting);
        if (result.hasDataOnly()) {
            System.out.println("All Books:");
            for (Book book : result.getData()) {
                System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getAvailableCopies());
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void listReaders() {
        AppResult<List<Reader>> result = readerService.getReadersList(new Sorting(null, null));
        if (result.hasDataOnly()) {
            System.out.println("All Readers:");
            for (Reader reader : result.getData()) {
                System.out.println(reader.getId() + "\t" + reader.getName() + "\t" + reader.getEmail() + "\t" + reader.getPhoneNumber());
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void listLibrarians() {
        System.out.println("Do you want them sorted alphabetically default yes, your answer [Y/n]:");
        String answer = scanner.nextLine();

        boolean isAlphabetSorted = !answer.toLowerCase().startsWith("n");
        Sorting sorting = new Sorting(null, isAlphabetSorted);
        AppResult<List<Librarian>> result = librarianService.getLibrariesList(sorting);
        if (result.hasDataOnly()) {
            System.out.println("All Librarians:");
            for (Librarian librarian : result.getData()) {
                System.out.println(librarian.getId() + "\t" + librarian.getName() + "\t" + librarian.getEmail() + "\t" + librarian.getPhoneNumber() + "\t" + librarian.getLibraryId());
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void findBookByTitle() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        AppResult<List<Book>> result = bookService.getBooksByTitle(title);
        if (result.hasDataOnly()) {
            if (result.getData().isEmpty()) {
                System.out.println("No books found with title \"" + title + "\"");
            } else {
                System.out.println("Books with title \"" + title + "\":");
                for (Book book : result.getData()) {
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getPublicationYear());
                }
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void findBooksByAuthor() {
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        AppResult<List<Book>> result = bookService.getBooksByAuthor(authorName);
        if (result.hasDataOnly()) {
            if (result.getData().isEmpty()) {
                System.out.println("No books found by author \"" + authorName + "\"");
            } else {
                System.out.println("Books by author \"" + authorName + "\":");
                for (Book book : result.getData()) {
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getPublicationYear());
                }
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void getAllLibraries() {
        System.out.println("Do you want them sorted alphabetically default yes, your answer [Y/n]:");
        String answer = scanner.nextLine();

        boolean isAlphabetSorted = !answer.toLowerCase().startsWith("n");
        Sorting sorting = new Sorting(null, isAlphabetSorted);
        AppResult<List<Library>> result = libraryService.getLibrariesList(sorting);
        if (result.hasDataOnly()) {
            System.out.println("All Libraries:");
            for (Library library : result.getData()) {
                System.out.println(library.getId() + "\t" + library.getName() + "\t" + library.getAddress() + "\t" + library.getPhoneNumber());
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }

    private void getAllSectionsInLibrary() {
        System.out.print("Enter library ID: ");
        int libraryId = scanner.nextInt();
        AppResult<List<Section>> result = sectionService.findSectionInLibrary(libraryId);
        if (result.hasDataOnly()) {
            if (result.getData().isEmpty()) {
                System.out.println("No sections found for library with ID " + libraryId);
            } else {
                System.out.println("All sections in library with ID " + libraryId + ":");
                for (Section section : result.getData()) {
                    System.out.println(section.getId() + "\t" + section.getName());
                }
            }
        } else {
            System.out.println("Error happened. " + result.getError().getMessage());
        }
    }
}
