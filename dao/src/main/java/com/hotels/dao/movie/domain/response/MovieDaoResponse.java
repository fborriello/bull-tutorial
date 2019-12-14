package com.hotels.dao.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.ContactType;
import com.hotels.common.constants.Genre;
import com.hotels.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieDaoResponse {
    private final String id;
    private final String title;
    private final String description;
    private final PersonDao director;
    private final List<PersonDao> actors;
    private final Genre genre;
    private final float price;
}

@AllArgsConstructor
@Getter
class PersonDao {
    private final String fullName;
    private final Sex sex;
    private final List<ContactDao> contacts;
}

@AllArgsConstructor
@Getter
class ContactDao {
    private final String value;
    private final ContactType contactType;
}