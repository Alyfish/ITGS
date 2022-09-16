package com.exam.foodit.Model;

import com.google.gson.annotations.SerializedName;

public class Responce_Model {
    @SerializedName("data")
    private Object response;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseModel{ status="+ status + " message="+ message + " response=" + response +'}';
    }
}
