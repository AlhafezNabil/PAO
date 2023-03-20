package services.library;

import java.util.List;

import entities.Library;
import entities.Sorting;

public interface ILibrary {
    public void createLibrary(Library library);  

    public List<Library> getLibrariesList(Sorting sorting);    

    public Library getLibrary(int id);

    public void updateLibrary(int id, Library library);    
    
    public void deleteLibrary(int id);    
}
