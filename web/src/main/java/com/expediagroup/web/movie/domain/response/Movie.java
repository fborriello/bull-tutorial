package com.expediagroup.web.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Genre;

public record Movie(String id, String title, String description,
                    Person director,
                    List<Person> actors, Genre genre,
                    Price price) {
}
