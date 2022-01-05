package com.expediagroup.service.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

public record PersonSvc(String fullName, Sex sex, List<ContactSvc> contacts) {
}
