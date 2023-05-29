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
import services.logs.AuditService;
import services.reader.IReader;
import services.reader.ImplReader;
import services.sections.ISection;
import services.sections.ImplSection;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuManager {

    //    private final DBManager dbManager;

    private final IAuthor authorService = ImplAuthor.getInstance();
    private final IBook bookService = ImplBook.getInstance();
    private final ILibrarian librarianService = ImplLibrarian.getInstance();
    private final ILibrary libraryService = ImplLibrary.getInstance();
    private final IReader readerService = ImplReader.getInstance();

    private final ISection sectionService = ImplSection.getInstance();

    private final MenuUI menuUI;

    public MenuManager(MenuUI menuUI) {
        this.menuUI = menuUI;
    }

    public void run() {
        int option;
        do {
            menuUI.printMenu();
            option = menuUI.getUserInputAsInt();
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

    private void addLibrary() {
        int id = menuUI.getUserInputAsInt("Enter library ID: ");
        String name = menuUI.getUserInputAsString("Enter library name: ");
        String address = menuUI.getUserInputAsString("Enter library address: ");
        String phoneNumber = menuUI.getUserInputAsString("Enter library phone number: ");
        Library library = new Library(id, name, address, phoneNumber);
        var result = libraryService.createLibrary(library);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new library");
        }
    }

    private void addSection() {
        int id = menuUI.getUserInputAsInt("Enter section ID: ");
        String name = menuUI.getUserInputAsString("Enter section name: ");
        int libraryId = menuUI.getUserInputAsInt("Enter library ID for the section: ");
        Section section = new Section(id, name, libraryId);
        var result = sectionService.createSection(section);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new section");
        }
    }

    private void addAuthor() {
        int id = menuUI.getUserInputAsInt("Enter author ID: ");
        String name = menuUI.getUserInputAsString("Enter author name: ");
        String birthDate = menuUI.getUserInputAsString("Enter author birth date (yyyy-MM-dd): ");
        Author author = new Author(id, name, birthDate);

        var result = authorService.createAuthor(author);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new author");
        }
    }

    private void addBook() {
        int id = menuUI.getUserInputAsInt("Enter book ID: ");
        String title = menuUI.getUserInputAsString("Enter book title: ");
        int authorId = menuUI.getUserInputAsInt("Enter book author ID: ");
        int sectionId = menuUI.getUserInputAsInt("Enter book section ID: ");
        String publicationDate = menuUI.getUserInputAsString("Enter publication date: ");
        int totalCopies = menuUI.getUserInputAsInt("Enter total copies: ");
        int availableCopies = menuUI.getUserInputAsInt("Enter available copies: ");

        Book book = new Book(id, title, authorId, sectionId, publicationDate, totalCopies, availableCopies);

        var result = bookService.createBook(book);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new book");
        }
    }

    private void addReader() {
        int id = menuUI.getUserInputAsInt("Enter reader ID: ");
        String name = menuUI.getUserInputAsString("Enter reader name: ");
        String email = menuUI.getUserInputAsString("Enter reader email: ");
        String phoneNumber = menuUI.getUserInputAsString("Enter reader phone number: ");
        String location = menuUI.getUserInputAsString("Enter reader location: ");
        String createdDate = menuUI.getUserInputAsString("Enter reader created date: ");
        Reader reader = new Reader(id, name, email, phoneNumber, location, createdDate);

        var result = readerService.createReader(reader);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new reader");
        }
    }

    private void addLibrarian() {
        int id = menuUI.getUserInputAsInt("Enter librarian ID: ");
        String name = menuUI.getUserInputAsString("Enter librarian name: ");
        String email = menuUI.getUserInputAsString("Enter librarian email: ");
        String phoneNumber = menuUI.getUserInputAsString("Enter librarian phone number: ");
        int libraryId = menuUI.getUserInputAsInt("Enter library ID for the librarian: ");
        Librarian librarian = new Librarian(id, name, email, phoneNumber, libraryId);
        var result = librarianService.createLibrarian(librarian);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new librarian");
        }
    }

    private void borrowBook() {
        int id = menuUI.getUserInputAsInt("Enter borrowing ID: ");
        int bookId = menuUI.getUserInputAsInt("Enter book ID: ");
        int readerId = menuUI.getUserInputAsInt("Enter reader ID: ");
        String borrowingDate = menuUI.getUserInputAsString("Enter borrowing date (yyyy-MM-dd): ");
        String dueDate = menuUI.getUserInputAsString("Enter due date (yyyy-MM-dd): ");
        Borrowing borrowing = new Borrowing(id, bookId, readerId, borrowingDate, dueDate);
        var result = bookService.borrowBook(borrowing);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Borrowing a new book");
        }
    }

    private void returnBook() {
        int id = menuUI.getUserInputAsInt("Enter borrowing ID: ");
        var result = bookService.returnBorrowing(id);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Returning a book");
        }
    }

    private void listSections() {
        Sorting sorting = new Sorting(null, true);
        AppResult<List<Section>> result = sectionService.getSectionsList(sorting);
        if (result.hasDataOnly()) {
            List<Section> sections = result.getData();
            if (sections.isEmpty()) {
                System.out.println("No sections found.");
            } else {
                System.out.println("All Sections:");
                for (Section section : sections) {
                    System.out.println(section.getId() + "\t" + section.getName() + "\t" + section.getLibraryId());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all sections");
        }
    }

    private void listAuthors() {
        Sorting sorting = new Sorting(true, false);
        AppResult<List<Author>> result = authorService.getAuthorsList(sorting);
        if (result.hasDataOnly()) {
            List<Author> authors = result.getData();
            if (authors.isEmpty()) {
                System.out.println("No authors found.");
            } else {
                System.out.println("All Authors:");
                for (Author author : authors) {
                    System.out.println(author.getId() + "\t" + author.getName() + "\t" + author.getBirthDate());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all authors");
        }
    }

    private void listBooks() {
        Sorting sorting = new Sorting(true, false);
        AppResult<List<Book>> result = bookService.getBooksList(sorting);
        if (result.hasDataOnly()) {
            List<Book> books = result.getData();
            if (books.isEmpty()) {
                System.out.println("No books found.");
            } else {
                System.out.println("All Books:");
                for (Book book : books) {
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getAvailableCopies());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all books");
        }
    }

    private void listReaders() {
        AppResult<List<Reader>> result = readerService.getReadersList(new Sorting(null, null));
        if (result.hasDataOnly()) {
            List<Reader> readers = result.getData();
            if (readers.isEmpty()) {
                System.out.println("No readers found.");
            } else {
                System.out.println("All Readers:");
                for (Reader reader : readers) {
                    System.out.println(reader.getId() + "\t" + reader.getName() + "\t" + reader.getEmail() + "\t" + reader.getPhoneNumber());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all readers");
        }
    }

    private void listLibrarians() {
        Sorting sorting = new Sorting(true, false);
        AppResult<List<Librarian>> result = librarianService.getLibrariesList(sorting);
        if (result.hasDataOnly()) {
            List<Librarian> librarians = result.getData();
            if (librarians.isEmpty()) {
                System.out.println("No librarians found.");
            } else {
                System.out.println("All Librarians:");
                for (Librarian librarian : librarians) {
                    System.out.println(librarian.getId() + "\t" + librarian.getName() + "\t" + librarian.getEmail() + "\t" + librarian.getPhoneNumber() + "\t" + librarian.getLibraryId());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all librarians");
        }
    }

    private void findBookByTitle() {
        String title = menuUI.getUserInputAsString("Enter book title: ");
        AppResult<List<Book>> result = bookService.getBooksByTitle(title);
        if (result.hasDataOnly()) {
            List<Book> books = result.getData();
            if (books.isEmpty()) {
                System.out.println("No books found with title \"" + title + "\"");
            } else {
                System.out.println("Books with title \"" + title + "\":");
                for (Book book : books) {
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getpublicationDate());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Find a book by title");
        }
    }

    private void findBooksByAuthor() {
        String authorName = menuUI.getUserInputAsString("Enter author name: ");
        AppResult<List<Book>> result = bookService.getBooksByAuthor(authorName);
        if (result.hasDataOnly()) {
            List<Book> books = result.getData();
            if (books.isEmpty()) {
                System.out.println("No books found by author \"" + authorName + "\"");
            } else {
                System.out.println("Books by author \"" + authorName + "\":");
                for (Book book : books) {
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getpublicationDate());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Find books by author");
        }
    }

    private void getAllLibraries() {
        Sorting sorting = new Sorting(true, false);
        AppResult<List<Library>> result = libraryService.getLibrariesList(sorting);
        if (result.hasDataOnly()) {
            List<Library> libraries = result.getData();
            if (libraries.isEmpty()) {
                System.out.println("No libraries found.");
            } else {
                System.out.println("All Libraries:");
                for (Library library : libraries) {
                    System.out.println(library.getId() + "\t" + library.getName() + "\t" + library.getAddress() + "\t" + library.getPhoneNumber());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all libraries");
        }
    }

    private void getAllSectionsInLibrary() {
        int libraryId = menuUI.getUserInputAsInt("Enter library ID: ");
        AppResult<List<Section>> result = sectionService.findSectionInLibrary(libraryId);
        if (result.hasDataOnly()) {
            List<Section> sections = result.getData();
            if (sections.isEmpty()) {
                System.out.println("No sections found for library with ID " + libraryId);
            } else {
                System.out.println("All sections in library with ID " + libraryId + ":");
                for (Section section : sections) {
                    System.out.println(section.getId() + "\t" + section.getName());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all sections in a library");
        }
    }
}
