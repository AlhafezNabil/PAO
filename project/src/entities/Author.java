package entities;


public class Author extends Person {

    private String birthDate;

    public Author(int id, String name, String birthDate) {
        super(id, name, null, null);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


}
