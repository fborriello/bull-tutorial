package com.expediagroup.dao.service.ext.domain.response;

import com.expediagroup.common.constants.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Contact {
    private final String value;
    private final ContactType contactType;
}
