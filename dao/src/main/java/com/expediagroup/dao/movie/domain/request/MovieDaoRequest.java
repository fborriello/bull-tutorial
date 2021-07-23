package com.expediagroup.dao.movie.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieDaoRequest {
    private final String title;
    private final String genre;
}
