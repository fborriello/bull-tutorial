package com.hotels.dao.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieDaoRequest {
    private final String title;
    private final String genre;
}
