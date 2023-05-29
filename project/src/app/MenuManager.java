package app;

import core.AppResult;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MenuManager {

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
                case 1 -> libraryOptions();
                case 2 -> sectionOptions();
                case 3 -> authorOptions();
                case 4 -> bookOptions();
                case 5 -> readerOptions();
                case 6 -> librarianOptions();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option. Please try again.");
            }
            menuUI.getUserInputAsNone("Press any key to clear...");
            MenuUI.clearConsole();
        } while (option != 0);
    }

    private void libraryOptions() {
        int option;
        menuUI.printLibraryMenu();
        option = menuUI.getUserInputAsInt();
        switch (option) {
            case 1 -> addLibrary();
            case 2 -> getAllLibraries();
            case 3 -> getLibraryById();
            case 4 -> updateLibrary();
            case 5 -> deleteLibrary();
            case 0 -> System.out.println("Return...");
            default -> System.out.println("Invalid option. Please try again.");

        }

    }

    private void addLibrary() {
        Library library = menuUI.readLibrary();
        var result = libraryService.createLibrary(library);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new library");
        }
    }

    private void getAllLibraries() {

        Sorting sorting = menuUI.getSortAlphabetically();
        AppResult<List<Library>> result = libraryService.getLibrariesList(sorting);
        menuUI.printLibraryResults(result);

        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all libraries");
        }
    }

    private void getLibraryById() {
        int id = menuUI.getUserInputAsInt("Enter library ID: ");
        var result = libraryService.getLibrary(id);
        menuUI.printLibraryResult(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Gets a library");
        }
    }

    private void updateLibrary() {
        int id = menuUI.getUserInputAsInt("Enter library ID: ");
        Library library = menuUI.readLibrary();
        library.setId(id);
        var result = libraryService.updateLibrary(library);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Update a library");
        } else menuUI.printFailure();

    }

    private void deleteLibrary() {
        int id = menuUI.getUserInputAsInt("Enter library ID: ");
        var result = libraryService.deleteLibrary(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Deletes a library");
        } else menuUI.printFailure();

    }

    private void sectionOptions() {
        int option;
        menuUI.printSectionMenu();
        option = menuUI.getUserInputAsInt();
        switch (option) {
            case 1 -> addSection();
            case 2 -> getAllSections();
            case 3 -> getSectionById();
            case 4 -> updateSection();
            case 5 -> deleteSection();
            case 6 -> getAllSectionsInLibrary();
            case 0 -> System.out.println("Return...");
            default -> System.out.println("Invalid option. Please try again.");

        }

    }


    private void addSection() {
        Section section = menuUI.readSection();
        var result = sectionService.createSection(section);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new section");
        }
    }

    private void getAllSections() {

        Sorting sorting = menuUI.getSortAlphabetically();
        var result = sectionService.getSectionsList(sorting);
        menuUI.printSectionResults(result);

        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all sections");
        }
    }

    private void getSectionById() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = sectionService.getSection(id);
        menuUI.printSectionResult(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Gets a section");
        }
    }

    private void updateSection() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var item = menuUI.readSection();
        item.setId(id);
        var result = sectionService.updateSection(item);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Update a section");
        } else menuUI.printFailure();

    }

    private void deleteSection() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = sectionService.deleteSection(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Deletes a section");
        } else menuUI.printFailure();
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

    //    private void addAuthor() {
