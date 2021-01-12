package com.example.sportseventv2;

public class EventHelperClass {
    String eTitle, description, imgUrl,eventChild;

    public EventHelperClass(String eTitle, String description, String imgUrl,String eventChild) {
        this.eTitle = eTitle;
        this.description = description;
        this.imgUrl = imgUrl;
        this.eventChild = eventChild;
    }

    public String getEventChild() {
        return eventChild;
    }

    public void setEventChild(String eventChild) {
        this.eventChild = eventChild;
    }

    public String geteTitle() {
        return eTitle;
    }

    public void seteTitle(String title) {
        this.eTitle = eTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
