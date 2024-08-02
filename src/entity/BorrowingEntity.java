package entity;

public class BorrowingEntity {
    private String brID;
    private String bookID;
    private String memberID;
    private String brDate;
    private String dueDate;
    private String returnDate;
    private String status;

    public BorrowingEntity() {
    }

    public BorrowingEntity(String brID, String bookID, String memberID, String brDate, String dueDate, String returnDate, String status) {
        this.brID = brID;
        this.bookID = bookID;
        this.memberID = memberID;
        this.brDate = brDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getBrID() {
        return brID;
    }

    public void setBrID(String brID) {
        this.brID = brID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getBrDate() {
        return brDate;
    }

    public void setBrDate(String brDate) {
        this.brDate = brDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowingEntity [brID=" + brID + ", bookID=" + bookID + ", memberID=" + memberID + ", brDate=" + brDate
                + ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", status=" + status + "]";
    }

}
