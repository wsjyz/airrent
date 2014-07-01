package com.eighth.airrent.domain;



/**
 * Created by lk.zh on 2014/5/20.
 */

public class Information {


    private String informationId;
    private String title;
    private String content;
    private String postTime;
    private String status;

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
