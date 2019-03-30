package com.hotels.service.movie.domain.response;

import java.util.List;

import com.hotels.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonSvc {
    private final String fullName;
    private final Sex sex;
    private final List<PhoneNumberSvc> phoneNumbers;
}
