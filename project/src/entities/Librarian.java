package entities;

public class Librarian extends Person {
    private int libraryId;
    private String startedWorkAt;

    public Librarian(int id, String name, String email, String phoneNumber, int libraryId) {
        super(id, name, email, phoneNumber);
        this.libraryId = libraryId;
    }


    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getStartedWorkAt() {
        return startedWorkAt;
    }

    public void setStartedWorkAt(String startedWorkAt) {
        this.startedWorkAt = startedWorkAt;
    }
}

