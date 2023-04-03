package services.sections;

import core.AppResult;
import entities.EmptyEntity;
import entities.Section;
import entities.Sorting;
import repository.setction.SectionRepository;
import services.sections.ISection;

import java.util.List;

public class ImplSection implements ISection {
    private static volatile ImplSection instance;
    private SectionRepository repository;

    public static ImplSection getInstance() {
        if (instance == null) {
            synchronized (ImplSection.class) {
                if (instance == null) {
                    instance = new ImplSection(SectionRepository.getInstance());
                }
            }
        }
        return instance;
    }

    private ImplSection(SectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppResult<Section> createSection(Section section) {
        return repository.save(section);
    }

    @Override
    public AppResult<List<Section>> getSectionsList(Sorting sorting) {
        return repository.findAll(sorting);
    }

    public AppResult<List<Section>> findSectionInLibrary(int libraryId) {
        return repository.findSectionInLibrary(libraryId);
    }

    @Override
    public AppResult<Section> getSection(int id) {
        return repository.findById(id);
    }

    @Override
    public AppResult<EmptyEntity> updateSection(Section entity) {
        return repository.update(entity);
    }

    @Override
    public AppResult<EmptyEntity> deleteSection(int id) {
        return repository.delete(id);
    }
}
