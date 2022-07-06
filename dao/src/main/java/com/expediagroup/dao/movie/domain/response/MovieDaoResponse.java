package com.expediagroup.dao.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Genre;

public record MovieDaoResponse(String id, String title, String description,
                               PersonDao director,
                               List<PersonDao> actors,
                               Genre genre, float price) {
}
