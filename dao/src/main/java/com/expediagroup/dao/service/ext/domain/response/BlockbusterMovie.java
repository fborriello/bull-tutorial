package com.expediagroup.dao.service.ext.domain.response;

import java.util.List;

import com.expediagroup.common.constants.Genre;

public record BlockbusterMovie(String id, String title, String description,
                               Staff director,
                               List<Staff> actors,
                               Genre genre, float price) {
}
