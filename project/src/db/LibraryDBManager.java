package db;

import entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryDBManager {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String JDBC_USERNAME = "project-pao";
    private static final String JDBC_PASSWORD = "12345678";

    public Connection connectToDB() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    public void addLibrary(int id, String name, String address, String phoneNumber) {

    }

    public void addSection(int id, String name, int libraryId) {

    }

    public void addAuthor(int id, String name, String birthDate) {

    }

    public void addBook(int id, String title, int authorId, int sectionId, int publicationYear) {

    }

    public void addReader(int id, String name, String email, String phoneNumber) {

    }

    public void addLibrarian(int id, String name, String email, String phoneNumber, int libraryId) {

    }

    public void borrowBook(int id, int readerId, int bookId, String borrowingDate, String dueDate) {

    }

    public void returnBook(int id) {

    }

    public List<Section> getAllSections() {
        return null;
    }

    public List<Author> getAllAuthors() {
        return null;
    }

    public List<Book> getAllBooks() {
        return null;
    }

    public List<Reader> getAllReaders() {
        return null;
    }

    public List<Librarian> getAllLibrarians() {
        return null;
    }

    public List<Book> getBooksByTitle(String title) {
        return null;
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return null;
    }

    public List<Library> getAllLibraries() {
        return null;
    }

    public List<Section> getAllSectionsInLibrary(int libraryId) {
        return null;
    }

}
