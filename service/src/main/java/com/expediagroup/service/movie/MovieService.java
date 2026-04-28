package com.expediagroup.service.movie;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expediagroup.beans.BeanUtils;
import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.dao.movie.MovieDao;
import com.expediagroup.dao.movie.domain.request.MovieDaoRequest;
import com.expediagroup.dao.movie.domain.response.MovieDaoResponse;
import com.expediagroup.service.movie.domain.request.MovieSvcRequest;
import com.expediagroup.service.movie.domain.response.MovieSvcResponse;

@Service
public class MovieService {
    private final BeanUtils beanUtils;
    private final MovieDao movieDao;
    private final BeanTransformer movieSvcResponseTransformer;

    @Autowired
    public MovieService(final BeanUtils beanUtils, final MovieDao movieDao,
            final BeanTransformer movieSvcResponseTransformer) {
        this.beanUtils = beanUtils;
        this.movieDao = movieDao;
        this.movieSvcResponseTransformer = movieSvcResponseTransformer;
    }

    public List<MovieSvcResponse> searchMovie(final MovieSvcRequest movieSvcRequest) {
        notNull(movieSvcRequest, "movieSvcRequest cannot be null!");
        var beanTransformer = beanUtils.getTransformer();
        // request transformation
        var movieDaoRequest = beanTransformer.transform(movieSvcRequest, MovieDaoRequest.class);
        var foundMovies = movieDao.searchMovie(movieDaoRequest);
        // response transformation
        return transformResponse(foundMovies);
    }

    private List<MovieSvcResponse> transformResponse(final List<MovieDaoResponse> foundMovies) {
        return foundMovies.stream()
                .map(movie -> movieSvcResponseTransformer.transform(movie, MovieSvcResponse.class))
                .collect(Collectors.toList());
    }
}
