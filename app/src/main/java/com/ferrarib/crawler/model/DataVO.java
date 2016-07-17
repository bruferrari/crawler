package com.ferrarib.crawler.model;

import java.util.Calendar;

/**
 * Created by bruno on 7/1/16.
 */

public class DataVO {

    public String title;
    public String description;
    public String source;
    public Calendar publishingDate;
    public int imageId;

    public DataVO(String title, String description, String source,
                  Calendar publishingDate, int imageId) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.publishingDate = publishingDate;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Calendar getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Calendar publishingDate) {
        this.publishingDate = publishingDate;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
