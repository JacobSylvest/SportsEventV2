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

    public String getStartLAT() {
        return startLAT;
    }

    public void setStartLAT(String startLAT) {
        this.startLAT = startLAT;
    }

    public String getStartLNG() {
        return startLNG;
    }

    public void setStartLNG(String startLNG) {
        this.startLNG = startLNG;
    }

    public String getVia1LAT() {
        return via1LAT;
    }

    public void setVia1LAT(String via1LAT) {
        this.via1LAT = via1LAT;
    }

    public String getVia1LNG() {
        return via1LNG;
    }

    public void setVia1LNG(String via1LNG) {
        this.via1LNG = via1LNG;
    }

    public String getVia2LAT() {
        return via2LAT;
    }

    public void setVia2LAT(String via2LAT) {
        this.via2LAT = via2LAT;
    }

    public String getVia2LNG() {
        return via2LNG;
    }

    public void setVia2LNG(String via2LNG) {
        this.via2LNG = via2LNG;
    }

    public String getVia3LAT() {
        return via3LAT;
    }

    public void setVia3LAT(String via3LAT) {
        this.via3LAT = via3LAT;
    }

    public String getVia3LNG() {
        return via3LNG;
    }

    public void setVia3LNG(String via3LNG) {
        this.via3LNG = via3LNG;
    }

    public String getEndLAT() {
        return endLAT;
    }

    public void setEndLAT(String endLAT) {
        this.endLAT = endLAT;
    }

    public String getEndLNG() {
        return endLNG;
    }

    public void setEndLNG(String endLNG) {
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
