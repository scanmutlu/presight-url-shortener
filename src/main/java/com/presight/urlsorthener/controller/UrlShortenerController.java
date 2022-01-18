package com.presight.urlsorthener.controller;

import com.presight.urlsorthener.model.Url;
import com.presight.urlsorthener.repository.UrlRepository;
import com.presight.urlsorthener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
public class UrlShortenerController {

    protected Logger logger = Logger.getLogger(UrlShortenerController.class
            .getName());
    protected UrlRepository urlRepository;
    protected UrlService urlService;
    protected ResourceLoader resourceLoader;

    @Autowired
    public UrlShortenerController(UrlRepository urlRepository, UrlService urlService, ResourceLoader resourceLoader) {
        this.urlRepository = urlRepository;
        this.urlService = urlService;
        this.resourceLoader = resourceLoader;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/generate")
    public ResponseEntity<Object> generate(@RequestBody Url url) throws Exception {
        try{
            return new ResponseEntity<>(urlService.generate(url), HttpStatus.OK);
        }catch (MalformedURLException e){
            return new ResponseEntity<>("Invalid URL",HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/goto/url")
    public ResponseEntity<Object> goTo(@RequestParam String urlShortener) throws Exception {

        Url url = urlRepository.findById(Long.valueOf(urlShortener)).get();
        URI uri = new URI(url.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/openapi")
    public ResponseEntity<Object> openapi() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:/openapi.yaml");

        if (resource.exists()) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType("application/x-yaml"))
                    .header("Content-Disposition", "attachment; filename=myfile.yaml")
                    .body(new InputStreamResource(resource.getInputStream()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
