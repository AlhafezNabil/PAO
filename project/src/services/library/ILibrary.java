package services.library;

import java.util.List;

import core.AppResult;
import entities.EmptyEntity;
import entities.Library;
import entities.Sorting;

public interface ILibrary {
    public AppResult<Library> createLibrary(Library library);

    public AppResult<List<Library>> getLibrariesList(Sorting sorting);

    public AppResult<Library> getLibrary(int id);

    public AppResult<EmptyEntity> updateLibrary(Library entity);

    public AppResult<EmptyEntity> deleteLibrary(int id);


}

