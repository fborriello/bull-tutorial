package com.expediagroup.web.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Person {
    private final String name;
    private final String surname;
    private final Sex sex;
    private final List<Contact> contacts;
}
