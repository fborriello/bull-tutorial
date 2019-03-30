package com.hotels.dao.movie.domain.response;

import com.hotels.common.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PersonDao {
    private final String fullName;
    private final Sex sex;
    private final List<PhoneNumberDao> phoneNumbers;
}
