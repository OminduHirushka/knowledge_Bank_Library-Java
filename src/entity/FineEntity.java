package entity;

public class FineEntity {

    private String fineID;
    private String brID;
    private double amount;
    private String issuedDate;
    private String paidDate;
    private String status;

    public FineEntity() {
    }

    public FineEntity(String fineID, String brID, double amount, String issuedDate, String paidDate, String status) {
        this.fineID = fineID;
        this.brID = brID;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.paidDate = paidDate;
        this.status = status;
    }

    public String getFineID() {
        return fineID;
    }

    public void setFineID(String fineID) {
        this.fineID = fineID;
    }

    public String getBrID() {
        return brID;
    }

    public void setBrID(String brID) {
        this.brID = brID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FineEntity [fineID=" + fineID + ", brID=" + brID + ", amount=" + amount + ", issuedDate=" + issuedDate
                + ", paidDate=" + paidDate + ", status=" + status + "]";
    }

}
