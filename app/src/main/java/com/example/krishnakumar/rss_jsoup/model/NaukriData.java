package com.example.krishnakumar.rss_jsoup.model;

/**
 * Created by krishnakumar on 23-12-2015.
 */
public  class NaukriData{
    public  String Headline;
    public  String ImgUrl;
    public  String publishDate;

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public  String getHeadline() {
        return Headline;
    }

    public  void setHeadline(String headline) {
        Headline = headline;
    }

    public  String getImgUrl() {
        return ImgUrl;
    }

    public  void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }
}