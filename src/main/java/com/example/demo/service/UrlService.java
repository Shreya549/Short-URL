package com.example.demo.service;

import com.example.demo.model.Url;

public interface UrlService {
    Url saveUrl(Url url);
    Url getUrlById(String id);
    Url updateUrl(Url url, String id);
    String getActualUrl(String shortUrl);
    void deleteUrl(String id);
}
