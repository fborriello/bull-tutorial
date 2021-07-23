package com.expediagroup.dao.service.ext.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BlockbusterMovie {
    private final String id;
    private final String title;
    private final String description;
    private final Staff director;
    private final List<Staff> actors;
    private final Genre genre;
    private final float price;
}
