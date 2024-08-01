package dto;

public class CategoryDto {

    private String catID;
    private String catName;

    public CategoryDto() {

    }

    public CategoryDto(String catID, String catName) {
        this.catID = catID;
        this.catName = catName;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "CategoryDto [catID=" + catID + ", catName=" + catName + "]";
    }

}

