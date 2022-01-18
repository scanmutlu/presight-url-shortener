package service;

import com.presight.urlsorthener.model.Url;
import com.presight.urlsorthener.repository.UrlRepository;
import com.presight.urlsorthener.service.UrlService;
import com.presight.urlsorthener.service.UrlServiceImpl;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@TestConfiguration
public class UrlServiceTestConfiguration {

    @MockBean
    private UrlRepository urlRepository;

    @Bean
    public UrlService urlService() {
        Url google = new Url();
        google.setUrl("https://www.google.com");
        google.setUrlShortener(4L);
        Mockito.when(urlRepository.findById(google.getUrlShortener()))
                .thenReturn(Optional.of(google));

        Mockito.when(urlRepository.save(any(Url.class))).thenReturn(google);
        return new UrlServiceImpl(urlRepository);
    }

}