package entities;

public class Sorting {
    private Boolean isDateSorted;
    private Boolean isAlphabeticSorted;

    public Sorting() {

    }

    public Sorting(boolean isDateSorted, boolean isAlphabeticSorted) {
        this.isDateSorted = isDateSorted;
        this.isAlphabeticSorted = isAlphabeticSorted;

    }


    public Sorting(Boolean isDateSorted, Boolean isAlphabeticSorted) {
        this.isDateSorted = isDateSorted;
        this.isAlphabeticSorted = isAlphabeticSorted;
    }
}
