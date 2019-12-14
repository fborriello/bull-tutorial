package com.hotels.web.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.ContactType;
import com.hotels.common.constants.Genre;
import com.hotels.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {
    private final String id;
    private final String title;
    private final String description;
    private final Person director;
    private final List<Person> actors;
    private final Genre genre;
    private final Price price;
}

@AllArgsConstructor
@Getter
class Person {
    private final String name;
    private final String surname;
    private final Sex sex;
    private final List<Contact> contacts;
}

@AllArgsConstructor
@Getter
class Contact {
    private final String value;
    private final ContactType contactType;
}

@AllArgsConstructor
@Getter
class Price {
    private final float netPrice;
    private final float grossPrice;
}