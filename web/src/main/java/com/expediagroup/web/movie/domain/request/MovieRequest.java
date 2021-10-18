package com.expediagroup.web.movie.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
@AllArgsConstructor
public class MovieRequest {
    @NotNull
    String title;
    String genre;
}
