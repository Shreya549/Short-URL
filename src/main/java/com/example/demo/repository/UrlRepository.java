package com.example.demo.repository;

import com.example.demo.model.Url;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findAllByUser(User user);
    Url findByShortUrl(String shortUrl);
    void deleteAllByUser(User user);
}
