package entities;

public class Section {
    private int id;
    private String name;
    private int libraryId;

    public Section(String name, int libraryId) {
        this.name = name;
        this.libraryId = libraryId;


    }

    public Section(int id, String name, int libraryId) {
        this.id = id;
        this.name = name;
        this.libraryId = libraryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", libraryId=" + libraryId +
                '}';
    }
}
