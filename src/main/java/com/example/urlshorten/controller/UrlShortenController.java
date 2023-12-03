package com.example.urlshorten.controller;

import com.example.urlshorten.service.UrlShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class UrlShortenController {
    @Autowired
    UrlShortenService shortenService;

    private String DOMAIN = "http://localhost:8080/v1/";
    @GetMapping("/shorten")
    public Map<String, Object> shorten(@RequestParam String longUrl){
        String shortUrl = shortenService.shorten(longUrl);

        Map<String, Object> res = new HashMap<>();
        res.put("url", DOMAIN + shortUrl);
        return res;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> decode(@PathVariable String shortUrl){
        String longUrl = shortenService.getLongUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }


}
