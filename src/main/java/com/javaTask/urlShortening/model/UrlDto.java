package com.javaTask.urlShortening.model;

public class UrlDto {

    private String originalUrl;


    public UrlDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public UrlDto() {
    }
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

}
