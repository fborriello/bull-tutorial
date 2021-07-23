package com.expediagroup.dao.movie.domain.response;

import com.expediagroup.common.constants.ContactType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContactDao {
    private final String value;
    private final ContactType contactType;
}
