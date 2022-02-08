package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;
import com.example.demo.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    private UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        super();
        this.urlRepository = urlRepository;
    }

    @Override
    public Url saveUrl(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url getUrlById(long id) {
        return urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public Url updateUrl(Url url, long id) {
        Url existingUrl = urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Url", "Id", id));
        existingUrl.setActualUrl(url.getActualUrl());
        existingUrl.setShortUrl(url.getShortUrl());

        urlRepository.save(existingUrl);
        return existingUrl;
    }

    @Override
    public void deleteUrl(long id) {
        urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        urlRepository.deleteById(id);
    }
}
