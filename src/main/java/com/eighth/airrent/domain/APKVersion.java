package com.eighth.airrent.domain;


/**
 * Created by dam on 2014/7/11.
 */

public class APKVersion {
    private String lastVersionCode;//最新版本号
    private String downloadUrl;//下载链接

    public String getLastVersionCode() {
        return lastVersionCode;
    }

    public void setLastVersionCode(String lastVersionCode) {
        this.lastVersionCode = lastVersionCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
