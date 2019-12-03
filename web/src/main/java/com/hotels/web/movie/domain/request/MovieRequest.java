package com.hotels.web.movie.domain.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {
    @NotNull
    private String title;
    private String genre;
}
