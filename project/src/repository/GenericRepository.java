package repository;

import core.AppResult;
import entities.EmptyEntity;
import entities.Sorting;

import java.util.List;
import java.util.Optional;

/**
 * @author cvoinea
 * <p>
 * This is a generic contract for repositories.
 * <p>
 * Repositories are contracts between the domain layer and the persistence layer.
 * All the CRUD operations (Create, read, update, and delete) can be implemented with the help of a repository interface.
 */
public interface GenericRepository<T> {

    AppResult<T> save(T entity);

    AppResult<List<T>> findAll(Sorting options);

    AppResult<T> findById(int id);

    AppResult<EmptyEntity> update(T entity);

    AppResult<EmptyEntity>  delete(int id);
}
