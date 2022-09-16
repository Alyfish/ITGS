package com.exam.foodit.Model;

import com.google.gson.annotations.SerializedName;

public class Category {
   /* { cid : 1 , cname : "see food" }*/
    @SerializedName("cid")
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @SerializedName("cname")
    private String cname;
}
