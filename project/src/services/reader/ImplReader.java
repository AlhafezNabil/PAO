package services.reader;

import core.AppResult;
import entities.EmptyEntity;
import entities.Librarian;
import entities.Sorting;
import repository.librarian.LibrarianRepository;
import services.librarian.ILibrarian;

import java.util.List;


import core.AppResult;
import entities.EmptyEntity;
import entities.Reader;
import entities.Sorting;
import repository.reader.ReaderRepository;

import java.util.List;

public class ImplReader implements IReader {
    private static volatile ImplReader instance;
    private ReaderRepository repository;

    public static ImplReader getInstance() {
        if (instance == null) {
            synchronized (ImplReader.class) {
                if (instance == null) {
                    instance = new ImplReader(ReaderRepository.getInstance());
                }
            }
        }
        return instance;
    }

    private ImplReader(ReaderRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppResult<Reader> createReader(Reader reader) {
        return repository.save(reader);
    }

    @Override
    public AppResult<List<Reader>> getReadersList(Sorting sorting) {
        return repository.findAll(sorting);
    }

    @Override
    public AppResult<Reader> getReader(int id) {
        return repository.findById(id);
    }

    @Override
    public AppResult<EmptyEntity> updateReader(Reader entity) {
        return repository.update(entity);
    }

    @Override
    public AppResult<EmptyEntity> deleteReader(int id) {
        return repository.delete(id);
    }
}
