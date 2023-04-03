package services.reader;

import core.AppResult;
import entities.EmptyEntity;
import entities.Librarian;
import entities.Reader;
import entities.Sorting;

import java.util.List;
public interface IReader {
    public AppResult<Reader> createReader(Reader reader);

    public AppResult<List<Reader>> getReadersList(Sorting sorting);

    public AppResult<Reader> getReader(int id);

    public AppResult<EmptyEntity> updateReader(Reader entity);

    public AppResult<EmptyEntity> deleteReader(int id);
}
