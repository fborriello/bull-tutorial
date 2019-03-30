package com.hotels.web.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Person {
    private final String name;
    private final String surname;
    private final Sex sex;
    private final List<PhoneNumber> phoneNumbers;
}
