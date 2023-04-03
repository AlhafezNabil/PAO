package services.author;

import core.AppResult;
import entities.Author;
import entities.EmptyEntity;
import entities.Sorting;

import java.util.List;

public interface IAuthor {
    public AppResult<Author> createAuthor(Author author);

    public AppResult<List<Author>> getAuthorsList(Sorting sorting);

    public AppResult<Author> getAuthor(int id);

    public AppResult<EmptyEntity> updateAuthor(Author entity);

    public AppResult<EmptyEntity> deleteAuthor(int id);
}
