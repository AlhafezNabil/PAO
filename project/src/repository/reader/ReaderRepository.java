package repository.reader;


import core.AppResult;
import db.DBManager;
import entities.EmptyEntity;
import entities.Reader;
import entities.Sorting;
import repository.GenericRepository;

import java.util.List;


public class ReaderRepository implements GenericRepository<Reader> {

    private final DBManager db;

    private static volatile ReaderRepository instance;

    public static ReaderRepository getInstance() {
        if (instance == null) {
            synchronized (ReaderRepository.class) {
                if (instance == null) {
                    instance = new ReaderRepository();
                }
            }
        }
        return instance;
    }

    private ReaderRepository() {
        this.db = DBManager.getInstance();
    }


    @Override
    public AppResult<Reader> save(Reader entity) {
        return db.addReader(entity);

    }

    @Override
    public AppResult<List<Reader>> findAll(Sorting options) {
        return db.getAllReaders(options);
    }

    @Override
    public AppResult<Reader> findById(int id) {
        return db.getReader(id);
    }

    @Override
    public AppResult<EmptyEntity> update(Reader entity) {
        return db.updateReader(entity);
    }

    @Override
    public AppResult<EmptyEntity> delete(int id) {
        return db.deleteReader(id);
    }

}
