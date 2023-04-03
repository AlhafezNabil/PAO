package services.librarian;

import core.AppResult;
import entities.EmptyEntity;
import entities.Librarian;
import entities.Sorting;

import java.util.List;

public interface ILibrarian {
    public AppResult<Librarian> createLibrarian(Librarian librarian);

    public AppResult<List<Librarian>> getLibrariesList(Sorting sorting);

    public AppResult<Librarian> getLibrarian(int id);

    public AppResult<EmptyEntity> updateLibrarian(Librarian entity);

    public AppResult<EmptyEntity> deleteLibrarian(int id);


}

