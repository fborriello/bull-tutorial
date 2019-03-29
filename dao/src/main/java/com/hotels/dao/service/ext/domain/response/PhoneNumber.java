package com.hotels.dao.service.ext.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.hotels.common.constants.PhoneType;

@AllArgsConstructor
@Getter
public class PhoneNumber {
    private final String number;
    private final PhoneType phoneType;
}
