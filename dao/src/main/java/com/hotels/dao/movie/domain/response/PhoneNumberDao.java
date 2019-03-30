package com.hotels.dao.movie.domain.response;

import com.hotels.common.constants.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneNumberDao {
    private final String number;
    private final PhoneType phoneType;
}
