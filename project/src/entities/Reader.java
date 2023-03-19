package entities;

public class Reader extends Person {
    private String location;
    private String createdDate;

    public Reader(int id, String name, String email, String phoneNumber, String location, String createdDate) {
        super(id, name, email, phoneNumber);
        this.location = location;
        this.createdDate = createdDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
