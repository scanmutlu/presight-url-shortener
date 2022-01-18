package com.presight.urlsorthener.service;

import com.presight.urlsorthener.model.Url;

public interface UrlService {
    Url generate(Url url) throws Exception;
    Boolean isValidUrl(String url);
}
