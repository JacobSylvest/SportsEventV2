package com.example.sportseventv2;

public class EventHelperClass {
    String eTitle, description, imgUrl,eventChild,startLAT,startLNG,via1LAT,via1LNG,via2LAT,via2LNG,via3LAT,via3LNG,endLAT,endLNG;

    public EventHelperClass(String eTitle, String description, String imgUrl,String eventChild,
                            String startLAT, String startLNG, String via1LAT, String via1LNG, String via2LAT,
                            String via2LNG, String via3LAT, String via3LNG, String endLAT, String endLNG) {
        this.eTitle = eTitle;
        this.description = description;
        this.imgUrl = imgUrl;
        this.eventChild = eventChild;
        this.startLAT = startLAT;
        this.startLNG = startLNG;
        this.via1LAT = via1LAT;
        this.via1LNG = via1LNG;
        this.via2LAT = via2LAT;
        this.via2LNG = via2LNG;
        this.via3LAT = via3LAT;
        this.via3LNG = via3LNG;
        this.endLAT = endLAT;
        this.endLNG = endLNG;
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