//        int id = menuUI.getUserInputAsInt("Enter author ID: ");
//        String name = menuUI.getUserInputAsString("Enter author name: ");
//        String birthDate = menuUI.getUserInputAsString("Enter author birth date (yyyy-MM-dd): ");
//        Author author = new Author(id, name, birthDate);
//
//        var result = authorService.createAuthor(author);
//        if (result.hasDataOnly()) {
//            AuditService.writeToAuditLog("Add a new author");
//        }
//    }
    private void authorOptions() {
        int option;
        menuUI.printAuthorMenu();
        option = menuUI.getUserInputAsInt();
        switch (option) {
            case 1 -> addAuthor();
            case 2 -> getAllAuthors();
            case 3 -> getAuthorById();
            case 4 -> updateAuthor();
            case 5 -> deleteAuthor();
            case 0 -> System.out.println("Return...");
            default -> System.out.println("Invalid option. Please try again.");

        }

    }


    private void addAuthor() {
        Author author = menuUI.readAuthor();
        var result = authorService.createAuthor(author);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new author");
        }
    }

    private void getAllAuthors() {

        Sorting sorting = menuUI.getSortAlphabetically();
        var result = authorService.getAuthorsList(sorting);
        menuUI.printAuthorResults(result);

        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all authors");
        }
    }

    private void getAuthorById() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = authorService.getAuthor(id);
        menuUI.printAuthorResult(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Gets a author");
        }
    }

    private void updateAuthor() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var item = menuUI.readAuthor();
        item.setId(id);
        var result = authorService.updateAuthor(item);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Update a author");
        } else menuUI.printFailure();

    }

    private void deleteAuthor() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = authorService.deleteAuthor(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Deletes a author");
        } else menuUI.printFailure();
    }

    private void readerOptions() {
        int option;
        menuUI.printReaderMenu();
        option = menuUI.getUserInputAsInt();
        switch (option) {
            case 1 -> addReader();
            case 2 -> getAllReaders();
            case 3 -> getReaderById();
            case 4 -> updateReader();
            case 5 -> deleteReader();
            case 0 -> System.out.println("Return...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void addReader() {
        Reader reader = menuUI.readReader();
        var result = readerService.createReader(reader);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new reader");
        }
    }

    private void getAllReaders() {
        Sorting sorting = menuUI.getSortAlphabetically();
        var result = readerService.getReadersList(sorting);
        menuUI.printReaderResults(result);

        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all readers");
        }
    }

    private void getReaderById() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = readerService.getReader(id);
        menuUI.printReaderResult(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Gets a reader");
        }
    }

    private void updateReader() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var item = menuUI.readReader();
        item.setId(id);
        var result = readerService.updateReader(item);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Update a reader");
        } else menuUI.printFailure();
    }

    private void deleteReader() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = readerService.deleteReader(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Deletes a reader");
        } else menuUI.printFailure();
    }

    private void librarianOptions() {
        int option;
        menuUI.printLibrarianMenu();
        option = menuUI.getUserInputAsInt();
        switch (option) {
            case 1 -> addLibrarian();
            case 2 -> getAllLibrarians();
            case 3 -> getLibrarianById();
            case 4 -> updateLibrarian();
            case 5 -> deleteLibrarian();
            case 0 -> System.out.println("Return...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void addLibrarian() {
        Librarian librarian = menuUI.readLibrarian();
        var result = librarianService.createLibrarian(librarian);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new librarian");
        }
    }

    private void getAllLibrarians() {
        Sorting sorting = menuUI.getSortAlphabetically();
        var result = librarianService.getLibrariesList(sorting);
        menuUI.printLibrarianResults(result);

        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all librarians");
        }
    }

    private void getLibrarianById() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = librarianService.getLibrarian(id);
        menuUI.printLibrarianResult(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Gets a librarian");
        }
    }

    private void updateLibrarian() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var item = menuUI.readLibrarian();
        item.setId(id);
        var result = librarianService.updateLibrarian(item);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Update a librarian");
        } else menuUI.printFailure();
    }

    private void deleteLibrarian() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = librarianService.deleteLibrarian(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Deletes a librarian");
        } else menuUI.printFailure();
    }

    private void bookOptions() {
        int option;
        menuUI.printBookMenu();
        option = menuUI.getUserInputAsInt();
        switch (option) {
            case 1 -> addBook();
            case 2 -> getAllBooks();
            case 3 -> getBookById();
            case 4 -> updateBook();
            case 5 -> deleteBook();
            case 6 -> findBooksByAuthor();
            case 7 -> findBookByTitle();
            case 8 -> borrowBook();
            case 9 -> returnBook();
            case 0 -> System.out.println("Return...");
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void addBook() {
        Book book = menuUI.readBook();
        var result = bookService.createBook(book);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Add a new book");
        }
    }

    private void getAllBooks() {
        Sorting sorting = menuUI.getAlphabeticallyAndSortOnDate();
        var result = bookService.getBooksList(sorting);
        menuUI.printBookResults(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Get all books");
        }
    }

    private void getBookById() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = bookService.getBook(id);
        menuUI.printBookResult(result);
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Gets a book");
        }
    }

    private void updateBook() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var item = menuUI.readBook();
        item.setId(id);
        var result = bookService.updateBook(item);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Update a book");
        } else {
            menuUI.printFailure();
        }
    }

    private void deleteBook() {
        int id = menuUI.getUserInputAsInt("Enter id: ");
        var result = bookService.deleteBook(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Deletes a book");
        } else {
            menuUI.printFailure();
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
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getPublicationDate());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Find books by author");
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
                    System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthorId() + "\t" + book.getSectionId() + "\t" + book.getPublicationDate());
                }
            }
        } else {
            System.out.println("Error occurred: " + result.getError().getMessage());
        }
        if (result.hasDataOnly()) {
            AuditService.writeToAuditLog("Find a book by title");
        }
    }


    private void borrowBook() {
        int bookId = menuUI.getUserInputAsInt("Enter book ID: ");
        int readerId = menuUI.getUserInputAsInt("Enter reader ID: ");

        LocalDate borrowingDate = null;
        LocalDate dueDate = null;
        boolean isInputDateCorrect;
        do {
            try {
                borrowingDate = LocalDate.parse(menuUI.getUserInputAsString("Enter borrowing date (yyyy-MM-dd): "), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                dueDate = LocalDate.parse(menuUI.getUserInputAsString("Enter due date (yyyy-MM-dd): "), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (!dueDate.isAfter(borrowingDate)) {
                    System.out.println("Due date must be after borrowing date. Please try again.");
                    isInputDateCorrect = false;
                } else
                    isInputDateCorrect = true;


            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
                isInputDateCorrect = false;

            }
        } while (!isInputDateCorrect);


        Borrowing borrowing = new Borrowing(bookId, readerId, borrowingDate.toString(), dueDate.toString());
        var result = bookService.borrowBook(borrowing);

        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Borrowing a new book");
        } else menuUI.printFailure(result.getError());

    }

    private void returnBook() {
        int id = menuUI.getUserInputAsInt("Enter borrowing ID: ");
        var result = bookService.returnBorrowing(id);
        if (result.hasDataOnly()) {
            menuUI.printSuccess();
            AuditService.writeToAuditLog("Returning a book");
        } else menuUI.printFailure();
    }
}
