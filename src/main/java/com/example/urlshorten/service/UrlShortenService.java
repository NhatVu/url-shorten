package com.example.urlshorten.service;

import com.example.urlshorten.entity.UrlShortenEntity;
import com.example.urlshorten.repository.UrlShortenRepository;
import com.example.urlshorten.utils.Base62Conversion;
import com.example.urlshorten.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UrlShortenService {
    /*
    in real world, we can call SnowFlake service, to generate unique ID.
    In this example, to make it simple, use internal snowFlake service
     */

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private UrlShortenRepository repository;

    @Autowired
    private Base62Conversion conversion;

    public String shorten(String longUrl){
        /*
        1. if longUrl exist in db -> return shorten string
        2. if longUrl exist,
            2.1: call snowflake to get id
            2.2 encode base62 id
            2.3 save to database
            2.4 return shorten string
         */
        UrlShortenEntity entity = repository.getByLongUrl(longUrl);
        if(entity != null){
            return entity.getShortUrl();
        }

        long id = snowFlake.nextId();
        String shortUrl = conversion.encode(id);

        entity = UrlShortenEntity.builder()
                .id(id)
                .shortUrl(shortUrl)
                .longUrl(longUrl)
                .build();
        repository.save(entity);
        return shortUrl;
    }

    public String getLongUrl(String shortUrl){
        UrlShortenEntity entity = repository.getByShortUrl(shortUrl);
        if(entity == null){
            throw new IllegalArgumentException("shortUrl is not valid");
        }
        return entity.getLongUrl();
    }
}
