package repository.author;


import core.AppResult;
import db.DBManager;
import entities.EmptyEntity;
import entities.Author;
import entities.Sorting;
import repository.GenericRepository;

import java.util.List;


public class AuthorRepository implements GenericRepository<Author> {

    private final DBManager db;

    private static volatile AuthorRepository instance;

    public static AuthorRepository getInstance() {
        if (instance == null) {
            synchronized (AuthorRepository.class) {
                if (instance == null) {
                    instance = new AuthorRepository();
                }
            }
        }
        return instance;
    }

    private AuthorRepository() {
        this.db = DBManager.getInstance();
    }


    @Override
    public AppResult<Author> save(Author entity) {
        return db.addAuthor(entity);

    }

    @Override
    public AppResult<List<Author>> findAll(Sorting options) {
        return db.getAllAuthors(options);
    }

    @Override
    public AppResult<Author> findById(int id) {
        return db.getAuthor(id);
    }

    @Override
    public AppResult<EmptyEntity> update(Author entity) {
        return db.updateAuthor(entity);
    }

    @Override
    public AppResult<EmptyEntity> delete(int id) {
        return db.deleteAuthor(id);
    }

}
