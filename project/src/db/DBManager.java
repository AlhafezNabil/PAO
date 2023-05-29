package db;

import core.AppResult;
import core.errors.UnExpectedError;
import core.errors.CustomError;
import entities.*;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
    String sqlLibrary = "INSERT INTO libraries (id, name, address, phone_number) VALUES (?, ?, ?, ?)";
    String sqlLibrarian = "INSERT INTO librarians (id, name, email, phone_number, library_id) VALUES (?, ?, ?, ?, ?)";
    String sqlSection = "INSERT INTO sections (id, name, library_id) VALUES (?, ?, ?)";
    String sqlAuthor = "INSERT INTO authors (id, name, birth_date) VALUES (?, ?, ?)";
    String sqlReader = "INSERT INTO readers (id, name, email, phone_number) VALUES (?, ?, ?, ?)";
    String sqlBook = "INSERT INTO books (id, title, author_id, section_id, publication_date, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String sqlBorrowings = "INSERT INTO borrowings (id, reader_id, book_id, borrowing_date, due_date, returned_rate) VALUES (?, ?, ?, ?, ?, ?)";

    private static volatile DBManager instance;

    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/pao_library_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Admin@123456";


    private Connection connection;

    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    private DBManager() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Exception driver not available: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("MySQL Exception Could not connect to database: " + e.getMessage());
        }
    }


    public static Connection getDatabaseConnection() {
        return getInstance().connection;
    }

    public static void printError(Exception exception) {
        System.out.println("===================================== Error Starts =====================================");
        System.out.print("Error message: ");
        System.out.println(exception.getMessage());
        System.out.print("Error stack trace: ");
        exception.printStackTrace();
        System.out.println("===================================== Error Ends =====================================");

    }

    public static <T> AppResult<T> mapErrorToBaseError(Exception exception) {
        try {
            printError(exception);
            if (exception instanceof SQLException) return new AppResult<T>(new CustomError(exception.getMessage()));

            return new AppResult<T>(new UnExpectedError("UnKnown error happened"));


        } catch (Exception e) {
            return new AppResult<T>(new UnExpectedError("UnKnown error happened"));

        }
    }

    //    ======================================== Library ===========================================================
    public AppResult<Library> addLibrary(Library library) {
        try (PreparedStatement statement = connection.prepareStatement(sqlLibrary)) {
            statement.setInt(1, library.getId());
            statement.setString(2, library.getName());
            statement.setString(3, library.getAddress());
            statement.setString(4, library.getPhoneNumber());
            statement.execute();
            return new AppResult<>(library);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<List<Library>> getAllLibraries(Sorting options) {
        String sql = "SELECT * FROM libraries";

        if (options.getAlphabeticSorted()) {
            sql += " ORDER BY name";
        }

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            List<Library> libraries = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                Library library = new Library(id, name, address, phoneNumber);
                libraries.add(library);
            }

            return new AppResult<>(libraries);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<Library> getLibrary(int libraryId) {
        String sql = "SELECT * FROM libraries WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libraryId);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");

                Library library = new Library(id, name, address, phoneNumber);
                return new AppResult<>(library);
            } else {

                return new AppResult<>(new CustomError("No library found with id: " + libraryId));
            }
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<EmptyEntity> updateLibrary(Library library) {
        String sqlLibrary = "UPDATE libraries SET name = ?, address = ?, phone_number = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlLibrary)) {
            statement.setString(1, library.getName());
            statement.setString(2, library.getAddress());
            statement.setString(3, library.getPhoneNumber());
            statement.setInt(4, library.getId());

            statement.executeUpdate();

            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);

        }
    }

    public AppResult<EmptyEntity> deleteLibrary(int libraryId) {
        String sql = "DELETE FROM libraries WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libraryId);
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }


    //    ======================================== Section ===========================================================
    public AppResult<Section> addSection(Section section) {
        try (PreparedStatement statement = connection.prepareStatement(sqlSection)) {
            statement.setInt(1, section.getId());
            statement.setString(2, section.getName());
            statement.setInt(3, section.getLibraryId());
            statement.executeUpdate();
            return new AppResult(section);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }

    }

    public AppResult<Section> getSection(int sectionId) {
        String sql = "SELECT * FROM sections WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sectionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int libraryId = resultSet.getInt("library_id");

                Section section = new Section(id, name, libraryId);
                return new AppResult<>(section);
            } else {

                return new AppResult<>(new CustomError("No section found with id: " + sectionId));
            }


        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<List<Section>> getAllSectionsInLibrary(Sorting options) {
        String sql = "SELECT * FROM sections";
        if (options.getAlphabeticSorted()) {
            sql += " ORDER BY name";
        }

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {


            List<Section> sections = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int libraryId = resultSet.getInt("library_id");

                Section section = new Section(id, name, libraryId);
                sections.add(section);
            }
            return new AppResult<>(sections);
        } catch (Exception sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }

    public AppResult<List<Section>> getAllSectionsInLibrary(int libId) {
        String sql = "SELECT * FROM sections WHERE library_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libId);
            ResultSet resultSet = statement.executeQuery();
            List<Section> sections = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int libraryId = resultSet.getInt("library_id");

                Section section = new Section(id, name, libraryId);
                sections.add(section);
            }
            return new AppResult<>(sections);
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }

    public AppResult<EmptyEntity> updateSection(Section section) {
        String sqlSection = "UPDATE sections SET name = ?, library_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlSection)) {
            statement.setString(1, section.getName());
            statement.setInt(2, section.getLibraryId());
            statement.setInt(3, section.getId());
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);

        }
    }

    public AppResult<EmptyEntity> deleteSection(int sectionId) {
        String sql = "DELETE FROM sections WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sectionId);
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }

    //    ======================================== Author ===========================================================
    public AppResult<Author> addAuthor(Author author) {
        try (PreparedStatement statement = connection.prepareStatement(sqlAuthor)) {
            statement.setInt(1, author.getId());
            statement.setString(2, author.getName());
            statement.setDate(3, Date.valueOf(author.getBirthDate()));
            statement.execute();
            return new AppResult<>(author);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<Author> getAuthor(int authorId) {
        String sql = "SELECT * FROM authors WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, authorId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                Author author = new Author(id, name, birthDate.toString());
                return new AppResult<>(author);
            } else {
                return new AppResult<>(new CustomError("No author found with id: " + authorId));
            }

        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<List<Author>> getAllAuthors(Sorting options) {
        String sql = "SELECT * FROM authors";
        if (options.getAlphabeticSorted()) {
            sql += " ORDER BY name";
        }

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                Author author = new Author(id, name, birthDate.toString());
                authors.add(author);
            }
            return new AppResult<>(authors);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<EmptyEntity> updateAuthor(Author author) {
        String sqlAuthor = "UPDATE authors SET name = ?, birth_date = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlAuthor)) {
            statement.setString(1, author.getName());
            statement.setDate(2, Date.valueOf(author.getBirthDate()));
            statement.setInt(3, author.getId());

            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);

        }
    }

    public AppResult<EmptyEntity> deleteAuthor(int authorId) {
        String sql = "DELETE FROM authors WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, authorId);
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }


    //    ======================================== Librarian ===========================================================
    public AppResult<Librarian> addLibrarian(Librarian librarian) {
        try (PreparedStatement statement = connection.prepareStatement(sqlLibrarian)) {
            statement.setInt(1, librarian.getId());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getEmail());
            statement.setString(4, librarian.getPhoneNumber());
            statement.setInt(5, librarian.getLibraryId());
            statement.execute();
            return new AppResult<>(librarian);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<Librarian> getLibrarian(int librarianId) {
        String sql = "SELECT * FROM librarians WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, librarianId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                int libraryId = resultSet.getInt("library_id");
                Librarian librarian = new Librarian(id, name, email, phoneNumber, libraryId);
                return new AppResult<>(librarian);
            } else {
                return new AppResult<>(new CustomError("No librarian found with id: " + librarianId));
            }
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }


    public AppResult<List<Librarian>> getAllLibrarians(Sorting options) {
        String sql = "SELECT * FROM librarians";

        if (options.getAlphabeticSorted()) {
            sql += " ORDER BY name";
        }

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            List<Librarian> librarians = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                int libraryId = resultSet.getInt("library_id");
                Librarian librarian = new Librarian(id, name, email, phoneNumber, libraryId);
                librarians.add(librarian);
            }
            return new AppResult<>(librarians);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }

    }

    public AppResult<EmptyEntity> updateLibrarian(Librarian librarian) {
        String sqlLibrarian = "UPDATE librarians SET name = ?, email = ?, phone_number = ?, library_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlLibrarian)) {
            statement.setString(1, librarian.getName());
            statement.setString(2, librarian.getEmail());
            statement.setString(3, librarian.getPhoneNumber());
            statement.setInt(4, librarian.getLibraryId());
            statement.setInt(5, librarian.getId());
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }

    public AppResult<EmptyEntity> deleteLibrarian(int librarianId) {
        String sql = "DELETE FROM librarians WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, librarianId);
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }


    //    ======================================== Reader ===========================================================
    public AppResult<Reader> addReader(Reader reader) {
        String sqlReader = "INSERT INTO readers (id, name, email, phone_number, location, created_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlReader)) {
            statement.setInt(1, reader.getId());
            statement.setString(2, reader.getName());
            statement.setString(3, reader.getEmail());
            statement.setString(4, reader.getPhoneNumber());
            statement.setString(5, reader.getLocation());
            statement.setString(6, reader.getCreatedDate());
            statement.execute();
            return new AppResult<>(reader);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<Reader> getReader(int readerId) {
        String sql = "SELECT * FROM readers WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, readerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String location = resultSet.getString("location");
                String createdDate = resultSet.getString("created_date");

                Reader reader = new Reader(id, name, email, phoneNumber, location, createdDate);
                return new AppResult<>(reader);
            } else {
                return new AppResult<>(new CustomError("No reader found with id: " + readerId));
            }
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<List<Reader>> getAllReaders(Sorting options) {
        String sql = "SELECT * FROM readers";

        if (options.getAlphabeticSorted()) {
            sql += " ORDER BY name";
        }

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            List<Reader> readers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String location = resultSet.getString("location");
                String createdDate = resultSet.getString("created_date");

                Reader reader = new Reader(id, name, email, phoneNumber, location, createdDate);
                readers.add(reader);
            }
            return new AppResult<>(readers);
        } catch (Exception exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<EmptyEntity> updateReader(Reader reader) {
        String sqlReader = "UPDATE readers SET name = ?, email = ?, phone_number = ?, location = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlReader)) {
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getEmail());
            statement.setString(3, reader.getPhoneNumber());
            statement.setString(4, reader.getLocation());
            statement.setInt(5, reader.getId());
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }

    public AppResult<EmptyEntity> deleteReader(int readerId) {
        String sql = "DELETE FROM readers WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, readerId);
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            return mapErrorToBaseError(sqlException);
        }
    }

    //    ======================================== Book ===========================================================
    public AppResult<Book> addBook(Book book) {
        String sqlBook = "INSERT INTO books (id, title, author_id, section_id, publication_date, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlBook)) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getAuthorId());
            statement.setInt(4, book.getSectionId());
            statement.setString(5, book.getPublicationDate());
            statement.setInt(6, book.getTotalCopies());
            statement.setInt(7, book.getAvailableCopies());

            statement.execute();
            return new AppResult<>(book);
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<Book> getBook(int bookId) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                int sectionId = resultSet.getInt("section_id");
                String publicationDate = resultSet.getString("publication_date");
                int totalCopies = resultSet.getInt("total_copies");
                int availableCopies = resultSet.getInt("available_copies");

                Book book = new Book(id, title, authorId, sectionId, publicationDate, totalCopies, availableCopies);
                return new AppResult<>(book);
            } else {
                return new AppResult<>(new CustomError("Book not found"));
            }
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<List<Book>> getAllBooks(Sorting options) {
        String sql = "SELECT * FROM books";
        if (options.getAlphabeticSorted()) {
            sql += " ORDER BY title";
        }

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                int sectionId = resultSet.getInt("section_id");
                String publicationDate = resultSet.getString("publication_date");
                int totalCopies = resultSet.getInt("total_copies");
                int availableCopies = resultSet.getInt("available_copies");

                Book book = new Book(id, title, authorId, sectionId, publicationDate, totalCopies, availableCopies);
                books.add(book);
            }
            return new AppResult<>(books);
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<EmptyEntity> updateBook(Book book) {
        String sqlBook = "UPDATE books SET title = ?, author_id = ?, section_id = ?, publication_date = ?, total_copies = ?, available_copies = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sqlBook)) {
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthorId());
            statement.setInt(3, book.getSectionId());
            statement.setString(4, book.getPublicationDate());
            statement.setInt(5, book.getTotalCopies());
            statement.setInt(6, book.getAvailableCopies());
            statement.setInt(7, book.getId());

            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<EmptyEntity> deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }


    public AppResult<EmptyEntity> borrowBook(Borrowing borrowing) {
        String sqlGetBook = "SELECT available_copies FROM books WHERE id = ?";
        String sqlUpdateBook = "UPDATE books SET available_copies = ? WHERE id = ?";
        String sqlBorrowings = "INSERT INTO borrowings (id, reader_id, book_id, borrowing_date, due_date) VALUES (?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);  // Start a transaction *** and below i revers it i kicked my ass with it ^_*


            PreparedStatement getBookStmt = connection.prepareStatement(sqlGetBook);
            getBookStmt.setInt(1, borrowing.getBookId());
            ResultSet rs = getBookStmt.executeQuery();
            if (!rs.next()) {
                return new AppResult<>(new CustomError("Book not found."));
            }
            int availableCopies = rs.getInt("available_copies");
            if (availableCopies <= 0) {
                return new AppResult<>(new CustomError("No copies left to borrow."));
            }


            PreparedStatement updateBookStmt = connection.prepareStatement(sqlUpdateBook);
            updateBookStmt.setInt(1, availableCopies - 1);
            updateBookStmt.setInt(2, borrowing.getBookId());
            updateBookStmt.executeUpdate();


            PreparedStatement statement = connection.prepareStatement(sqlBorrowings);
            statement.setInt(1, borrowing.getId());
            statement.setInt(2, borrowing.getReaderId());
            statement.setInt(3, borrowing.getBookId());
            statement.setDate(4, Date.valueOf(borrowing.getBorrowedDate()));
            statement.setDate(5, Date.valueOf(borrowing.getDueDate()));
            statement.executeUpdate();

            connection.commit();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return mapErrorToBaseError(sqlException);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public AppResult<EmptyEntity> returnBorrowing(int id) throws SQLException {
        String sqlGetBookId = "SELECT book_id FROM borrowings WHERE id = ?";
        String sqlUpdateBook = "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?";
        String sqlBorrowings = "UPDATE borrowings SET returned_date = ? WHERE id = ?";
        String sqlSelectBorrowings = "SELECT * FROM borrowings WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlSelectBorrowings)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Date dueDate = rs.getDate("due_date");
                Date returnedDate = Date.valueOf(LocalDate.now());
                if (returnedDate.after(dueDate)) {
                    System.out.println("You have to pay a fine because the book was returned late.");
                } else {

                    System.out.println("It's good that you have returned the book back on time.");
                }
            } else {
                return new AppResult<>(new CustomError("Borrowing not found."));
            }
        }
        try {
            connection.setAutoCommit(false);

            PreparedStatement getBookIdStmt = connection.prepareStatement(sqlGetBookId);
            getBookIdStmt.setInt(1, id);
            ResultSet rs = getBookIdStmt.executeQuery();
            if (!rs.next()) {
                return new AppResult<>(new CustomError("Borrowing not found."));
            }
            int bookId = rs.getInt("book_id");

            PreparedStatement updateBookStmt = connection.prepareStatement(sqlUpdateBook);
            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(sqlBorrowings);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, id);
            statement.executeUpdate();

            connection.commit();
            return new AppResult<>(new EmptyEntity());
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return mapErrorToBaseError(sqlException);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public AppResult<List<Book>> getBooksByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                int sectionId = resultSet.getInt("section_id");
                String publicationDate = resultSet.getString("publication_date");
                int totalCopies = resultSet.getInt("total_copies");
                int availableCopies = resultSet.getInt("available_copies");

                Book book = new Book(id, bookTitle, authorId, sectionId, publicationDate, totalCopies, availableCopies);
                books.add(book);
            }
            return new AppResult<>(books);
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }

    public AppResult<List<Book>> getBooksByAuthor(String authorName) {
        String sql = "SELECT * FROM books b INNER JOIN authors a ON b.author_id = a.id WHERE a.name LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + authorName + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                int sectionId = resultSet.getInt("section_id");
                String publicationDate = resultSet.getString("publication_date");
                int totalCopies = resultSet.getInt("total_copies");
                int availableCopies = resultSet.getInt("available_copies");

                Book book = new Book(id, bookTitle, authorId, sectionId, publicationDate, totalCopies, availableCopies);
                books.add(book);
            }
            return new AppResult<>(books);
        } catch (SQLException exception) {
            return mapErrorToBaseError(exception);
        }
    }

}

