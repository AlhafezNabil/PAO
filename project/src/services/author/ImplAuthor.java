package services.author;

import core.AppResult;
import entities.EmptyEntity;
import entities.Librarian;
import entities.Sorting;
import repository.librarian.LibrarianRepository;
import services.librarian.ILibrarian;

import java.util.List;


import core.AppResult;
import entities.Author;
import entities.EmptyEntity;
import entities.Sorting;
import repository.author.AuthorRepository;

import java.util.List;

public class ImplAuthor implements IAuthor {
    private static volatile ImplAuthor instance;
    private AuthorRepository repository;

    public static ImplAuthor getInstance() {
        if (instance == null) {
            synchronized (ImplAuthor.class) {
                if (instance == null) {
                    instance = new ImplAuthor(AuthorRepository.getInstance());
                }
            }
        }
        return instance;
    }

    private ImplAuthor(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppResult<Author> createAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    public AppResult<List<Author>> getAuthorsList(Sorting sorting) {
        return repository.findAll(sorting);
    }

    @Override
    public AppResult<Author> getAuthor(int id) {
        return repository.findById(id);
    }

    @Override
    public AppResult<EmptyEntity> updateAuthor(Author entity) {
        return repository.update(entity);
    }

    @Override
    public AppResult<EmptyEntity> deleteAuthor(int id) {
        return repository.delete(id);
    }
}
