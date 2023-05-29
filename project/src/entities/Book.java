package entities;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private int sectionId;
    private int totalCopies;
    private int availableCopies;
    private String publicationDate;

    public Book(int id,
                String title,
                int authorId,
                int sectionId,
                String publicationDate,
                int totalCopies,
                int availableCopies) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.sectionId = sectionId;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.publicationDate = publicationDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", sectionId=" + sectionId +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}
