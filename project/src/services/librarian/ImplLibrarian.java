package services.librarian;

import core.AppResult;
import entities.EmptyEntity;
import entities.Librarian;
import entities.Sorting;
import repository.librarian.LibrarianRepository;


import java.util.List;

public class ImplLibrarian implements ILibrarian {
    private static volatile ImplLibrarian instance;
    private LibrarianRepository repository;

    public static ImplLibrarian getInstance() {
        if (instance == null) {
            synchronized (ImplLibrarian.class) {
                if (instance == null) {
                    instance = new ImplLibrarian(LibrarianRepository.getInstance());
                }
            }
        }
        return instance;
    }

    private ImplLibrarian(LibrarianRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppResult<Librarian> createLibrarian(Librarian librarian) {
        return repository.save(librarian);

    }

    @Override
    public AppResult<List<Librarian>> getLibrariesList(Sorting sorting) {
        return repository.findAll(sorting);
    }

    @Override
    public AppResult<Librarian> getLibrarian(int id) {
        return repository.findById(id);
    }

    @Override
    public AppResult<EmptyEntity> updateLibrarian(Librarian entity) {
        return repository.update(entity);
    }

    @Override
    public AppResult<EmptyEntity> deleteLibrarian(int id) {
        return repository.delete(id);
    }
}
