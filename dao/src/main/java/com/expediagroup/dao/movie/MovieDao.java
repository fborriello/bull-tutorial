package com.expediagroup.dao.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.dao.movie.domain.request.MovieDaoRequest;
import com.expediagroup.dao.movie.domain.response.MovieDaoResponse;
import com.expediagroup.dao.service.ext.BlockbusterService;
import com.expediagroup.dao.service.ext.domain.response.BlockbusterMovie;

@Service
public class MovieDao {
    @Autowired
    private BlockbusterService blockbusterService;

    @Autowired
    private BeanTransformer movieDaoResponseTransformer;

    public List<MovieDaoResponse> searchMovie(final MovieDaoRequest movieDaoRequest) {
        var foundMovies = blockbusterService.searchMovie(movieDaoRequest.title());
        return transformResponse(foundMovies);
    }

    private List<MovieDaoResponse> transformResponse(final List<BlockbusterMovie> foundMovies) {
        return foundMovies.stream()
                .map(movie -> movieDaoResponseTransformer.transform(movie, MovieDaoResponse.class))
                .toList();
    }
}
