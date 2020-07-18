package com.lin.ego.base.vo;

import java.util.Arrays;

public class SearchItem {
    private Long id;
    private String title;
    private  String sellPoint;
    private Long price;
    private String[] images;
    private String categoryName;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String[] getImages() {

        this.images= this.image.split(",");
        return this.images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @Override
    public String toString() {
        return "SearchItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", images=" + Arrays.toString(images) +
                ", categoryName='" + categoryName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
