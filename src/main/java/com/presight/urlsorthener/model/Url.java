package com.presight.urlsorthener.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Url extends BaseEntity {

    @Column(name = "url")
    private String url;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_shortener")
    private Long urlShortener;
}
