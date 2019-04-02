package com.hotels.dao.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.beans.transformer.Transformer;
import com.hotels.dao.movie.domain.response.MovieDaoResponse;
import com.hotels.dao.movie.domain.request.MovieDaoRequest;
import com.hotels.dao.service.ext.BlockbusterService;
import com.hotels.dao.service.ext.domain.response.BlockbusterMovie;

@Service
public class MovieDao {
    @Autowired
    private BlockbusterService blockbusterService;

    @Autowired
    private Transformer movieDaoResponseTransformer;

    public List<MovieDaoResponse> searchMovie(final MovieDaoRequest movieDaoRequest) {
        List<BlockbusterMovie> foundMovies = blockbusterService.searchMovie(movieDaoRequest.getTitle());
        // response transformation
        return transformResponse(foundMovies);
    }

    private List<MovieDaoResponse> transformResponse(final List<BlockbusterMovie> foundMovies) {
        return foundMovies.stream()
                .map(movie -> movieDaoResponseTransformer.transform(movie, MovieDaoResponse.class))
                .collect(Collectors.toList());
    }
}
