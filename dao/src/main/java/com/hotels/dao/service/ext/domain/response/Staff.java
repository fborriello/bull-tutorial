package com.hotels.dao.service.ext.domain.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.hotels.common.constants.Sex;

@AllArgsConstructor
@Getter
public class Staff {
    private final String fullName;
    private final Sex sex;
    private final List<PhoneNumber> phoneNumbers;
}
