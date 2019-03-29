package com.hotels.dao.service.ext.domain.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.hotels.common.constants.Genre;

@AllArgsConstructor
@Getter
public class BlockbusterMovie {
    private final String id;
    private final String title;
    private final String description;
    private final Staff director;
    private final List<Staff> actors;
    private final Genre genre;
}
