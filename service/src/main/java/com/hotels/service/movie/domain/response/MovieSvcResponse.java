package com.hotels.service.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieSvcResponse {
    private final String id;
    private final String title;
    private final String description;
    private final PersonSvc director;
    private final List<PersonSvc> actors;
    private final Genre genre;
    private final PriceSvc price;
}
