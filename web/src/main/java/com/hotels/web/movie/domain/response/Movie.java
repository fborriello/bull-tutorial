package com.hotels.web.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {
    private final String id;
    private final String title;
    private final String description;
    private final Person director;
    private final List<Person> actors;
    private final Genre genre;
    private final Price price;
}
