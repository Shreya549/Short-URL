package com.example.demo.repository;

import com.example.demo.model.Url;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    public List<Url> findAllByUser(User user);
    public Url findByShortUrl(String shortUrl);
    public void deleteAllByUser(User user);
}
