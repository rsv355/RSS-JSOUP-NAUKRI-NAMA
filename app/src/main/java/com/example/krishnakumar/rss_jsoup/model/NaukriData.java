package com.example.krishnakumar.rss_jsoup.model;

import java.util.ArrayList;

/**
 * Created by krishnakumar on 23-12-2015.
 */
public  class NaukriData{
    public  String Headline;
    public  String ImgUrl;
    public  String publishDate;
    public ArrayList<String> data;

    public NaukriData() {
        data = new ArrayList<>();
    }

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

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void insertIntoData(String newData) {
        data.add(newData);
    }

    @Override
    public String toString() {
        return "NaukriData{" +
                " data=" + data +
                '}';
    }
}