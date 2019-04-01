package com.hotels.dao.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonDao {
    private final String fullName;
    private final Sex sex;
    private final List<ContactDao> contacts;
}
