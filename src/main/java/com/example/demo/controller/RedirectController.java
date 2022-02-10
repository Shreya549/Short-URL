package com.example.demo.controller;

import com.example.demo.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/")
public class RedirectController {

    private UrlService urlService;

    public RedirectController(UrlService urlService) {
        super();
        this.urlService = urlService;
    }

    @GetMapping("{url}")
    public ResponseEntity<Object> redirectToExternalUrl(@PathVariable("url") String url) throws URISyntaxException {
        URI externalUrl = new URI(urlService.getActualUrl(url));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(externalUrl);
        return new ResponseEntity<>(httpHeaders, HttpStatus.PERMANENT_REDIRECT);
    }
}
