package com.expediagroup.web.movie.domain.request;

import jakarta.validation.constraints.NotNull;

public record MovieRequest(@NotNull String title, String genre) {
}
