package com.expediagroup.web.movie.domain.request;

import javax.validation.constraints.NotNull;

public record MovieRequest(@NotNull String title, String genre) {
}
