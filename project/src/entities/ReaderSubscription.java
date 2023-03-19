package entities;

public class ReaderSubscription {
    private int id;
    private int readerId;
    private int libraryId;
    private int sectionId;
    private int subscriptionPlanId;
    private String startDate;
    private String endDate;

    public ReaderSubscription(int id, int readerId, int libraryId, int sectionId, int subscriptionPlanId, String startDate, String endDate) {
        this.id = id;
        this.readerId = readerId;
        this.libraryId = libraryId;
        this.sectionId = sectionId;
        this.subscriptionPlanId = subscriptionPlanId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSubscriptionPlanId() {
        return subscriptionPlanId;
    }

    public void setSubscriptionPlanId(int subscriptionPlanId) {
        this.subscriptionPlanId = subscriptionPlanId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
 
}
