package com.expediagroup.service.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Genre;

public record MovieSvcResponse(String id, String title, String description,
                               PersonSvc director,
                               List<PersonSvc> actors,
                               Genre genre,
                               PriceSvc price) {
}
