package com.example.demo.controller;

import com.example.demo.model.Url;
import com.example.demo.model.User;
import com.example.demo.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {
    private final UrlService urlService;

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
    @PostMapping("/save")
    public ResponseEntity<Url> saveUrl(@RequestBody Url url){
        return new ResponseEntity<Url>(urlService.saveUrl(url), HttpStatus.CREATED);
    }

    //get url
    @GetMapping("/get/{id}")
    public ResponseEntity<Url> getUrlById(@PathVariable("id") long id){
        return new ResponseEntity<Url>(urlService.getUrlById(id), HttpStatus.OK);
    }

    //update url
    @PutMapping("edit/{id}")
    public ResponseEntity<Url> updateUrlById(@PathVariable("id") long id, @RequestBody Url url){
        return new ResponseEntity<Url>(urlService.updateUrl(url, id), HttpStatus.OK);
    }

    //delete url
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUrl(@PathVariable("id") long id){
        urlService.deleteUrl(id);
        return new ResponseEntity<String>("Url deleted successfully", HttpStatus.OK);
    }
}
