package com.hotels.service.movie.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PriceSvc {
    private final float netPrice;
    private final float grossPrice;
}
