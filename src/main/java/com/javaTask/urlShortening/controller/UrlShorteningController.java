package com.javaTask.urlShortening.controller;

import com.javaTask.urlShortening.model.Url;
import com.javaTask.urlShortening.model.UrlDto;
import com.javaTask.urlShortening.model.UrlErrorResponseDto;
import com.javaTask.urlShortening.model.UrlResponseDto;
import com.javaTask.urlShortening.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UrlShorteningController {

    @Autowired
    private UrlService urlService;
    @PostMapping("/shorten")
    public ResponseEntity<?> generateShortLink (@RequestBody UrlDto urlDto)
    {
        Url urlToRet = urlService.generateShortLink(urlDto);

        if (urlToRet != null) {
            UrlResponseDto urlResponseDto = new UrlResponseDto();
            urlResponseDto.setShortLink(urlToRet.getShortLink());
            urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
            return new ResponseEntity<UrlResponseDto>(urlResponseDto , HttpStatus.OK);

        }
        else {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setStatus("404");
            urlErrorResponseDto.setError("Error, please try again");

            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
        }

    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl (@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if (StringUtils.isEmpty(shortLink)) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setStatus("400");
            urlErrorResponseDto.setError("Invalid URL");
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
        }

        Url urlToReturn = urlService.getEncodedUrl(shortLink);
        if (urlToReturn == null) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setStatus("400");
            urlErrorResponseDto.setError("URL not found in DB");
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
        }
        urlService.countClicks(shortLink);
        response.sendRedirect(urlToReturn.getOriginalUrl());

        return null;
    }
}
