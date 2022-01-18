package com.presight.urlsorthener.service;

import com.presight.urlsorthener.model.Url;
import com.presight.urlsorthener.repository.UrlRepository;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class UrlServiceImpl implements UrlService{

    protected UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url generate(Url url) throws Exception {
        if(isValidUrl(url.getUrl()))
        return urlRepository.save(url);
        else throw new MalformedURLException("Invalid Url - Cannot create connection!");
    }

    @Override
    public Boolean isValidUrl(String url) {
        try {
            URL urlGiven = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlGiven.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
