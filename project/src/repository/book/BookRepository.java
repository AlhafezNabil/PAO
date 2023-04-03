package repository.book;


import core.AppResult;
import db.DBManager;
import entities.Borrowing;
import entities.EmptyEntity;
import entities.Book;
import entities.Sorting;
import repository.GenericRepository;

import java.util.List;


public class BookRepository implements GenericRepository<Book> {

    private final DBManager db;

    private static volatile BookRepository instance;

    public static BookRepository getInstance() {
        if (instance == null) {
            synchronized (BookRepository.class) {
                if (instance == null) {
                    instance = new BookRepository();
                }
            }
        }
        return instance;
    }

    private BookRepository() {
        this.db = DBManager.getInstance();
    }


    @Override
    public AppResult<Book> save(Book entity) {
        return db.addBook(entity);

    }

    @Override
    public AppResult<List<Book>> findAll(Sorting options) {
        return db.getAllBooks(options);
    }

    @Override
    public AppResult<Book> findById(int id) {
        return db.getBook(id);
    }

    @Override
    public AppResult<EmptyEntity> update(Book entity) {
        return db.updateBook(entity);
    }

    @Override
    public AppResult<EmptyEntity> delete(int id) {
        return db.deleteBook(id);
    }


    public AppResult<List<Book>> getBooksByTitle(String title) {
        return db.getBooksByTitle(title);

    }

    public AppResult<List<Book>> getBooksByAuthor(String authorName) {
        return db.getBooksByAuthor(authorName);

    }

    public AppResult<EmptyEntity> borrowBook(Borrowing borrowing) {
        return db.borrowBook(borrowing);
    }

    public AppResult<EmptyEntity> returnBorrowing(int id) {
        return db.returnBorrowing(id);
    }

}
