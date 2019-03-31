package com.hotels.dao.movie.domain.response;

import com.hotels.common.constants.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MovieDaoResponse {
    private final String id;
    private final String title;
    private final String description;
    private final PersonDao director;
    private final List<PersonDao> actors;
    private final Genre genre;
    private final float price;
}
