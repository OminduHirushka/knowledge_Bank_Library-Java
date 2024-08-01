package entity;

public class BookEntity {

    private String bookID;
    private String title;
    private String description;
    private String catID;
    private String author;
    private int nob;

    public BookEntity() {
    }

    public BookEntity(String bookID, String title, String description, String catID, String author, int nob) {
        this.bookID = bookID;
        this.title = title;
        this.description = description;
        this.catID = catID;
        this.author = author;
        this.nob = nob;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNob() {
        return nob;
    }

    public void setNob(int nob) {
        this.nob = nob;
    }

    @Override
    public String toString() {
        return "BookEntity [bookID=" + bookID + ", title=" + title + ", description=" + description + ", catID=" + catID
                + ", author=" + author + ", nob=" + nob + "]";
    }

}
