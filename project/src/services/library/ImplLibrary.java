package services.library;

import core.AppResult;
import entities.EmptyEntity;
import entities.Library;
import entities.Sorting;
import repository.library.LibraryRepository;

import java.util.List;

public class ImplLibrary implements ILibrary {
    private static volatile ImplLibrary instance;
    private LibraryRepository repository;

    public static ImplLibrary getInstance() {
        if (instance == null) {
            synchronized (ImplLibrary.class) {
                if (instance == null) {
                    instance = new ImplLibrary(LibraryRepository.getInstance());
                }
            }
        }
        return instance;
    }

    private ImplLibrary(LibraryRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppResult<Library> createLibrary(Library library) {
        return repository.save(library);

    }

    @Override
    public AppResult<List<Library>> getLibrariesList(Sorting sorting) {
        return repository.findAll(sorting);
    }

    @Override
    public AppResult<Library> getLibrary(int id) {
        return repository.findById(id);
    }

    @Override
    public AppResult<EmptyEntity> updateLibrary(Library entity) {
        return repository.update(entity);
    }

    @Override
    public AppResult<EmptyEntity> deleteLibrary(int id) {
        return repository.delete(id);
    }
}
