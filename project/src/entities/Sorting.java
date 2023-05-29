package entities;

public class Sorting {
    private Boolean isDateSorted;
    private Boolean isAlphabeticSorted;

    public Sorting() {
    }

    public static class Builder {
        private Boolean isDateSorted = false;
        private Boolean isAlphabeticSorted = false;

        public Builder dateSorted(boolean isDateSorted) {
            this.isDateSorted = isDateSorted;
            return this;
        }

        public Builder alphabeticSorted(boolean isAlphabeticSorted) {
            this.isAlphabeticSorted = isAlphabeticSorted;
            return this;
        }

        public Sorting build() {
            Sorting sorting = new Sorting();
            sorting.isDateSorted = this.isDateSorted;
            sorting.isAlphabeticSorted = this.isAlphabeticSorted;
            return sorting;
        }
    }

    public Boolean getDateSorted() {
        return isDateSorted;
    }

    public Boolean getAlphabeticSorted() {
        return isAlphabeticSorted;
    }
}
