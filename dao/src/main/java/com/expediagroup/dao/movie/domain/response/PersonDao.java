package com.expediagroup.dao.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonDao {
    private final String fullName;
    private final Sex sex;
    private final List<ContactDao> contacts;
}
