package service;

import com.presight.urlsorthener.model.Url;
import com.presight.urlsorthener.repository.UrlRepository;
import com.presight.urlsorthener.service.UrlService;
import com.presight.urlsorthener.service.UrlServiceImpl;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@Import(UrlServiceTestConfiguration.class)
public class UrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Test
    public void generate() throws Exception {
        Url google = new Url();
        google.setUrl("https://www.google.com");
        google.setUrlShortener(4L);
        Url generatedUrl = urlService.generate(google);
        assertEquals(google.getUrl(), generatedUrl.getUrl());
    }

    @Test
    public void isValidUrlNotValid() throws Exception {
        assertFalse(urlService.isValidUrl("www.fdfwerwef.fwerwe.we"));
    }

    @Test
    public void isValidUrlValid() throws Exception {
        assertTrue(urlService.isValidUrl("https://www.google.com"));
    }

}
