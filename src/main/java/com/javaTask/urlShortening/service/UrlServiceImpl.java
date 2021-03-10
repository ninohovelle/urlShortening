package com.javaTask.urlShortening.service;

import com.google.common.hash.Hashing;
import com.javaTask.urlShortening.model.Statistics;
import com.javaTask.urlShortening.model.Url;
import com.javaTask.urlShortening.model.UrlDto;
import com.javaTask.urlShortening.repository.StatisticsRepository;
import com.javaTask.urlShortening.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public Url generateShortLink(UrlDto urlDto) {

        if (StringUtils.isNotEmpty(urlDto.getOriginalUrl())) {
            String encodedUrl = encodeUrl(urlDto.getOriginalUrl());
            Url urlToPersist = new Url();
            urlToPersist.setShortLink(encodedUrl);
            urlToPersist.setOriginalUrl(urlDto.getOriginalUrl());
            urlToPersist.setCreationDate(LocalDateTime.now());
            Url urlToRet = persistShortLink(urlToPersist);

            if (urlToRet != null)
                return urlToRet;

            return null;
        }
        return null;
    }

    private String encodeUrl(String originalUrl) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(originalUrl.concat(time.toString()), StandardCharsets.UTF_8).toString();
        return encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink(url);
        return urlToRet;
    }

    @Override
    public void countClicks(String shortLink) {

        if (StringUtils.isNotEmpty(shortLink)) {
            {
                Statistics statisticsToPersist = new Statistics();
                statisticsToPersist.setShortLink(shortLink);
                statisticsToPersist.setClickCount(1);
                statisticsToPersist.setClickTime(LocalDateTime.now());
                persistClicks(statisticsToPersist);
            }
        }
    }
    @Override
    public void persistClicks(Statistics statisticsToPersist) {
        statisticsRepository.save(statisticsToPersist);
    }
}
