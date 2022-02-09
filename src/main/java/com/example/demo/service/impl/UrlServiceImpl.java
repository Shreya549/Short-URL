package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Url;
import com.example.demo.model.User;
import com.example.demo.repository.UrlRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{
    private UrlRepository urlRepository;
    private UserRepository userRepository;

    public UrlServiceImpl(UrlRepository urlRepository, UserRepository userRepository) {
        super();
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Url saveUrl(Url url) {
        User user = userRepository.findById(url.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User", "Id", url.getUser().getId()));
        url.setUser(user);
        return urlRepository.save(url);
    }

    @Override
    public Url getUrlById(long id) {
        return urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Url", "Id", id));
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
