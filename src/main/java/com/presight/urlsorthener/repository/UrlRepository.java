package com.presight.urlsorthener.repository;

import com.presight.urlsorthener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
