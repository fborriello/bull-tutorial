package com.hotels.web.movie.domain.response;

import com.hotels.common.constants.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Contact {
    private final String value;
    private final ContactType contactType;
}
