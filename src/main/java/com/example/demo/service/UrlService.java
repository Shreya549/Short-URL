package com.example.demo.service;

import com.example.demo.model.Url;

public interface UrlService {
    Url saveUrl(Url url);
    Url getUrlById(long id);
    Url updateUrl(Url url, long id);
    void deleteUrl(long id);
}
