package repository.setction;


import core.AppResult;
import db.DBManager;
import entities.*;
import repository.GenericRepository;

import java.util.List;


public class SectionRepository implements GenericRepository<Section> {

    private final DBManager db;

    private static volatile SectionRepository instance;

    public static SectionRepository getInstance() {
        if (instance == null) {
            synchronized (SectionRepository.class) {
                if (instance == null) {
                    instance = new SectionRepository();
                }
            }
        }
        return instance;
    }

    private SectionRepository() {
        this.db = DBManager.getInstance();
    }


    @Override
    public AppResult<Section> save(Section entity) {
        return db.addSection(entity);

    }

    @Override
    public AppResult<List<Section>> findAll(Sorting options) {
        return db.getAllSectionsInLibrary(options);
    }

    @Override
    public AppResult<Section> findById(int id) {
        return db.getSection(id);
    }

    @Override
    public AppResult<EmptyEntity> update(Section entity) {
        return db.updateSection(entity);
    }

    @Override
    public AppResult<EmptyEntity> delete(int id) {
        return db.deleteSection(id);
    }


    public AppResult<List<Section>> findSectionInLibrary(int libraryId) {
        return db.getAllSectionsInLibrary(libraryId);
    }

}
