package com.javaTask.urlShortening.controller;
import com.javaTask.urlShortening.model.Url;
import com.javaTask.urlShortening.model.UrlDto;
import com.javaTask.urlShortening.service.UrlService;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class UrlShorteningControllerTest {

    @InjectMocks
    UrlShorteningController urlShorteningController = new UrlShorteningController();
    @Mock
    protected UrlService urlService;


    @Before
    public void setUp() throws Exception {
        Url url = new Url();
        url.setShortLink("short");
        when (urlService.generateShortLink(any())).thenReturn(url);
    }

    @Test
    public void shorten() {
        UrlDto url = new UrlDto();
        url.setOriginalUrl("original");
        ResponseEntity<?> response = urlShorteningController.generateShortLink(url);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
