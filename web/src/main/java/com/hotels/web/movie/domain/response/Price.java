package com.hotels.web.movie.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Price {
    private final float netPrice;
    private final float grossPrice;
}
