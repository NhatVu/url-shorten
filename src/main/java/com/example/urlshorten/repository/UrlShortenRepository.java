package com.example.urlshorten.repository;

import com.example.urlshorten.entity.UrlShortenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenRepository extends MongoRepository<UrlShortenEntity, Long> {
    public UrlShortenEntity getByLongUrl(String longUrl);
    public UrlShortenEntity getByShortUrl(String shortUrl);
}
