package com.expediagroup.dao.service.ext.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Staff {
    private final String fullName;
    private final Sex sex;
    private final List<Contact> contacts;
}
