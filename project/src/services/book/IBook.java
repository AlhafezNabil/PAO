package services.book;

import core.AppResult;
import entities.*;

import java.util.List;
public interface IBook {
    public AppResult<Book> createBook(Book book);

    public AppResult<List<Book>> getBooksList(Sorting sorting);

    public AppResult<Book> getBook(int id);

    public AppResult<EmptyEntity> updateBook(Book entity);

    public AppResult<EmptyEntity> deleteBook(int id);


    public AppResult<List<Book>> getBooksByTitle(String title) ;

    public AppResult<List<Book>> getBooksByAuthor(String authorName) ;

    public AppResult<EmptyEntity> borrowBook(Borrowing borrowing) ;

    public AppResult<EmptyEntity> returnBorrowing(int id) ;
}
