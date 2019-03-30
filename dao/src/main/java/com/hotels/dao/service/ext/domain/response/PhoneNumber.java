package com.hotels.dao.service.ext.domain.response;

import com.hotels.common.constants.PhoneType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneNumber {
    private final String number;
    private final PhoneType phoneType;
}
