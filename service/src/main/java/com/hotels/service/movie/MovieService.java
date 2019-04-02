package com.hotels.service.movie;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import com.hotels.dao.movie.domain.response.MovieDaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.service.movie.domain.response.MovieSvcResponse;
import com.hotels.dao.movie.MovieDao;
import com.hotels.dao.movie.domain.request.MovieDaoRequest;
import com.hotels.service.movie.domain.request.MovieSvcRequest;
import com.hotels.beans.BeanUtils;
import com.hotels.beans.transformer.Transformer;

@Service
public class MovieService {
    @Autowired
    private BeanUtils beanUtils;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private Transformer movieSvcResponseTransformer;

    public List<MovieSvcResponse> searchMovie(final MovieSvcRequest movieSvcRequest) {
        notNull(movieSvcRequest, "movieSvcRequest cannot be null!");
        Transformer beanTransformer = beanUtils.getTransformer();
        // request transformation
        MovieDaoRequest movieDaoRequest = beanTransformer.transform(movieSvcRequest, MovieDaoRequest.class);
        List<MovieDaoResponse> foundMovies = movieDao.searchMovie(movieDaoRequest);
        // response transformation
        return transformResponse(foundMovies);
    }

    private List<MovieSvcResponse> transformResponse(final List<MovieDaoResponse> foundMovies) {
        return foundMovies.stream()
                .map(movie -> movieSvcResponseTransformer.transform(movie, MovieSvcResponse.class))
                .collect(Collectors.toList());
    }
}
