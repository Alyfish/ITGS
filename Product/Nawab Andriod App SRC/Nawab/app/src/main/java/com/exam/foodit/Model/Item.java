package com.exam.foodit.Model;

import com.google.gson.annotations.SerializedName;

public class Item {

   public Item(){

   }

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("catid")
    private String catid;

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private String price;


    @SerializedName("desc")
    private String desc;

    public String getid() {
        return id;
    }

    public void setCid(String cid) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
