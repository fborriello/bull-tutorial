package com.hotels.service.movie.domain.response;

import com.hotels.common.constants.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContactSvc {
    private final String value;
    private final ContactType contactType;
}
