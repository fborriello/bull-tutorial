package com.expediagroup.service.movie.domain.request;

import jakarta.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSvcRequest {
    @NotEmpty(message = "title cannot be null or empty!")
    private String title;
    private String genre;
}
