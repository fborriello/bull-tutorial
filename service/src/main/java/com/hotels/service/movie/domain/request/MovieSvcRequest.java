package com.hotels.service.movie.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSvcRequest {
    private String title;
    private String genre;
}
