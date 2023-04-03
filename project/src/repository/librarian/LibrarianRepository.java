package repository.librarian;


import core.AppResult;
import db.DBManager;
import entities.EmptyEntity;
import entities.Librarian;
import entities.Sorting;
import repository.GenericRepository;

import java.util.List;


public class LibrarianRepository implements GenericRepository<Librarian> {

    private final DBManager db;

    private static volatile LibrarianRepository instance;

    public static LibrarianRepository getInstance() {
        if (instance == null) {
            synchronized (LibrarianRepository.class) {
                if (instance == null) {
                    instance = new LibrarianRepository();
                }
            }
        }
        return instance;
    }

    private LibrarianRepository() {
        this.db = DBManager.getInstance();
    }


    @Override
    public AppResult<Librarian> save(Librarian entity) {
        return db.addLibrarian(entity);

    }

    @Override
    public AppResult<List<Librarian>> findAll(Sorting options) {
        return db.getAllLibrarians(options);
    }

    @Override
    public AppResult<Librarian> findById(int id) {
        return db.getLibrarian(id);
    }

    @Override
    public AppResult<EmptyEntity> update(Librarian entity) {
        return db.updateLibrarian(entity);
    }

    @Override
    public AppResult<EmptyEntity> delete(int id) {
        return db.deleteLibrarian(id);
    }

}
