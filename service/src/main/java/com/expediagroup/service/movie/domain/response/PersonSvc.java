package com.expediagroup.service.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonSvc {
    private final String fullName;
    private final Sex sex;
    private final List<ContactSvc> contacts;
}
