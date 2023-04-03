package services.sections;

import core.AppResult;
import entities.EmptyEntity;
import entities.Library;
import entities.Section;
import entities.Sorting;

import java.util.List;

public interface ISection {
    public AppResult<Section> createSection(Section section);

    public AppResult<List<Section>> getSectionsList(Sorting sorting);

    public AppResult<Section> getSection(int id);

    public AppResult<EmptyEntity> updateSection(Section entity);

    public AppResult<EmptyEntity> deleteSection(int id);

    AppResult<List<Section>> findSectionInLibrary(int libraryId);
}
