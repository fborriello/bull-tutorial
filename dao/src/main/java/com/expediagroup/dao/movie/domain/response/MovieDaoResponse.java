package com.expediagroup.dao.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
