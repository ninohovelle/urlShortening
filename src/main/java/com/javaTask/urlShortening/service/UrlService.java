package com.javaTask.urlShortening.service;

import com.javaTask.urlShortening.model.Statistics;
import com.javaTask.urlShortening.model.Url;
import com.javaTask.urlShortening.model.UrlDto;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {

    public Url generateShortLink (UrlDto urlDto);
    public Url persistShortLink ( Url url);
    public Url getEncodedUrl (String url);
    public void countClicks(String shortLink);
    public void persistClicks(Statistics statisticsToPersist);
    }
