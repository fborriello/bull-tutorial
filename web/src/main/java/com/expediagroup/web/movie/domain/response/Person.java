package com.expediagroup.web.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

public record Person(String name, String surname, Sex sex,
                     List<Contact> contacts) {

}
