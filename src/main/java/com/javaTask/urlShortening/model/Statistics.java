package com.javaTask.urlShortening.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
public class Statistics {
    @Id
    @GeneratedValue
    private long id;
    private String shortLink;
    private long clickCount;
    private LocalDateTime clickTime;

    public Statistics(long id, String shortLink, long clickCount, LocalDateTime clickTime) {
        this.id = id;
        this.shortLink = shortLink;
        this.clickCount = clickCount; //always 1
        this.clickTime = clickTime;
    }
    public Statistics() {

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getClickTime() {
        return clickTime;
    }

    public void setClickTime(LocalDateTime clickTime) {
        this.clickTime = clickTime;
    }

}