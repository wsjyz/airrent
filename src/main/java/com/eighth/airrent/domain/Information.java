package com.eighth.airrent.domain;



/**
 * Created by lk.zh on 2014/5/20.
 */

public class Information extends BaseDomain{


    private String informationId;//主键
    private String title;//标题
    private String content;//内容
    private String postTime;//发布时间yyyy-MM-dd HH:mm:ss
    private String fileUril;//图片链接 http://xxx.jpg
    private String status;//状态PUB已发布UNPUB未发布

    public String getFileUril() {
        return fileUril;
    }

    public void setFileUril(String fileUril) {
        this.fileUril = fileUril;
    }

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
