package com.hotels.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.dao.domain.request.MovieDaoRequest;
import com.hotels.dao.service.ext.BlockbusterService;
import com.hotels.dao.service.ext.domain.response.BlockbusterMovie;

@Service
public class MovieDao {
    @Autowired
    private BlockbusterService blockbusterService;

    public List<BlockbusterMovie> searchMovie(final MovieDaoRequest movieDaoRequest) {
        return blockbusterService.searchMovie(movieDaoRequest.getTitle());
    }
}
