package com.example.demo.service.impl;

import antlr.StringUtils;
import com.example.demo.exception.BadUrlException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Url;
import com.example.demo.model.User;
import com.example.demo.repository.UrlRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlServiceImpl(UrlRepository urlRepository, UserRepository userRepository) {
        super();
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Url saveUrl(Url url) {
        User user = userRepository.findById(url.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User", "Id", url.getUser().getId()));
        url.setUser(user);
        if (urlRepository.findByShortUrl(url.getShortUrl()) != null) {
            throw new BadUrlException(String.format("A url with name %s exists", url.getShortUrl()));
        }

        // check if short url is alphanumeric
        if (!(url.getShortUrl() != null && url.getShortUrl().matches("^[a-zA-Z0-9-_]*$"))) {
            throw new BadUrlException("Url can contain letters, special characters - & _");
        }

        if (url.getShortUrl() == null || url.getShortUrl().length() == 0) {
            // generate 6 digit code
            url.setShortUrl(UUID.randomUUID().toString().replace("-", "").substring(0, 6));
        }

        return urlRepository.save(url);
    }

    @Override
    public Url getUrlById(long id) {
        return urlRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Url", "Id", id));
    }

    @Override
    public String getActualUrl(String shortUrl) {
        String actualUrl = urlRepository.findByShortUrl(shortUrl).getActualUrl();
        if (actualUrl == null) {
            throw new ResourceNotFoundException("Url", "shortUrl", shortUrl);
        }
        return actualUrl;
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
