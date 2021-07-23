package com.expediagroup.service.movie;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.stream.Collectors;

import com.expediagroup.beans.transformer.BeanTransformer;
import com.expediagroup.dao.movie.domain.response.MovieDaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expediagroup.service.movie.domain.response.MovieSvcResponse;
import com.expediagroup.dao.movie.MovieDao;
import com.expediagroup.dao.movie.domain.request.MovieDaoRequest;
import com.expediagroup.service.movie.domain.request.MovieSvcRequest;
import com.expediagroup.beans.BeanUtils;

@Service
public class MovieService {
    @Autowired
    private BeanUtils beanUtils;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private BeanTransformer movieSvcResponseTransformer;

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
