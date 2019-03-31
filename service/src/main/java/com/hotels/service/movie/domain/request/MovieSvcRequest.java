package com.hotels.service.movie.domain.request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSvcRequest {
    @NotEmpty(message = "title cannot be null or empty!")
    private String title;
    private String genre;
}
