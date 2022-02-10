package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateUrlException extends RuntimeException {
    private String shortUrl;

    public DuplicateUrlException(String shortUrl) {
        super(String.format("A short url with name %s exists", shortUrl));
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
