package com.hotels.web.domain.response;

import com.hotels.common.constants.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneNumber {
    private final String number;
    private final PhoneType phoneType;
}
