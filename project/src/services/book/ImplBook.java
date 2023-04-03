package services.book;

import core.AppResult;
import entities.*;
import repository.author.AuthorRepository;
import services.author.IAuthor;

import java.util.List;


import core.AppResult;
import entities.EmptyEntity;
import entities.Sorting;
import repository.book.BookRepository;

import java.util.List;

public class ImplBook implements IBook {
    private static volatile ImplBook instance;
    private final BookRepository repository;

    public static ImplBook getInstance() {
        if (instance == null) {
            synchronized (ImplBook.class) {
                if (instance == null) {
                    instance = new ImplBook(BookRepository.getInstance());
                }
            }
        }
        return instance;
    }

    private ImplBook(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppResult<Book> createBook(Book book) {
        return repository.save(book);
    }

    @Override
    public AppResult<List<Book>> getBooksList(Sorting sorting) {
        return repository.findAll(sorting);
    }

    @Override
    public AppResult<Book> getBook(int id) {
        return repository.findById(id);
    }

    @Override
    public AppResult<EmptyEntity> updateBook(Book entity) {
        return repository.update(entity);
    }

    @Override
    public AppResult<EmptyEntity> deleteBook(int id) {
        return repository.delete(id);
    }

    public AppResult<List<Book>> getBooksByTitle(String title) {
        return repository.getBooksByTitle(title);

    }

    public AppResult<List<Book>> getBooksByAuthor(String authorName) {
        return repository.getBooksByAuthor(authorName);

    }

    public AppResult<EmptyEntity> borrowBook(Borrowing borrowing) {
        return repository.borrowBook(borrowing);
    }

    public AppResult<EmptyEntity> returnBorrowing(int id) {
        return repository.returnBorrowing(id);
    }
}
