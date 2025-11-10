package com.model;

public class Product {
    private int id;
    private String name_vi;
    private String description_vi;
    private String name_en;
    private String description_en;
    private double price;
    private double weight;
    private String categoryName; // hiển thị loại
    private int categoryID;      // ID loại

    public Product() {}

    public Product(int id, String name, String description, double price, double weight, String categoryName) {
        this.id = id;
        this.name_vi = name;           // tạm dùng cho tiếng Việt
        this.description_vi = description;
        this.price = price;
        this.weight = weight;
        this.categoryName = categoryName;
    }

    // ---- Getter & Setter ----
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName_vi() { return name_vi; }
    public void setName_vi(String name_vi) { this.name_vi = name_vi; }

    public String getDescription_vi() { return description_vi; }
    public void setDescription_vi(String description_vi) { this.description_vi = description_vi; }

    public String getName_en() { return name_en; }
    public void setName_en(String name_en) { this.name_en = name_en; }

    public String getDescription_en() { return description_en; }
    public void setDescription_en(String description_en) { this.description_en = description_en; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
}
