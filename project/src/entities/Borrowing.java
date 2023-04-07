package entities;

public class Borrowing {
    private int id;
    private int bookId;
    private int readerId;
    private String borrowedDate;
    private String dueDate;
    private String returnedDate;

    public Borrowing(int id, int bookId, int readerId, String borrowedDate, String dueDate, String returnedDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    public Borrowing(int id, int bookId, int readerId, String borrowedDate, String dueDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

 
}
