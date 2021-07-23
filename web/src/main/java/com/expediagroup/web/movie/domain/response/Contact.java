package com.expediagroup.web.movie.domain.response;

import com.expediagroup.common.constants.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Contact {
    private final String value;
    private final ContactType contactType;
}
