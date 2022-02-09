package com.example.demo.controller;

import com.example.demo.model.Url;
import com.example.demo.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save/url")
public class UrlController {
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        super();
        this.urlService = urlService;
    }

    //test
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Endpoint working", HttpStatus.OK);
    }

    //create url
    @PostMapping
    public ResponseEntity<Url> saveUrl(@RequestBody Url url){
        return new ResponseEntity<Url>(urlService.saveUrl(url), HttpStatus.CREATED);
    }

    //get url
    @GetMapping("{id}")
    public ResponseEntity<Url> getUrlById(@PathVariable("id") long id){
        return new ResponseEntity<Url>(urlService.getUrlById(id), HttpStatus.OK);
    }

    //update url
    @PutMapping("{id}")
    public ResponseEntity<Url> updateUrlById(@PathVariable("id") long id, @RequestBody Url url){
        return new ResponseEntity<Url>(urlService.updateUrl(url, id), HttpStatus.OK);
    }

    //delete url
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable("id") long id){
        urlService.deleteUrl(id);
        return new ResponseEntity<String>("Url deleted successfully", HttpStatus.OK);
    }
}
