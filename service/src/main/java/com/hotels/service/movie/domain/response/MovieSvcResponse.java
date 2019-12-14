package com.hotels.service.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.ContactType;
import com.hotels.common.constants.Genre;
import com.hotels.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieSvcResponse {
    private final String id;
    private final String title;
    private final String description;
    private final PersonSvc director;
    private final List<PersonSvc> actors;
    private final Genre genre;
    private final PriceSvc price;
}

@AllArgsConstructor
@Getter
class ContactSvc {
    private final String value;
    private final ContactType contactType;
}

@AllArgsConstructor
@Getter
class PersonSvc {
    private final String fullName;
    private final Sex sex;
    private final List<ContactSvc> contacts;
}

@AllArgsConstructor
@Getter
class PriceSvc {
    private final float netPrice;
    private final float grossPrice;
}
