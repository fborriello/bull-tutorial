package com.expediagroup.dao.movie.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Sex;

public record PersonDao(String fullName, Sex sex,
                        List<ContactDao> contacts) {
}
