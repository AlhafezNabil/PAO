package repository.library;


import core.AppResult;
import db.DBManager;
import entities.EmptyEntity;
import entities.Library;
import entities.Sorting;
import repository.GenericRepository;

import java.util.*;


public class LibraryRepository implements GenericRepository<Library> {

    private final DBManager db;

    private static volatile LibraryRepository instance;

    public static LibraryRepository getInstance() {
        if (instance == null) {
            synchronized (LibraryRepository.class) {
                if (instance == null) {
                    instance = new LibraryRepository();
                }
            }
        }
        return instance;
    }

    private LibraryRepository() {
        this.db = DBManager.getInstance();
    }


    @Override
    public AppResult<Library> save(Library entity) {
        return db.addLibrary(entity);

    }

    @Override
    public AppResult<List<Library>> findAll(Sorting options) {
        return db.getAllLibraries(options);
    }

    @Override
    public AppResult<Library> findById(int id) {
        return db.getLibrary(id);
    }

    @Override
    public AppResult<EmptyEntity> update(Library entity) {
        return db.updateLibrary(entity);
    }

    @Override
    public AppResult<EmptyEntity> delete(int id) {
        return db.deleteLibrary(id);
    }

}
