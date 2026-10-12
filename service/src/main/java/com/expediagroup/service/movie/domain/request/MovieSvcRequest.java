package com.expediagroup.service.movie.domain.request;

import jakarta.validation.constraints.NotEmpty;

public record MovieSvcRequest(
        @NotEmpty(message = "title cannot be null or empty!") String title,
        String genre
) {
}
