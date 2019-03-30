package com.hotels.dao.movie;

import java.util.List;
import java.util.stream.Collectors;

import com.hotels.beans.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.dao.movie.domain.request.MovieDaoRequest;
import com.hotels.dao.service.ext.BlockbusterService;
import com.hotels.dao.service.ext.domain.response.BlockbusterMovie;

@Service
public class MovieDao {
    @Autowired
    private BlockbusterService blockbusterService;

    @Autowired
    private Transformer movieDaoResponseTransformer;

    public List<MovieDao> searchMovie(final MovieDaoRequest movieDaoRequest) {
        List<BlockbusterMovie> foundMovies = blockbusterService.searchMovie(movieDaoRequest.getTitle());
        return transformResponse(foundMovies);
    }

    private List<MovieDao> transformResponse(final List<BlockbusterMovie> foundMovies) {
        return foundMovies.stream()
                .map(movie -> movieDaoResponseTransformer.transform(movie, MovieDao.class))
                .collect(Collectors.toList());
    }
}
