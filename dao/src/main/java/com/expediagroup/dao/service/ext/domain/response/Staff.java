package com.expediagroup.dao.service.ext.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

public record Staff(String fullName, Sex sex,
                    List<Contact> contacts) {
}
