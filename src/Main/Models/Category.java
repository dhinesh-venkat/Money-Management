package Main.Models;

public class Category {
    public String categoryName;
    public String desc;

    public Category(String name) {
        this.categoryName = name;
    }


    public Category(String name, String desc) {
        this.categoryName = name;
        this.desc = desc;
    }
}

