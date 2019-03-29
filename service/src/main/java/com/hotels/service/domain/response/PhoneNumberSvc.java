package com.hotels.service.domain.response;

import com.hotels.common.constants.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneNumberSvc {
    private final String number;
    private final PhoneType phoneType;
}
